package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
public class UpdateRestaurant extends HttpServlet 
{

	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String RESTAURANT_MANAGEMENT_ACCESS     = "/RestaurantManagement";
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
		 //Je recupere les valeurs du formulaire de modification d<un restaurant
		 int idRestaurant=Integer.parseInt( request.getParameter("updateIdRestaurant"));
		 String name=request.getParameter("updateName");
		 int idRestaurateur=Integer.parseInt( request.getParameter("updateRestaurateurId"));
		 String typeOfFood=request.getParameter("updateTypeFood");
		 String phone=request.getParameter("updatePhone");
		 String address=request.getParameter("updateAddress");
		
		 //Je cree un nouveau bean restaurant pour y stocker les valeures saisies dans le formulaire
		 RestaurantBean restaurant= new RestaurantBean();
		 
		 //Je set les valeurs du formulaire de modification
		 restaurant.setIdRestaurant(idRestaurant);
		 restaurant.setName(name);
		 restaurant.setIdUserAccountRestaurateur(idRestaurateur);
		 restaurant.setKindOfFood(typeOfFood);
		 restaurant.setPhoneNumber(phone);
		 restaurant.setAddress(address);
		 
		 System.out.println(restaurant.toString());
		 
		 //J'effectue l'update en bb
		 boolean retour = restaurantDao.updateResataurant(restaurant);
		 
		 Map<String, String>  returnMessage= new HashMap<String, String>();
		 
		 if(retour==true)
		 {
			 returnMessage.put("succes", "Les informations du restaurant "+name+"ont bien ete modifiees");
			 
			 request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
			 
			 ServletContext context= getServletContext();
			 RequestDispatcher rd= context.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS);
			 rd.forward(request, response);
		 }
		 else if(retour==false)
		 {
			 returnMessage.put("fail", "Une erreur est survenu, veuillez reessayer.");
			 
			 request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
			 
			 ServletContext context= getServletContext();
			 RequestDispatcher rd= context.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS);
			 rd.forward(request, response);
			
		 }
		 
		 
	 }
}
