package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Beans.RestaurantBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MenuManageDao;
import ca.etsmtl.log210.DAO.RestaurantDao;

public class ShowAllMenuResto extends HttpServlet {

	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String MENU_MANAGEMENT_ACCESS     = "/Restrict/Restaurateur/ShowAllMenuResto.jsp";
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
		 	
		 	System.out.println("JE suis dans init de  ShowAllMenuResto");
	    	this.menuDao= ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMenuRestaurantDao();
	    	this.restaurantDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getRestaurantDao();
	 }
	 public String getServletInfo(){
		return "Servlet ShowAllMenuResto";
		 
	 }
	
	 
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 ID_RESTAURANT_RECEIVED = Integer.parseInt(request.getParameter("idRestaurant"));

		 System.out.println("voici l'Id du restaurant " + ID_RESTAURANT_RECEIVED);
		 
		 PRESENT_RESTAURANT = restaurantDao.getNomRestaurant(ID_RESTAURANT_RECEIVED);

				 

		 
		 //Creation des liste de donnes de requete
		 ArrayList<MenuBean> activeMenuRestaurantList;
		 ArrayList<MenuBean> inactiveMenuRestaurantList;
		 
		 
		 //On recupere les donnees qui seront recu avec la requete sql
		 activeMenuRestaurantList = menuDao.showAllActiveMenuForOneResto(ID_RESTAURANT_RECEIVED);
		 inactiveMenuRestaurantList = menuDao.showAllInactiveMenuForOneResto(ID_RESTAURANT_RECEIVED);
		 
		
		 //AJOUT DES ELEMENTS A LA REQUETE DE REPONSE
		 request.setAttribute(ACTIVE_MENU_RESTAURANT_ATTRIBUTE,  activeMenuRestaurantList);
		 request.setAttribute(INACTIVE_MENU_RESTAURANT_ATTRIBUTE, inactiveMenuRestaurantList);
		 request.setAttribute(ID_RESTAURANT_REFERENCE, PRESENT_RESTAURANT.getIdRestaurant());
		 request.setAttribute(RESTAURANT_NAME_TITRE, PRESENT_RESTAURANT.getName());
		 
		 //On renvoie la requete de reponse au bon endroit du restrict
		 this.getServletContext().getRequestDispatcher( MENU_MANAGEMENT_ACCESS  ).forward( request, response );
	 }
	 
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 
		this.doGet(request, response); 
		
	 }
	 
}
