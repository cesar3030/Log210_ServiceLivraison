package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
public class NewRestaurant extends HttpServlet
{

	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String RESTAURANT_MANAGEMENT_PAGE     = "/RestaurantManagement";
	 public static final String REQUEST_FINISHED_STATE = "returnMessage";
	 
	 
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
	
	 
	 public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 int idLinkedRestaurateur = Integer.parseInt(request.getParameter("restaurateurId"));
		 
		 RestaurantBean restaurantToAdd = new RestaurantBean();
		 
		 restaurantToAdd.setIdUserAccountRestaurateur( idLinkedRestaurateur);
		 restaurantToAdd.setName(request.getParameter("name"));
		 restaurantToAdd.setAddress(request.getParameter("address"));
		 restaurantToAdd.setPhoneNumber(request.getParameter("phone"));
		 restaurantToAdd.setKindOfFood(request.getParameter("typeFood"));
		 
		 boolean insertReturn;
		 
		 insertReturn = restaurantDao.addNewRestaurant(restaurantToAdd);
		 
		Map<String, String>  returnMessage= new HashMap<String, String>();
		 
		 if(insertReturn==true)
		 {
			 /*Si l'idientifiant du restaurateur est 0, cela veut dire que 
			  *c'est l'utilisateur par defaut qui est utilisé et non pas un restaurateur réel
			  */
			 if( idLinkedRestaurateur>0)
			 {
				 returnMessage.put("succes","Le restaurant a ete ajoute avec succes !");
			 }
			 else
			 {
				 returnMessage.put("warning","Le restaurant a ete ajoute mais sans restaurateur");
			 }
		 }
		 else
		 {
			 returnMessage.put("fail","Une erreur est survenue, le restaurant n'a pas pu etre ajoute. Veuillez reessayer.");
		 }
		 
		 request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
		 
		 this.getServletContext().getRequestDispatcher(RESTAURANT_MANAGEMENT_PAGE).forward( request, response );
	 }
}
