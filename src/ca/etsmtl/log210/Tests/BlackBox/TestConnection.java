package ca.etsmtl.log210.Tests.BlackBox;

import org.mockito.Mockito;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.Controller.UserConnection;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.UserAccountDao;
import ca.etsmtl.log210.DAO.UserAccountDaoImpl;
import junit.framework.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.*;


public class TestConnection extends TestCase
{
	/**
	 * Test de la conection avec un utilisateur inconnu
	 * @throws Exception
	 */
    public void testConnectionUnknowUser() throws Exception 
	{
    		
		 HttpServletRequest request = mock(HttpServletRequest.class);       
	     HttpServletResponse response = mock(HttpServletResponse.class);    
	     DAOFactory daoF = mock(DAOFactory.class);
	     HttpSession session = mock(HttpSession.class); 
	 	 UserAccountBean userConnected = new UserAccountBean();
	 	 
	 	 UserConnection testTarget = Mockito.spy(new UserConnection());
	 	 
	 	 
	     when(request.getParameter("email")).thenReturn("test@test.ca");
	     when(request.getParameter("password")).thenReturn("password");
	     
	     doReturn(userConnected).when(testTarget).getUserAccount(anyString(),anyString());
	     doReturn(session).when(request).getSession();
	     doNothing().when(testTarget).redirect(request, response);
	     
	     
	     testTarget.doPost(request, response);
	     verify(testTarget).addInErrorMap("unknowUser","L'utilisateur n'est pas connu. Veuillez verifier votre courriel et votre mot de passe.");
	     verify(testTarget,times(1)).redirect(request, response);
	}
    
    /**
	 * Test de la conection avec un utilisateur connu
	 * @throws Exception
	 */
    public void testConnectionKnowUser() throws Exception 
	{
    		
		 HttpServletRequest request = mock(HttpServletRequest.class);       
	     HttpServletResponse response = mock(HttpServletResponse.class);    
	     HttpSession session = mock(HttpSession.class); 
	 	 UserAccountBean userConnected = new UserAccountBean();
	 	 userConnected.setEmail("utilisateurConnu@gmail.com");
	 	 
	 	 UserConnection testTarget = Mockito.spy(new UserConnection());
	 	 
	 	 
	     when(request.getParameter("email")).thenReturn("test@test.ca");
	     when(request.getParameter("password")).thenReturn("password");
	     
	     doReturn(userConnected).when(testTarget).getUserAccount(anyString(),anyString());
	     doReturn(session).when(request).getSession();
	     doNothing().when(testTarget).redirect(request, response);
	     
	     
	     testTarget.doPost(request, response);
	     verify(testTarget).addInErrorMap("succes","L'utilisateur est connecte");
	     verify(testTarget,times(1)).redirect(request, response);
	}

}