package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.RestaurantBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.OrderDao;
import ca.etsmtl.log210.DAO.RestaurantDao;
import ca.etsmtl.log210.DAO.UserAccountDao;
import ca.etsmtl.log210.Utils.EmailUtility;
import ca.etsmtl.log210.Utils.ExpressLivraisonSms;

/**
 * Aissou Idriss Jeanroy Cesar Murat David
 */
public class OrderUpdateState extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String RESTAURANT_ORDER_MANAGEMENT_ACCESS = "/ShowOrderRestaurant";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	public static final String ETAT_REQUETE = "etat";

	// The instance of UserAccountDao who give us the possibility to execute
	// requests to the DB about userAccount
	private OrderDao orderDao;

	UserAccountDao userAccountDao = null;

	// Attributs pour
	private String host;
	private String port;
	private String user;
	private String pass;

	/**
	 * Method who is executed the fist time that the servlet is create. Here we
	 * get the connection to the DB throw UserAccountDao class. We must get the
	 * connection just once if we don't want to have a too many connection error
	 * in MySql.
	 */
	public void init() throws ServletException {
		this.orderDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderDao();

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
		// Je recupere les valeurs du formulaire de modification d<un restaurant
		int idOrderRecu = Integer.parseInt(request.getParameter("idOrder"));
		int status = Integer.parseInt(request.getParameter("status"));

		/**
		 * Choix de l'update selon le statut de la commande 0 = A pr�parer -->
		 * doit devenir 1 = En preparation 1 = En pr�paration --> doit devenir 2
		 * = Faite
		 */

		request.setAttribute(ETAT_REQUETE,
				orderDao.updateOrderState(idOrderRecu, status));

		this.getServletContext()
				.getRequestDispatcher(RESTAURANT_ORDER_MANAGEMENT_ACCESS)
				.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Je recupere les valeurs du formulaire de modification d<un restaurant
		int idOrderRecu = Integer.parseInt(request.getParameter("idOrder"));
		int status = Integer.parseInt(request.getParameter("status"));

		/**
		 * Choix de l'update selon le statut de la commande 0 = A pr�parer -->
		 * doit devenir 1 = En preparation 1 = En pr�paration --> doit devenir 2
		 * = Faite
		 */

		request.setAttribute(ETAT_REQUETE,updateOrderState(idOrderRecu, status));

		// On recupre la variable de session
		HttpSession session = request.getSession();

		System.out.println("session recupéré");

		OrderBean order = null;
		UserAccountBean client = null;

		//On récupère la commande
		order = getOrder(idOrderRecu);

		// On recupere le client qui a passé la commande
		client = getUserAccount(order.getIdUserAccount());

		// Variables utilisees pour l'envoi du courriel
		String recipient = client.getEmail();

		String subject = "Changement de statut de votre commande chez ExpressLivraison";

		if (status == 0) {
			System.out.println("changement pour en preparation");
			// On genere et stocke le contenu du message a
			// envoyer
			String content = this.generateMailContent(order, client,
					"en preparation");

			// Envoie de du courriel
			try {
				
				sendEmail(host, port, user, pass, recipient,
						subject, content);
				
				System.out.println(host + port + user + pass + recipient
						+ subject + content);
				
				//Envoie du SMS
				ExpressLivraisonSms sms = expressLivraisonSmsFabrique();
				sms.envoyerSmsCommandeEnPreparation(client.getPhoneNumber());
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (status == 1) {
			System.out.println("prete a etre livré");
			// On genere et stocke le contenu du message a
			// envoyer
			String content = this.generateMailContent(order, client,
					"prete a etre livre");
			// Envoie de du courriel
			try {
				sendEmail(host, port, user, pass, recipient,
						subject, content);
				System.out.println(host + port + user + pass + recipient
						+ subject + content);
				
				//Envoie du SMS
				ExpressLivraisonSms sms = new ExpressLivraisonSms();
				sms.envoyerSmsCommandeTerminee(client.getPhoneNumber());
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		this.getServletContext()
				.getRequestDispatcher(RESTAURANT_ORDER_MANAGEMENT_ACCESS)
				.forward(request, response);
	}

	public int updateOrderState(int idOrderRecu, int status) {
		
		return orderDao.updateOrderState(idOrderRecu, status);
	}

	public void sendEmail(String host2, String port2, String user2,
			String pass2, String recipient, String subject, String content) throws AddressException, MessagingException {
		EmailUtility.sendEmail(host, port, user, pass, recipient,
				subject, content);
	}

	public ExpressLivraisonSms expressLivraisonSmsFabrique() {
		return new ExpressLivraisonSms();
	}

	public UserAccountBean getUserAccount(int idUserAccount) {
		return userAccountDao.getUserAccountByID(idUserAccount);
	}

	public OrderBean getOrder(int idOrderRecu) {
		return orderDao.getOrder(idOrderRecu);
	}

	public String generateMailContent(OrderBean order, UserAccountBean client,
			String statut) {
		String beginning = "Chere " + client.getFirstName() + " "
				+ client.getName() + ",<br><br> Votre commande numero : "
				+ order.getConfirmationCode() + " est maintenant " + statut+".";
		String content = "<br>";
		String end = "<br><br>Merci de votre fidelite, au plaisir de vous revoir !";
		content = beginning + content + end;

		return content;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
