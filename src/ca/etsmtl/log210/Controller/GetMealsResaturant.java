package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;

public class GetMealsResaturant extends HttpServlet {

	/**
	 * 
	 */

	private static final long serialVersionUID = 6561975988106199732L;
	public static final String CONF_DAO_FACTORY = "daofactory";
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

		this.mealDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getMealMenuDao();
	}

	public String getServletInfo() {
		return "Servlet ShowAllMenuResto";

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
	
		String xml="";
		
		if (request.getParameter("menuId") != null) 
		{
			ID_MENU = Integer.parseInt(request.getParameter("menuId"));
			
			// Creation des liste de donnes de requete
			ArrayList<MealBean> mealList;

			// On recupere les donnees qui seront recu avec la requete sql
			mealList = mealDao.showAllMealFromMenu(ID_MENU);
			
			for(MealBean meal : mealList)
			 {
				//Je cree un xml pour chaque menu et je le parserai dans mon javascript pour en suite créé des balise html avec les informations
				 String description=meal.getDescription();
				 
				 if(description=="" || description==null || description.equals(""))
				 {
					 description="Sans description";
				 }
				 
				 xml=xml + "<MEAL>"
								+ "	<ID>"+meal.getIdMeal()+"</ID>"
								+ "	<NAME>"+meal.getName()+"</NAME>"
								+ "	<DESCRIPTION>"+description+"</DESCRIPTION>"
								+ "	<PRICE>"+meal.getPrice()+"</PRICE>"
								+ "</MEAL>";
				 
			 }
			
		} 

		response.getWriter().write("<SERVER>"+xml+"</SERVER>");
	}

}
