package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MenuManageDao;

public class ShowAllMenuResto extends HttpServlet {

	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String MENU_MANAGEMENT_ACCESS     = "/Restrict/Restaurateur/ShowAllMenuResto.jsp";
	 public static final String INACTIVE_MENU_RESTAURANT_ATTRIBUTE = "inactiveMenuRestaurantList";
	 public static final String ACTIVE_MENU_RESTAURANT_ATTRIBUTE = "activeMenuRestaurantList";
	 
	//Instance de menu qui va nous permettre de faire des requetes sur la BD
    private MenuManageDao menuDao;
    
    
    private int idRestauranReceived=1;
   
    
    
   /**
    * Method who is executed the fist time that the servlet is create. Here we get the connection to the DB throw UserAccountDao class.
    * We must get the connection just once if we don't want to have a too many connection error in MySql.
    */
	 public void init() throws ServletException 
	 {
		 	
		 	System.out.println("JE suis dans init de  ShowAllMenuResto");
	    	this.menuDao= ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMenuRestaurantDao();
	 }
	
	 
	 public synchronized void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 System.out.println("JE suis dans doGet de ShowAllMenuResto");
		 ArrayList<MenuBean> activeMenuRestaurantList;
		 ArrayList<MenuBean> inactiveMenuRestaurantList;
		 
		 activeMenuRestaurantList = menuDao.showAllActiveMenuForOneResto(this.idRestauranReceived);
		 inactiveMenuRestaurantList = menuDao.showAllInactiveMenuForOneResto(this.idRestauranReceived);
		 
		
		 
		 request.setAttribute(ACTIVE_MENU_RESTAURANT_ATTRIBUTE,  activeMenuRestaurantList);
		 request.setAttribute(INACTIVE_MENU_RESTAURANT_ATTRIBUTE, inactiveMenuRestaurantList);
		 
		 this.getServletContext().getRequestDispatcher( MENU_MANAGEMENT_ACCESS  ).forward( request, response );
	 }
	 
	
	public synchronized void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 System.out.println("JE suis dans doPost de ShowAllMenuResto");
		 ArrayList<MenuBean>  activeMenuRestaurantList;
		 ArrayList<MenuBean> inactiveMenuRestaurantList;
		 
		 activeMenuRestaurantList = menuDao.showAllActiveMenuForOneResto(this.idRestauranReceived);
		 inactiveMenuRestaurantList = menuDao.showAllInactiveMenuForOneResto(this.idRestauranReceived);
		
		 
		 this.getServletContext().getRequestDispatcher( MENU_MANAGEMENT_ACCESS ).forward( request, response );
	 }
	 
}
