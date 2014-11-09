package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;

/**
 * Aissou Idriss Jeanroy Cesar Murat David Permet d'ajouter un plat dans un menu
 */
public class NewMeal extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3405541725228732771L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String MEAL_MENU = "/ShowAllMealMenu";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	public static final String ID_MENU_REQUETE = "idMenuSession";

	private MealDao mealDAO;

	/**
	 * Method who is executed the fist time that the servlet is create. Here we
	 * get the connection to the DB throw UserAccountDao class. We must get the
	 * connection just once if we don't want to have a too many connection error
	 * in MySql.
	 */
	public void init() throws ServletException {
		this.mealDAO = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getMealDao();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("-----DÉBUT DANS NEW MEAL DÉBUT------");

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		// Création d'un Bean de plat
		MealBean mealToAdd = new MealBean();
		System.out.println("ID DU MENU : "
				+ session.getAttribute("idMenuSession"));

		// On remplis le Bean
		mealToAdd.setIdMenu(Integer.parseInt((String) session
				.getAttribute("idMenuSession")));
		mealToAdd.setName(request.getParameter("name"));
		mealToAdd.setPrice(Integer.parseInt(request.getParameter("price")));
		mealToAdd.setDescription(request.getParameter("description"));

		boolean insertReturn;

		// On envoie le bean à insérer au controlleur qui fait le lien avec la
		// BDD
		insertReturn = mealDAO.addNewMeal(mealToAdd);

		Map<String, String> returnMessage = new HashMap<String, String>();

		if (insertReturn == true) {
			returnMessage.put("succes", "Le plat a ete ajoute avec succes !");
		} else {
			returnMessage
					.put("fail",
							"Une erreur est survenue, le plat n'a pas pu etre ajoute. Veuillez reessayer.");
		}

		request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
		System.out.println("voici l'id du menu : "
				+ session.getAttribute("idMenuSession"));
		request.setAttribute(ID_MENU_REQUETE,
				session.getAttribute("idMenuSession"));

		System.out.println("voici l'idRestaurant : "
				+ session.getAttribute("idRestaurant"));
		request.setAttribute("idRestaurant",
				session.getAttribute("idRestaurant"));

		//Récupération d'informations pour les afficher à l'utilisateur
		if (request.getParameter("description").equals("")) {
			session.setAttribute("retourInt", 3);
			session.setAttribute("retourString", request.getParameter("name"));
		} else {
			session.setAttribute("retourInt", 0);
			session.setAttribute("retourString", request.getParameter("name"));
		}

		System.out.println("-----FIN DANS NEW MEAL FIN------");

		this.getServletContext().getRequestDispatcher(MEAL_MENU)
				.forward(request, response);

	}

}
