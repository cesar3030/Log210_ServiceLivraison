package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.Beans.RestaurantBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.RestaurantDao;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */
public class GetListVisibleRestaurants extends HttpServlet
{

	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String RESTAURANT_MANAGEMENT_PAGE     = "/RestaurantManagement";
	 public static final String ACTIVE_RESTAURATEUR_LIST = "activeRestaurateurList";
    private RestaurantDao restaurantDao;
   
 
   public void init() throws ServletException 
	{
   		this.restaurantDao= ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getRestaurantDao();
   	}
		
	 
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 ArrayList<RestaurantBean> restaurantList= new ArrayList<RestaurantBean>();
		 
		 restaurantList=restaurantDao.getListRestaurantWhoHaveMeals();
		 
		 // request.setAttribute(ACTIVE_RESTAURATEUR_LIST, restaurantList);
		 
		 String xml="";
		 
		 for (RestaurantBean restaurant : restaurantList) {
			
			 //Je cree un xml pour chaque restaurateur et je le parserai dans mon javascript pour cree 
			 //des lignes dans ma liste deroulante
			 
			 xml=xml + "<RESTAURANT>"
							+ "	<ID>"+restaurant.getIdRestaurant()+"</ID>"
							+ "	<NAME>"+restaurant.getName()+"</NAME>"
							+ "</RESTAURANT>";
					
		}
		 
		 response.getWriter().write("<SERVER>"+xml+"</SERVER>");
	 }

}