package ca.etsmtl.log210.Tests.BlackBox;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mockito;

import sun.invoke.util.VerifyType;
import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.Controller.AddNewMenu;
import ca.etsmtl.log210.Controller.UserConnection;
import junit.framework.TestCase;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
public class TestAddNewMenu extends TestCase {
	
	
	public void testAddNewMenuOK()throws Exception{
		
		 HttpServletRequest request = mock(HttpServletRequest.class);
		 HttpServletResponse response = mock(HttpServletResponse.class); 
		 HttpSession session = mock(HttpSession.class); 
		 
		 Mockito.when(request.getParameter("nomMenu")).thenReturn("testMenu");
		 Mockito.when(request.getParameter("descriptionMenu")).thenReturn("descriptionTest");
		 Mockito.when(session.getAttribute("restaurantActuel")).thenReturn("1");
	 	
	 	 System.out.println(request.getParameter("nomMenu"));
	 	 System.out.println(request.getParameter("descriptionMenu"));
	 	 System.out.println(session.getAttribute("restaurantActuel"));
	 	 
	 	 AddNewMenu testTargetAddMenu = Mockito.spy(new AddNewMenu());
	 	 
	 	 
	 	 
	 	 doNothing().when(testTargetAddMenu).redirection(request, response);
	 	 doReturn(1).when(testTargetAddMenu).getID_RESTAURANT_REFERENCE();
	 	 
	 	 
	 	 testTargetAddMenu.doPost(request, response);
	 	 
	 	 verify(testTargetAddMenu, times(1)).getMenuAajouter();
	 	 verify(testTargetAddMenu, times(1)).getMenuDao();
	 	 verify(testTargetAddMenu, times(1)).redirection(request, response);
	 	 
	}
	
	

}
