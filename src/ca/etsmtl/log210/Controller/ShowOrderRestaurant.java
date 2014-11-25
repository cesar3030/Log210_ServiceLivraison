package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.OrderBean;

import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.OrderDao;

/**
 * Aissou Idriss Jeanroy Cesar Murat David
 */

/**
 * Classe ShowOrderRestaurant affiliee a ShowOrderRestaurant.jsp
 * qui permet de recuperer les differentes commandes (selon les etats)
 * d'un restaurant
 * 
 *
 */
public class ShowOrderRestaurant extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5687451452007324770L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String SHOW_ORDER_MANAGEMENT_ACCESS = "/Restrict/Restaurateur/ShowOrderRestaurant.jsp";

	// The instance of UserAccountDao who give us the possibility to execute
	// requests to the DB about userAccount
	private OrderDao orderDao;

	/**
	 * Method who is executed the fist time that the servlet is create. Here we
	 * get the connection to the DB throw UserAccountDao class. We must get the
	 * connection just once if we don't want to have a too many connection error
	 * in MySql.
	 */
	public void init() throws ServletException {
		this.orderDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderDao();
	}

	/**
	 * Methode doGet qui grace a l'id dun restaurant va pouvoir aller recuperer
	 * toutes les commandes d un restaurant par utilisation des methodes :
	 *  orderDao.getListOrder0(idRestaurant); (non traitees)
	 *  orderDao.getListOrder1(idRestaurant); ( en preparation)
	 *  orderDao.getListOrder2(idRestaurant); ( terminees
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		int idRestaurant;

		if (request.getParameter("idRestaurant") == null) {
			idRestaurant = (int) request.getSession().getAttribute(
					"idRestaurant");
		} else {
			idRestaurant = Integer.parseInt(request
					.getParameter("idRestaurant"));
		}

		// Creation des differentes listes permettant de recceuil les differentes commandes
		request.getSession().setAttribute("idRestaurant", idRestaurant);
		ArrayList<OrderBean> orderList0 = new ArrayList<OrderBean>();
		ArrayList<OrderBean> orderList1 = new ArrayList<OrderBean>();
		ArrayList<OrderBean> orderList2 = new ArrayList<OrderBean>();

		//execution des methodes getListOrder permettant de recuperer les differentes categories de commande
		orderList0 = orderDao.getListOrder0(idRestaurant);
		orderList1 = orderDao.getListOrder1(idRestaurant);
		orderList2 = orderDao.getListOrder2(idRestaurant);
		
		//Ajout des differentes liste a la requete de retour
		request.setAttribute("orderList0", orderList0);
		request.setAttribute("orderList1", orderList1);
		request.setAttribute("orderList2", orderList2);

		//Redirection vers la servlet
		this.getServletContext()
				.getRequestDispatcher(SHOW_ORDER_MANAGEMENT_ACCESS)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
