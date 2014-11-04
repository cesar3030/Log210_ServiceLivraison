package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.RestaurantBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.RestaurantDao;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */
public class HomeOrderRestaurant extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String RESTAURANT_MANAGEMENT_ACCESS = "/Restrict/Restaurateur/HomeOrderRestaurant.jsp";
	// public static final String RESTAURANT_MANAGEMENT_ACCESS =
	// "/HomeRestaurateurManagement";
	public static final String ACTIVE_RESTAURANT_LISTE_ATTRIBUTE = "activeRestaurantList";

	public static final String SESSION_USER = "userSession";

	// The instance of UserAccountDao who give us the possibility to execute
	// requests to the DB about userAccount
	private RestaurantDao restaurantDao;

	/**
	 * Method who is executed the fist time that the servlet is create. Here we
	 * get the connection to the DB throw UserAccountDao class. We must get the
	 * connection just once if we don't want to have a too many connection error
	 * in MySql.
	 */
	public void init() throws ServletException {
		this.restaurantDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getRestaurantDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserAccountBean restaurateurConnected = (UserAccountBean) session
				.getAttribute("userSession");
		int idRestaurateur = restaurateurConnected.getUserId();

		ArrayList<RestaurantBean> activeRestaurantList = new ArrayList<RestaurantBean>();

		activeRestaurantList = restaurantDao
				.getActiveRestaurantsForRestaurateur(idRestaurateur);

		request.setAttribute(ACTIVE_RESTAURANT_LISTE_ATTRIBUTE,
				activeRestaurantList);

		this.getServletContext()
				.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
