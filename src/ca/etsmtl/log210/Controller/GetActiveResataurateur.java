package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.RestaurantDao;
import ca.etsmtl.log210.DAO.UserAccountDao;

public class GetActiveResataurateur extends HttpServlet
{

	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String RESTAURANT_MANAGEMENT_PAGE     = "/RestaurantManagement";
	 public static final String ACTIVE_RESTAURATEUR_LIST = "activeRestaurateurList";
	 
	 
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
		 ArrayList<UserAccountBean> restaurateurList= new ArrayList<UserAccountBean>();
		 
		 restaurateurList=userAccountDao.getListActiveRestaurateur();
		 
		 request.setAttribute(ACTIVE_RESTAURATEUR_LIST, restaurateurList);
		 
		 String xml="";
		 
		 for (UserAccountBean restaurateur : restaurateurList) {
			
			 //Je cree un xml pour chaque restaurateur et je le parserai dans mon javascript pour cree 
			 //des lignes dans ma liste deroulante
			 
			 xml=xml + "<RESTAURATEUR>"
							+ "	<ID>"+restaurateur.getUserId()+"</ID>"
							+ "	<NAME>"+restaurateur.getName()+"</NAME>"
							+ "	<FIRSTNAME>"+restaurateur.getFirstName()+"</FIRSTNAME>"
							+ "</RESTAURATEUR>";
					
		}
		 
		 response.getWriter().write("<SERVER>"+xml+"</SERVER>");
	 }

}
