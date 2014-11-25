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

/**
 * Classe qui est affiliée HomeOrderRestaurant.jsp (concernant les commandes)
 * Celle-ci affiche l'ensemble des commandes par restaurants lies a un restaurateur.
 * De ce fait, il disposera d'une vue d'ensemble sur les commandes de ces restaurants.
 * Cette servlet est accessible depuis l'onglet Mes Commandes de l'interface
 * utilisateur restaurateur.
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

	/**
	 * 	Methode doGet qui se déclanchera lorsqu'un restaurateur sélectionnera 
	  * l'onglet Mes commandes sur son interface user.
	  * Des lors, ceci va permettre d'afficher l'ensemble des restaurants afin de voir les commandes
	  * qui y sont affectees.  
	  * L'affichage des restaurant est liee a son identifiant utilisateur par utilisation de la methode :
	  * 				restaurantDao.getActiveRestaurantsForRestaurateur(idRestaurateur)
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//On recupere l'identifiant unique de l utilisateur restaurateur qui servira de parametre 
		UserAccountBean restaurateurConnected = (UserAccountBean) session
				.getAttribute("userSession");
		int idRestaurateur = restaurateurConnected.getUserId();

		//On execute la methode restaurantDao.getActiveRestaurantsForRestaurateur(idRestaurateur) pour recuperer a liste des resaurants
		ArrayList<RestaurantBean> activeRestaurantList = new ArrayList<RestaurantBean>();
		activeRestaurantList = restaurantDao
				.getActiveRestaurantsForRestaurateur(idRestaurateur);

		//On inserer la liste a la requete de retour
		request.setAttribute(ACTIVE_RESTAURANT_LISTE_ATTRIBUTE,
				activeRestaurantList);
		
		//On redirige vers la servlet de redirection
		this.getServletContext()
				.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
