package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;

/**
 * Aissou Idriss Jeanroy Cesar Murat David C
 * Classe permetant de supprimer un plat
 * d'un menu
 */
public class DeleteMeal extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3405541725228732771L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String MEAL_MENU = "/ShowAllMealMenu";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";

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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean insertReturn;
		System.out.println("-----DÉBUT DELETE MEAL MENU DÉBUT------");

		// On récupère la session actuelle
		HttpSession session = request.getSession();

		// On envoie l'id du plat à supprimer au controller qui exécutera une
		// requete pour supprimer le plat
		insertReturn = mealDAO.deleteNewMeal(Integer.parseInt(request
				.getParameter("idMeal")));

		Map<String, String> returnMessage = new HashMap<String, String>();

		if (insertReturn == true) {
			returnMessage.put("succes", "Le plat a ete ajoute avec succes !");
		} else {
			returnMessage
					.put("fail",
							"Une erreur est survenue, le plat n'a pas pu etre ajoute. Veuillez reessayer.");
		}
		// Paramètres de retour pour afficher les informations à l'utilisateur
		session.setAttribute("retourInt", 1);
		session.setAttribute("retourString", request.getParameter("nameMeal"));

		System.out.println("-----FIN DELETE MEAL MENU FIN------");
		this.getServletContext().getRequestDispatcher(MEAL_MENU)
				.forward(request, response);
	}

}
