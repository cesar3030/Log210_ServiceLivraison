package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;

/**
 * 
 * @author David Page affichant tous les plats d'un menu à l'utilisateur
 */
public class ShowAllMealMenu extends HttpServlet {

	private static final long serialVersionUID = 6561975988106199732L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	// Permet de savoir vers quelle page aller, une fois le traitement effectué
	public static final String MENU_MANAGEMENT_ACCESS = "/Restrict/Restaurateur/ShowAllMealMenu.jsp";
	public static final String MEAL_MENU_ATTRIBUTE = "mealMenuList";
	public static final String MEAL_MENU_TITLE_ATTRIBUTE = "mealMenu";
	public static final String ID_MENU_SESSION = "idMenuSession";
	public static final String MENU_NAME_TITRE = "menuTitleName";
	public static final String ID_RETAURANT = "idRestaurant";

	public String MENU_NAME = "menuName";
	public int ID_MENU;
	public int ID_RESTAURANT;

	// Instance de menu qui va nous permettre de faire des requetes sur la BD
	private MealDao mealDao;

	/**
	 * Method who is executed the fist time that the servlet is create. Here we
	 * get the connection to the DB throw UserAccountDao class. We must get the
	 * connection just once if we don't want to have a too many connection error
	 * in MySql.
	 */
	public void init() throws ServletException {

		this.mealDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getMealMenuDao();
	}

	public String getServletInfo() {
		return "Servlet ShowAllMenuResto";

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* Récupération de la session depuis la requête */

		HttpSession session = request.getSession();

		if (request.getParameter("idMenu") != null) {
			ID_MENU = Integer.parseInt(request.getParameter("idMenu"));
			session.setAttribute(ID_MENU_SESSION,
					request.getParameter("idMenu"));
			MENU_NAME = request.getParameter("name");
		} else {
			ID_MENU = Integer.parseInt((String) session
					.getAttribute("idMenuSession"));
			session.setAttribute(ID_MENU_SESSION,
					session.getAttribute("idMenuSession"));
		}

		if (request.getParameter("idRestaurant") != null) {
			ID_RESTAURANT = Integer.parseInt(request
					.getParameter("idRestaurant"));
			session.setAttribute("idRestaurant", ID_RESTAURANT);
		}

		// Creation des liste de donnes de requete
		ArrayList<MealBean> mealList;

		// On recupere les donnees qui seront recu avec la requete sql
		mealList = mealDao.showAllMealFromMenu(ID_MENU);

		// AJOUT DES ELEMENTS A LA REQUETE DE REPONSE
		request.setAttribute(MEAL_MENU_ATTRIBUTE, mealList);
		request.setAttribute(MENU_NAME_TITRE, MENU_NAME);
		request.setAttribute(ID_RETAURANT, ID_RESTAURANT);
		request.setAttribute(ID_MENU_SESSION, ID_MENU);

		// On renvoie la requete de reponse au bon endroit du restrict
		this.getServletContext().getRequestDispatcher(MENU_MANAGEMENT_ACCESS)
				.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

}
