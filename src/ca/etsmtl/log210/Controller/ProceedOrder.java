package ca.etsmtl.log210.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.AddressBean;
import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.AddressDao;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;
import ca.etsmtl.log210.DAO.OrderDao;
import ca.etsmtl.log210.DAO.OrderItemDao;
import ca.etsmtl.log210.DAO.UserAccountDao;
import ca.etsmtl.log210.Utils.EmailUtility;
import ca.etsmtl.log210.Utils.SmsUtility;

/**
 * Aissou Idriss Jeanroy Cesar Murat David
 */
public class ProceedOrder extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String SHOW_PAGE_ORDER_DONE = "/Restrict/Client/OrderDone.jsp";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	public static final String ATTRIBUTE_ORDER = "order";
	public static final String ATTRIBUTE_ADDRESS = "address";
	private static String ORDER = "order";
	public static final String SESSION_USER = "userSession";

	// Attributs pour
	private String host;
	private String port;
	private String user;
	private String pass;

	private OrderDao orderDao;
	private OrderItemDao orderItemDao;
	private AddressDao addressDao;
	private UserAccountDao userAccountDao;

	public void init() throws ServletException {
		this.orderDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderDao();
		this.orderItemDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderItemDao();
		this.addressDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getAddressDao();
		this.userAccountDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getUserAccountDao();

		// On va chercher les informations du serveur SMTP stockees dans web.xml
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> returnMessage = new HashMap<String, String>();

		// Le bean permettant de stocker l'adresse de livraison
		AddressBean deliveryAddress = null;

		OrderBean order = null;
		UserAccountBean client = null;
		int idNewOrder;
		// variable utilise pour verifier que les requetes sql s'est terminent
		// sans erreurs
		boolean continute = true;

		// Je recupere l'addresse de livraison
		int idAddress = Integer.parseInt(request.getParameter("addressList"));

		// je recupere la date et l'heure de livraison
		String hourAndDate = request.getParameter("dateAndHour");

		// On recupre la variable de session
		HttpSession session = request.getSession();

		// On va chercher la commande stockee dans la variable de session
		order = (OrderBean) session.getAttribute(ORDER);

		// On recupere le client qui est connecte
		client = (UserAccountBean) session.getAttribute(SESSION_USER);

		// On set l'id du client
		order.setIdUserAccount(client.getUserId());

		// On set a la commande la date et l'heure de la livraison
		order.setHourAndDate(hourAndDate);

		// Si -1 est retourne, cela veut dire que c'est une nouvelle adresse
		if (idAddress == -1) {
			// je recupere la la nouvelle adresse
			String newAddress = request.getParameter("newAddress");
			idAddress = addressDao.newAddress(newAddress,
					order.getIdUserAccount());

			// On test si l'ajout s'est bien fait
			if (idAddress < 0 || idAddress == 0) {
				// si il y a eu une erreur, on arrete le processus d'ajout de la
				// commande
				continute = false;
				System.out
						.println("Une erreur est survenue lors de la création en BD de la nouvelle adresse. Veuillez reessayer.");
				returnMessage
						.put("fail",
								"Une erreur est survenue lors de la création en BD de la nouvelle adresse. Veuillez reessayer.");
			}

		}

		/*
		 * On teste si l'identifiant de la dernière adresse utilisée (stocké
		 * dans le compte du client) est la même que celle qui a ete selectionee
		 * par l'utilisateur lors de la commande courante. Si c'est la même on
		 * fait pas de changement en BD Si ce n'est pas la même, on va modifier
		 * le champ USR_idMainAddress de la table tbuseraccount. Ce champ sert a
		 * definir l'adresse par defaut du compte afin que ce soit la derniere
		 * adresse utilisee qui soit affiche en premiere a l'utilisateur lors de
		 * ses futurs commandes.
		 */
		if (continute && client.getIdMainAddress() != idAddress) {
			client.setIdMainAddress(idAddress);
			continute = userAccountDao.updateIdMainAddressUsed(client);

			if (continute == false) {
				System.out
						.println("Une erreur est survenue lors de la modification de l'adresse principale en BD. Veuillez reessayer.");
				returnMessage
						.put("fail",
								"Une erreur est survenue lors de la modification de l'adresse principale en BD. Veuillez reessayer.");
			}
		}

		// On set l'id de l'adresse
		order.setIdAddress(idAddress);

		// Je cree la commande et recupere son identifiant pour l'associer aux
		// items de la commande
		idNewOrder = orderDao.newOrder(order);
		order.setIdOrder(idNewOrder);

		// On test si une commande a ete cree avec succes
		if (idNewOrder > 0) {
			// On cree et ajoute les items a la commande
			for (OrderItemBean item : order.getOrderItemsList()) {
				// Si il n'y a pas eu d'erreur lors de l'ajout de precedant
				// items on en ajoute un nouveau
				if (continute == true) {
					// On set a l'item, l'identifiant de la commande qui a ete
					// genere lorsque la commande a ete cree en bd
					item.setIdOrder(order.getIdOrder());

					// On ajoute l'item a la commande
					continute = orderItemDao.newOrderItem(item);

				} else {
					break;
				}
			}

			if (continute == true) {
				// On genere le code de confirmation
				String confirmCode = newConfirmationCode(order.getIdOrder());

				// On set au bean le code de confirmation
				order.setConfirmationCode(confirmCode);

				// On update en BD le code de confirmation
				continute = orderDao.setConfirmationCode(order);

				if (continute == true) {
					deliveryAddress = addressDao.getAddress(idAddress);

					if (continute == true) {
						try {
							returnMessage.put("succes", "Commande validé !");

							// On set en attribut la commande pour pouvoir
							// recuperer le numero de commande
							request.setAttribute(ATTRIBUTE_ORDER, order);

							// On set en attribut l'adresse de livraison pour
							// l'afficher
							request.setAttribute(ATTRIBUTE_ADDRESS,
									deliveryAddress);

							// Variables utilisees pour l'envoi du courriel
							String recipient = client.getEmail();

							String subject = "Confirmation de votre commande chez ExpressLivraison";

							// On genere et stocke le contenu du message a
							// envoyer
							String content = this.generateMailContent(order,
									client);

							// Envoie de du courriel
							EmailUtility.sendEmail(host, port, user, pass,
									recipient, subject, content);

							// SmsUtility.sendTextMessage("Voic commande est en cours de pr�paration","+"+client.getPhoneNumber());

							returnMessage.put("email",
									"Un message vous a �t� envoy� � l'adresse: "
											+ client.getEmail());

						} catch (Exception ex) {
							ex.printStackTrace();
							returnMessage.put("email",
									"Une erreur est survenue lors de l'envoie du mail de confirmation: "
											+ ex.getMessage());
						} finally {

							request.setAttribute(REQUEST_FINISHED_STATE,
									returnMessage);
							this.getServletContext()
									.getRequestDispatcher(SHOW_PAGE_ORDER_DONE)
									.forward(request, response);
						}

					} else {
						System.out
								.println("Une erreur est survenue lors de la recuperation en BDde l'adresse de livraison.");
						returnMessage
								.put("fail",
										"Une erreur est survenue lors de la recuperation en BD de l'adresse de livraison.");

						request.setAttribute(REQUEST_FINISHED_STATE,
								returnMessage);
						this.getServletContext()
								.getRequestDispatcher(SHOW_PAGE_ORDER_DONE)
								.forward(request, response);
					}
				} else {
					System.out
							.println("Une erreur est survenue lors de l'ajout en BD du code de confirmation de la commande.");
					returnMessage
							.put("fail",
									"Une erreur est survenue lors de l'ajout en BD du code de confirmation de la commande.");

					request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
					this.getServletContext()
							.getRequestDispatcher(SHOW_PAGE_ORDER_DONE)
							.forward(request, response);
				}

			} else {
				System.out
						.println("Une erreur est survenue lors de l'ajout en BD d'un item dans la commande. Veuillez reessayer.");
				returnMessage
						.put("fail",
								"Une erreur est survenue lors de l'ajout en BD d'un item dans la commande. Veuillez reessayer.");

				request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
				this.getServletContext()
						.getRequestDispatcher(SHOW_PAGE_ORDER_DONE)
						.forward(request, response);
			}

		} else {
			System.out
					.println("Une erreur est survenue lors de la création en BD d'une commande. Veuillez reessayer.");
			returnMessage
					.put("fail",
							"Une erreur est survenue lors de la création en BD d'une commande. Veuillez reessayer.");

			request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
			this.getServletContext().getRequestDispatcher(SHOW_PAGE_ORDER_DONE)
					.forward(request, response);
		}
	}

	/**
	 * Methode qui genere le code de confirmation de la commande. le code de
	 * confirmation a une longeur de 10 carracteres: des 0 et l'id de la
	 * commande ex: 0000000090 (8 zeros et l'id de la commande 90)
	 * 
	 * @param idCommande
	 * @return le code de confirmation
	 */
	private String newConfirmationCode(int idCommande) {
		String confirmCode = "";

		// J'ajout de l'id de la commande
		confirmCode = confirmCode + idCommande;

		// On complete la chaine avec des zeros devant l'identifiant de la
		// commande tant que la
		// chaine ne fait pas 10 caracteres
		while (confirmCode.length() < 10) {
			confirmCode = "0" + confirmCode;
		}

		return confirmCode;

	}

	/**
	 * M�thode qui cree le contenu du mail de confirmation de commande.
	 * 
	 * @param order
	 *            la commande
	 * @param client
	 *            le client qui a effectue la commande
	 * @return le contenu du courriel contenant le resumer de la commande
	 */
	private String generateMailContent(OrderBean order, UserAccountBean client) {
		String beginning = "Ch�re " + client.getFirstName() + " "
				+ client.getName()
				+ ",<br><br> Voici le r�sum� de la commande n�"
				+ order.getConfirmationCode();
		String content = "<br>";
		String end = "<br><br>Merci de votre fid�lit�, au plaisir de vous revoir !";

		for (OrderItemBean item : order.getOrderItemsList()) {
			int totalItemPrice = item.getQuantity() * item.getMeal().getPrice();

			content = content + "<br>" + item.getMeal().getName() + " * "
					+ item.getQuantity() + " = " + totalItemPrice;
		}

		content = content
				+ "<br><br>Le montant total de votre commande est de "
				+ order.getTotalPrice() + "$";

		content = beginning + content + end;

		return content;
	}

}
