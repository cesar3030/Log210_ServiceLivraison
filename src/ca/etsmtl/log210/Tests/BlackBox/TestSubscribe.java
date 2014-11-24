package ca.etsmtl.log210.Tests.BlackBox;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.Controller.Subscribe;
import junit.framework.*;
import static org.mockito.Mockito.*;

public class TestSubscribe extends TestCase {
	
	/**
	 * Test de la creation d'un compte
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	public void testSubscriptionNewuser() throws IOException, ServletException
	{
		 HttpServletRequest request = mock(HttpServletRequest.class);       
	     HttpServletResponse response = mock(HttpServletResponse.class);    
	     UserAccountBean user = mock(UserAccountBean.class);
	     Subscribe target = spy(new Subscribe());
	     
	     when(request.getParameter("name")).thenReturn(anyString());
	     when(request.getParameter("firstname")).thenReturn(anyString());
	     when(request.getParameter("password1")).thenReturn(anyString());
	     when(request.getParameter("birthday")).thenReturn(anyString());
	     when(request.getParameter("adress")).thenReturn(anyString());
	     when(request.getParameter("phone")).thenReturn("0000000000");
	     
	     doNothing().when(target).sendConfirmationTextMessage(request, response);
	     doNothing().when(target).addNewUserInDB(user);
	     doReturn(user).when(target).userAccountBeanFabrique();
	     doNothing().when(target).redirect(request, response);
	     
	     target.doPost(request, response);
	     
	     verify(target, times(1)).addInErrorMap("NewUserCompleted","Votre compte a bien ete cree correctement");
	     
	     
	}
}
