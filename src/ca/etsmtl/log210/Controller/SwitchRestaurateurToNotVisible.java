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

import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.RestaurantDao;
import ca.etsmtl.log210.DAO.UserAccountDao;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */
public class SwitchRestaurateurToNotVisible extends HttpServlet
{
public static final String CONF_DAO_FACTORY = "daofactory";
public static final String RESTAURANT_MANAGEMENT_ACCESS     = "/RestaurateurManagement";
public static final String REQUEST_FINISHED_STATE = "returnMessage";

//The instance of UserAccountDao who give us the possibility to execute requests to the DB about userAccount
private UserAccountDao userAccountDao;
private RestaurantDao restaurantDao;

/**
 *Methode qui va chercher les DAO restaurant et userAccount pour pouvoir interagir avec la BD
 */
public void init() throws ServletException 
{
	this.userAccountDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserAccountDao();
	
	this. restaurantDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getRestaurantDao();
}


public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
{
	 int idRestaurateur=Integer.parseInt( request.getParameter("idRestaurateur"));
	 System.out.println(idRestaurateur);
	 boolean retour = userAccountDao.swtichRestaurateurToNotVisible(idRestaurateur);
	 
	 
	 Map<String, String>  returnMessage= new HashMap<String, String>();
	 
	 //Si le restaurateur est passé en non visible sans erreur, on le unlink de ses restaurants 
	 if(retour==true)
	 {
		 restaurantDao.unlinkARestaurateurHisRestaurants(idRestaurateur);
		 
		 if(retour==true)
		 {
			 returnMessage.put("succes", "Le restaurateur est desormais non visible et tous ses restaurants sont maintenant sans restaurateur");
			 
			 request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
			 
			 ServletContext context= getServletContext();
			 RequestDispatcher rd= context.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS);
			 rd.forward(request, response);
		 }
		 else if(retour==false)
		 {
			 returnMessage.put("warning", "Le restaurateur est desormais non visible mais ses restaurants l'ont toujours comme restaurateur. Une erreur est survenue lors de la modification de ses restaurant.");
			 
			 request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
			 
			 ServletContext context= getServletContext();
			 RequestDispatcher rd= context.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS);
			 rd.forward(request, response);
			
		 }
	 }
	 else
	 {
		 returnMessage.put("fail", "Une erreur est survenu, veuillez reessayer.");
		 
		 request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
		 
		 ServletContext context= getServletContext();
		 RequestDispatcher rd= context.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS);
		 rd.forward(request, response);
		
	 }
	 
	 
}
}
