package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.Beans.OrderDetailsItemsBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.OrderItemDao;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */


/**
 * Servlets qui attaché à la page ShowOrderITems.jsp
 *
 */
public class ShowOrderItems extends HttpServlet {

	//LES VARIABLES STATIQUES
	private static final long serialVersionUID = 6561975988150619973L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String NUM_COMMANDE = "numCommande";
	public static final String MONTANT_TOT= "total";
	public static final String MENU_MANAGEMENT_ACCESS = "/Restrict/Restaurateur/ShowOrderItems.jsp";


	// Instance de la DAO OrderItemDao qui va nous permettre de faire des requetes sur la BD des items des commandes
	private OrderItemDao OrderItemDao;

	/**
	 * Method who is executed the fist time that the servlet is create. Here we
	 * get the connection to the DB throw UserAccountDao class. We must get the
	 * connection just once if we don't want to have a too many connection error
	 * in MySql.
	 */
	public void init() throws ServletException {

		this.OrderItemDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderItemDao();
	}

	/**
	 *doGet(HttpServletRequest request, HttpServletResponse response)
	 *Methode qui va permettre selon la requete recu (URL)
	 *de renvoyer une liste d'items selon un numéro de commande
	 *
	 *Retour :
	 *			int idOrder = numéro de la commande
	 *			ArrayList<OrderDetailsItemsBean> orderItemList = La liste des items d'une commande selon son id
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Les variables permettant de recevoir l id de a commande et la liste d'item
		int idOrder;
		int etat=0;
		ArrayList<OrderDetailsItemsBean> orderItemList;
		
		
		//On récupére l'id de la commande depuis l url
		idOrder= Integer.parseInt(request.getParameter("idOrder"));
		
		// On recupere les donnees qui seront recu avec la requete sql
		orderItemList = OrderItemDao.showAllOrderItem(idOrder);
		
		
		// AJOUT DES ELEMENTS A LA REQUETE DE REPONSE
		request.setAttribute(NUM_COMMANDE, idOrder);
		request.setAttribute(MONTANT_TOT, OrderItemDao.calculMontantTotal(orderItemList));
		request.setAttribute("orderItemList", orderItemList);

		
		// On retourne la requete de reponse au bon endroit du restrict
		this.getServletContext().getRequestDispatcher(MENU_MANAGEMENT_ACCESS)
				.forward(request, response);
	}

	
	/**
	 * Cette fonction n'est pas utiliser dans ce Servlet ShowOrderItems
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}
