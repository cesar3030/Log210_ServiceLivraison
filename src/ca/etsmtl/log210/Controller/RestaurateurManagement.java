package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.UserAccountDao;

public class RestaurateurManagement extends HttpServlet 
{

	 public static final String RESTAURATEUR_MANAGEMENT_ACCES     = "/Restrict/Admin/RestaurateurManagement.jsp";
	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String ACTIVE_RESTAURATEUR_LIST = "activeRestaurateurList";
	 public static final String INACTIVE_RESTAURATEUR_LIST = "inactiveRestaurateurList";
	 
	 
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
		 ArrayList<UserAccountBean> activeRestaurateurList= new ArrayList<UserAccountBean>();
		 ArrayList<UserAccountBean> inactiveRestaurateurList= new ArrayList<UserAccountBean>();
		 
		 activeRestaurateurList=userAccountDao.getListActiveRestaurateur();
		
		 inactiveRestaurateurList=userAccountDao.getListInactiveRestaurateur();
		 
		 request.setAttribute(ACTIVE_RESTAURATEUR_LIST, activeRestaurateurList);
		 request.setAttribute(INACTIVE_RESTAURATEUR_LIST,inactiveRestaurateurList);
		 

		 this.getServletContext().getRequestDispatcher( RESTAURATEUR_MANAGEMENT_ACCES  ).forward( request, response );
	 }
	 
	 public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 ArrayList<UserAccountBean> activeRestaurateurList= new ArrayList<UserAccountBean>();
		 ArrayList<UserAccountBean> inactiveRestaurateurList= new ArrayList<UserAccountBean>();
		 
		 activeRestaurateurList=userAccountDao.getListActiveRestaurateur();
		
		 // inactiveRestaurateurList
		 
		 request.setAttribute(ACTIVE_RESTAURATEUR_LIST, activeRestaurateurList);
		 request.setAttribute(INACTIVE_RESTAURATEUR_LIST,inactiveRestaurateurList);
		 

		 this.getServletContext().getRequestDispatcher( RESTAURATEUR_MANAGEMENT_ACCES  ).forward( request, response );
	 }

}

	
	

