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

public class NewMeal extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3405541725228732771L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String MEAL_MENU = "/ShowAllMealMenu";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	public static final String ID_MENU_REQUETE = "idMenu";

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

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		MealBean mealToAdd = new MealBean();
System.out.println(session.getAttribute("idMenuSession"));
		mealToAdd.setIdMenu(Integer.parseInt((String) session.getAttribute("idMenuSession")));
		mealToAdd.setName(request.getParameter("name"));
		mealToAdd.setPrice(Integer.parseInt(request.getParameter("price")));
		mealToAdd.setDescription(request.getParameter("description"));

		boolean insertReturn;

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
		request.setAttribute(ID_MENU_REQUETE, session.getAttribute("idMenuSession"));

		this.getServletContext().getRequestDispatcher(MEAL_MENU)
				.forward(request, response);
	}

}
