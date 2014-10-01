package ca.etsmtl.log210.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.RestaurantDao;

public class SwitchRestaurantToNotVisible extends HttpServlet 
	{

		 public static final String CONF_DAO_FACTORY = "daofactory";
		 public static final String RESTAURANT_MANAGEMENT_ACCESS     = "/Restrict/Admin/RestaurantManagement.jsp";
		 public static final String INACTIVE_RESTAURANT_LISTE_ATTRIBUTE = "inactiveRestaurantList";
		 public static final String ACTIVE_RESTAURANT_LISTE_ATTRIBUTE = "activeRestaurantList";
		 
	     //The instance of UserAccountDao who give us the possibility to execute requests to the DB about userAccount
	     private RestaurantDao restaurantDao;
	     
	     
	    /**
	     * Method who is executed the fist time that the servlet is create. Here we get the connection to the DB throw UserAccountDao class.
	     * We must get the connection just once if we don't want to have a too many connection error in MySql.
	     */
		 public void init() throws ServletException 
		 {
		    	this.restaurantDao= ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getRestaurantDao();
		 }
		
		 
		 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
		 {
			 int idRestaurant=Integer.parseInt( request.getParameter("idRestaurant"));
			 
			 boolean retour = restaurantDao.switchToNotVisibleRestaurant(idRestaurant);
			 
		 }
}
