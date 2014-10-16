package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Beans.RestaurantBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MenuManageDao;
import ca.etsmtl.log210.DAO.RestaurantDao;

public class ShowAllMenuResto extends HttpServlet {

	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String MENU_MANAGEMENT_ACCESS     = "/ShowAllMenuResto";
	 public static final String INACTIVE_MENU_RESTAURANT_ATTRIBUTE = "inactiveMenuRestaurantList";
	 public static final String ACTIVE_MENU_RESTAURANT_ATTRIBUTE = "activeMenuRestaurantList";
	
	 
	 public int ID_RESTAURANT_RECEIVED;// A CHANGER PAS LA SUITE
	 
	 public String RESTAURANT_NAME_TITRE = "restaurantTitreName" ;
	 public String ID_RESTAURANT_REFERENCE = "idRestaurantReference" ;
	 public RestaurantBean PRESENT_RESTAURANT;
	 
	//Instance de menu qui va nous permettre de faire des requetes sur la BD
    private MenuManageDao menuDao;
    private RestaurantDao restaurantDao;
    
     
    
   /**
    * Method who is executed the fist time that the servlet is create. Here we get the connection to the DB throw UserAccountDao class.
    * We must get the connection just once if we don't want to have a too many connection error in MySql.
    */
	 public void init() throws ServletException 
	 {
		   	this.menuDao= ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMenuRestaurantDao();
	    	this.restaurantDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getRestaurantDao();
	 }
	 public String getServletInfo(){
		return "Servlet ShowAllMenuResto";
		 
	 }
	
	 
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 
		 HttpSession session = request.getSession();

			if (request.getParameter("idRestaurant") != null) {
				 ID_RESTAURANT_RECEIVED = Integer.parseInt(request.getParameter("idRestaurant"));
				 session.setAttribute("restaurantActuel",ID_RESTAURANT_RECEIVED);
			} else {
			
				ID_RESTAURANT_RECEIVED = (int)session
						.getAttribute("restaurantActuel");
				}
	
		 PRESENT_RESTAURANT = restaurantDao.getNomRestaurant(ID_RESTAURANT_RECEIVED);
		 
		 //Creation des liste de donnes de requete
		 ArrayList<MenuBean> activeMenuRestaurantList;
			 
		 
		 //On recupere les donnees qui seront recu avec la requete sql
		 activeMenuRestaurantList = menuDao.showAllActiveMenuForOneResto(ID_RESTAURANT_RECEIVED);
		
		 
		 session.setAttribute("retourInt",2);
		
		 //AJOUT DES ELEMENTS A LA REQUETE DE REPONSE
		 request.setAttribute(ACTIVE_MENU_RESTAURANT_ATTRIBUTE,  activeMenuRestaurantList);
		

		 request.setAttribute(RESTAURANT_NAME_TITRE, PRESENT_RESTAURANT.getName());
		 
		 //On renvoie la requete de reponse au bon endroit du restrict
		 this.getServletContext().getRequestDispatcher( "/Restrict/Restaurateur/ShowAllMenuResto.jsp" ).forward( request, response );
	 }

	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 
		this.doGet(request, response); 
		
	 }
	 
}
