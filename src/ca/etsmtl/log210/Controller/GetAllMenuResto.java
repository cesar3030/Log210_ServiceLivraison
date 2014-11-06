package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

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
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */
public class GetAllMenuResto  extends HttpServlet {

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
		 
		 String xml="";
		 
			if (request.getParameter("restaurantId") != null) 
			{
				 ID_RESTAURANT_RECEIVED = Integer.parseInt(request.getParameter("restaurantId"));
				 
				 //Creation des liste de donnes de requete
				 ArrayList<MenuBean> activeMenuRestaurantList;
					 
				 
				 //On recupere les donnees qui seront recu avec la requete sql
				 activeMenuRestaurantList = menuDao.showMenuNotEmpty(ID_RESTAURANT_RECEIVED);
				 
				 for(MenuBean menu : activeMenuRestaurantList)
				 {
					 String description=menu.getDescription();
					 
					 if(description=="" || description==null || description.equals(""))
					 {
						 description="Sans description";
					 }
					 
					 xml=xml + "<MENU>"
									+ "	<ID>"+menu.getIdMenu()+"</ID>"
									+ "	<NAME>"+menu.getName()+"</NAME>"
									+ "	<DESCRIPTION>"+description+"</DESCRIPTION>"
									+ "</MENU>";
					 
				 }
				 
				 
			} 
			 
			
			response.getWriter().write("<SERVER>"+xml+"</SERVER>");
		

	 }

	 
}