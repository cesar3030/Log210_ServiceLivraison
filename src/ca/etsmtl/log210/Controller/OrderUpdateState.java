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
import ca.etsmtl.log210.DAO.OrderDao;
import ca.etsmtl.log210.DAO.RestaurantDao;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */
public class OrderUpdateState extends HttpServlet{

	public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String RESTAURANT_ORDER_MANAGEMENT_ACCESS     = "/ShowOrderRestaurant";
	 public static final String REQUEST_FINISHED_STATE = "returnMessage";
	 public static final String ETAT_REQUETE = "etat";
	 
  //The instance of UserAccountDao who give us the possibility to execute requests to the DB about userAccount
  private OrderDao orderDao;
  
  
 /**
  * Method who is executed the fist time that the servlet is create. Here we get the connection to the DB throw UserAccountDao class.
  * We must get the connection just once if we don't want to have a too many connection error in MySql.
  */
	 public void init() throws ServletException 
	 {
	    	this.orderDao= ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getOrderDao();
	 }
	
	 
	 public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 //Je recupere les valeurs du formulaire de modification d<un restaurant
		 int idOrderRecu=Integer.parseInt( request.getParameter("idOrder"));
		 int status = Integer.parseInt( request.getParameter("status"));
			 
		 /**Choix de l'update selon le statut de la commande
		  * 0 = A pr�parer --> doit devenir 1 = En preparation
		  * 1 = En pr�paration --> doit devenir 2 =  Faite
		  */
		 
		 request.setAttribute(ETAT_REQUETE, orderDao.updateOrderState(idOrderRecu, status));
		 
		 this.getServletContext().getRequestDispatcher( RESTAURANT_ORDER_MANAGEMENT_ACCESS ).forward( request, response );
	 }
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 //Je recupere les valeurs du formulaire de modification d<un restaurant
		 int idOrderRecu=Integer.parseInt( request.getParameter("idOrder"));
		 int status = Integer.parseInt( request.getParameter("status"));
			 
		 /**Choix de l'update selon le statut de la commande
		  * 0 = A pr�parer --> doit devenir 1 = En preparation
		  * 1 = En pr�paration --> doit devenir 2 =  Faite
		  */
		 
		 request.setAttribute(ETAT_REQUETE, orderDao.updateOrderState(idOrderRecu, status));
		 
		 this.getServletContext().getRequestDispatcher( RESTAURANT_ORDER_MANAGEMENT_ACCESS ).forward( request, response );
	 }
	
	
	
}
