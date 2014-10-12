package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MenuManageDao;

public class AddNewMenu extends HttpServlet {

	 private static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String SESSION_USER = "userSession";
	 //public String MENU_MANAGEMENT_ACCESS;
	 public int ID_RESTAURANT_REFERENCE;
	 public static final String MENU_MANAGEMENT_ACCESS     = "/ShowAllMenuResto";
	 public static final String ID_RESTAURANT_SESSION = "idRestaurantSession";
	 private MenuManageDao menuDao;


	/**
	    * Method who is executed the fist time that the servlet is create. Here we get the connection to the DB throw UserAccountDao class.
	    * We must get the connection just once if we don't want to have a too many connection error in MySql.
	    */
		 public void init() throws ServletException 
		 {
			 	
			 	System.out.println("JE suis dans init de  ShowAllMenuResto");
		    	this.menuDao= ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMenuRestaurantDao();
		 }
		 public String getServletInfo(){
			return "Servlet AddNewMenu";
			 
		 }
		
		 
		 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
		 {
						 			 
			 //On renvoie la requete de reponse au bon endroit du restrict
			 
		 }
		 
		
		public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
		 {
			HttpSession session = request.getSession();
			System.out.println(session.getAttribute("restaurantActuel"));
			session.setAttribute("insertion", true);
			//ID_RESTAURANT_REFERENCE = (int) session.getAttribute("restaurantActuel");
			
			MenuBean newMenu = new MenuBean();
			newMenu.setname((String) request.getParameter("nameMenu"));
			newMenu.setDescription((String) request.getParameter("descriptionMenu"));
			newMenu.setIdRestaurant((int)session.getAttribute("restaurantActuel"));
			
			if (this.menuDao.addNewMenu(newMenu)){
				System.out.println("Je vais renvoyer la requete vers "+ MENU_MANAGEMENT_ACCESS);
				this.getServletContext().getRequestDispatcher( MENU_MANAGEMENT_ACCESS  ).forward( request, response );
			}
			
			
			
		 }
	
	
}
