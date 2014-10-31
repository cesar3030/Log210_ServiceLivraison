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

public class ShowOrderItems extends HttpServlet {

	/**
	 * 
	 */

	private static final long serialVersionUID = 6561975988150619973L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String MENU_MANAGEMENT_ACCESS = "/Restrict/Restaurateur/ShowOrderItems.jsp";


	// Instance de menu qui va nous permettre de faire des requetes sur la BD
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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/* Récupération de la session depuis la requête */

		int idOrder;
		
		//HttpSession session = request.getSession();
		System.out.println(request.getParameter("idOrder"));
		idOrder= Integer.parseInt(request.getParameter("idOrder"));

		// Creation des liste de donnes de requete
		ArrayList<OrderDetailsItemsBean> orderItemList;

		// On recupere les donnees qui seront recu avec la requete sql
		orderItemList = OrderItemDao.showAllOrderItem(idOrder);

		System.out.println(orderItemList);
		// AJOUT DES ELEMENTS A LA REQUETE DE REPONSE
		request.setAttribute("orderItemList", orderItemList);

		System.out.println("Je retourne dans ShoOrderItems apres la requete le pb n'est pas la requete");
		// On renvoie la requete de reponse au bon endroit du restrict
		this.getServletContext().getRequestDispatcher(MENU_MANAGEMENT_ACCESS)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}
