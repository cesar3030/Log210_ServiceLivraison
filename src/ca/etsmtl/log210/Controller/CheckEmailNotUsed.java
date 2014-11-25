package ca.etsmtl.log210.Controller;

import java.io.IOException;

import javax.servlet.Servlet;
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
public class CheckEmailNotUsed extends HttpServlet implements Servlet
{
	public static final String CONF_DAO_FACTORY = "daofactory";
    
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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
    {
		
    		//Je recupere dans l'url la valeur du champ email
    		String testingEmail = (String) request.getParameter("mail");
    		String resultat;
    		
    		//Je test si cette email existe deja ou non
    		boolean addressAlreadyUsed = userAccountDao.emailAlreadyUsed(testingEmail);
			
		
		
		if (addressAlreadyUsed==true) 
		{
			resultat = "alreadyUsed";
		}
		else
		{
			resultat = "notUsed";
		}		
		response.getWriter().write("<message>"+resultat+"</message>");
	}
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
    {
		
    		//Je recupere dans l'url la valeur du champ email
    		String testingEmail = (String) request.getParameter("mail");
    		String resultat;
    		
    		//Je test si cette email existe deja ou non
    		boolean addressAlreadyUsed = userAccountDao.emailAlreadyUsed(testingEmail);
			
		
		
		if (addressAlreadyUsed==true) 
		{
			resultat = "alreadyUsed";
		}
		else
		{
			resultat = "notUsed";
		}		
		response.getWriter().write("<message>"+resultat+"</message>");
	}
	
	
}
