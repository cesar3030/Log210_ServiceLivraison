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
import ca.etsmtl.log210.DAO.UserAccountDao;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */
public class SwitchRestaurateurToVisible extends HttpServlet 
{

	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String RESTAURANT_MANAGEMENT_ACCESS     = "/RestaurateurManagement";
	 public static final String REQUEST_FINISHED_STATE = "returnMessage";
	 
   //The instance of UserAccountDao who give us the possibility to execute requests to the DB about userAccount
   private UserAccountDao userAccountDao;
   
   
  /**
   * Method who is executed the fist time that the servlet is create. Here we get the connection to the DB throw UserAccountDao class.
   * We must get the connection just once if we don't want to have a too many connection error in MySql.
   */
	 public void init() throws ServletException 
	 {
	    	this.userAccountDao= ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserAccountDao();
	 }
	
	 
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 int idRestaurateur=Integer.parseInt( request.getParameter("idRestaurateur"));
		 System.out.println(idRestaurateur);
		 boolean retour = userAccountDao.swtichRestaurateurToVisible(idRestaurateur);
		 
		 Map<String, String>  returnMessage= new HashMap<String, String>();
		 
		 if(retour==true)
		 {
			 returnMessage.put("succes", "Le restaurateur est a nouveau visible");
			 
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
