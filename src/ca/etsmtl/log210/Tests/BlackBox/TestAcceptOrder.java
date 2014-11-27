package ca.etsmtl.log210.Tests.BlackBox;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mockito;

import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.Controller.AcceptOrder;
import ca.etsmtl.log210.Controller.AddNewMenu;
import ca.etsmtl.log210.DAO.MenuManageDao;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class TestAcceptOrder extends TestCase
{
	public void testAcceptOrderNotAcceptedYetPOST()throws Exception
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class); 
		HttpSession session = mock(HttpSession.class);
		Map<String, String>  returnMessage = new HashMap<String, String>();
		UserAccountBean deliveryMan= mock(UserAccountBean.class);
		AcceptOrder target = spy(new AcceptOrder());
		
		doReturn(session).when(request).getSession();
		doReturn("1").when(request).getParameter("idOrder");
		doReturn(deliveryMan).when(session).getAttribute("userSession");
		doReturn(1).when(deliveryMan).getUserId();
		doNothing().when(target).redirection(request, response);
		doReturn(0).when(target).assignOrderToDelileveryMan(1, 1);
		doReturn(returnMessage).when(target).getNewErrorMap();
		
		target.doPost(request, response);
		
		verify(target,times(1)).assignOrderToDelileveryMan(1, 1);
		assertTrue(target.getReturnMessage().containsKey("succes"));
		
		
	}
	
	public void testAcceptOrderAlreadyAcceptedPOST()throws Exception
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class); 
		HttpSession session = mock(HttpSession.class);
		Map<String, String>  returnMessage = new HashMap<String, String>();
		UserAccountBean deliveryMan= mock(UserAccountBean.class);
		AcceptOrder target = spy(new AcceptOrder());
		
		doReturn(session).when(request).getSession();
		doReturn("1").when(request).getParameter("idOrder");
		doReturn(deliveryMan).when(session).getAttribute("userSession");
		doReturn(1).when(deliveryMan).getUserId();
		doNothing().when(target).redirection(request, response);
		doReturn(1).when(target).assignOrderToDelileveryMan(1, 1);
		doReturn(returnMessage).when(target).getNewErrorMap();
		
		target.doPost(request, response);
		
		verify(target,times(1)).assignOrderToDelileveryMan(1, 1);
		assertTrue(target.getReturnMessage().containsKey("warning"));
		
	}
	
	public void testAcceptOrderErrorBdPOST()throws Exception
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class); 
		HttpSession session = mock(HttpSession.class);
		Map<String, String>  returnMessage = new HashMap<String, String>();
		UserAccountBean deliveryMan= mock(UserAccountBean.class);
		AcceptOrder target = spy(new AcceptOrder());
		
		doReturn(session).when(request).getSession();
		doReturn("1").when(request).getParameter("idOrder");
		doReturn(deliveryMan).when(session).getAttribute("userSession");
		doReturn(1).when(deliveryMan).getUserId();
		doNothing().when(target).redirection(request, response);
		doReturn(2).when(target).assignOrderToDelileveryMan(1, 1);
		doReturn(returnMessage).when(target).getNewErrorMap();
		
		target.doPost(request, response);
		
		verify(target,times(1)).assignOrderToDelileveryMan(1, 1);
		assertTrue(target.getReturnMessage().containsKey("fail"));
		
	}
	
	public void testAcceptOrderNotAcceptedYetGET()throws Exception
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class); 
		HttpSession session = mock(HttpSession.class);
		Map<String, String>  returnMessage = new HashMap<String, String>();
		UserAccountBean deliveryMan= mock(UserAccountBean.class);
		AcceptOrder target = spy(new AcceptOrder());
		
		doReturn(session).when(request).getSession();
		doReturn("1").when(request).getParameter("idOrder");
		doReturn(deliveryMan).when(session).getAttribute("userSession");
		doReturn(1).when(deliveryMan).getUserId();
		doNothing().when(target).redirection(request, response);
		doReturn(0).when(target).assignOrderToDelileveryMan(1, 1);
		doReturn(returnMessage).when(target).getNewErrorMap();
		
		target.doGet(request, response);
		
		verify(target,times(1)).assignOrderToDelileveryMan(1, 1);
		assertTrue(target.getReturnMessage().containsKey("succes"));
		
		
	}
	
	public void testAcceptOrderAlreadyAcceptedGET()throws Exception
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class); 
		HttpSession session = mock(HttpSession.class);
		Map<String, String>  returnMessage = new HashMap<String, String>();
		UserAccountBean deliveryMan= mock(UserAccountBean.class);
		AcceptOrder target = spy(new AcceptOrder());
		
		doReturn(session).when(request).getSession();
		doReturn("1").when(request).getParameter("idOrder");
		doReturn(deliveryMan).when(session).getAttribute("userSession");
		doReturn(1).when(deliveryMan).getUserId();
		doNothing().when(target).redirection(request, response);
		doReturn(1).when(target).assignOrderToDelileveryMan(1, 1);
		doReturn(returnMessage).when(target).getNewErrorMap();
		
		target.doGet(request, response);
		
		verify(target,times(1)).assignOrderToDelileveryMan(1, 1);
		assertTrue(target.getReturnMessage().containsKey("warning"));
		
	}
	
	public void testAcceptOrderErrorBdGET()throws Exception
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class); 
		HttpSession session = mock(HttpSession.class);
		Map<String, String>  returnMessage = new HashMap<String, String>();
		UserAccountBean deliveryMan= mock(UserAccountBean.class);
		AcceptOrder target = spy(new AcceptOrder());
		
		doReturn(session).when(request).getSession();
		doReturn("1").when(request).getParameter("idOrder");
		doReturn(deliveryMan).when(session).getAttribute("userSession");
		doReturn(1).when(deliveryMan).getUserId();
		doNothing().when(target).redirection(request, response);
		doReturn(2).when(target).assignOrderToDelileveryMan(1, 1);
		doReturn(returnMessage).when(target).getNewErrorMap();
		
		target.doGet(request, response);
		
		verify(target,times(1)).assignOrderToDelileveryMan(1, 1);
		assertTrue(target.getReturnMessage().containsKey("fail"));
		
	}

}
