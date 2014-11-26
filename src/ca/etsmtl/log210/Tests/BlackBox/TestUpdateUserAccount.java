package ca.etsmtl.log210.Tests.BlackBox;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

import javax.servlet.http.*;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.Controller.UpdateUserAccount;

public class TestUpdateUserAccount extends TestCase
{
	public void testUpdateUserAccountFail() throws Exception 
	{
		UpdateUserAccount target = spy(new UpdateUserAccount());
		UserAccountBean user = mock(UserAccountBean.class);
		 HttpServletRequest request = mock(HttpServletRequest.class);       
	     HttpServletResponse response = mock(HttpServletResponse.class);    
	     HttpSession session = mock(HttpSession.class); 
	     Map<String,String> errors = new HashMap<String, String>();
		
		doReturn(user).when(target).userAccountBeanFactory();
		doReturn(session).when(request).getSession();
		doReturn(user).when(session).getAttribute("userSession");
		
		when(user.getEmail()).thenReturn("test@test.ca");
		doReturn("passwordpassword").when(target).getPassword(request);
		when(request.getParameter("phone")).thenReturn("0000000000");
		when(request.getParameter("adress")).thenReturn("adresse");
		when(user.getUserId()).thenReturn(1);
		
		doReturn(errors).when(user).verifyValidityOfDatas("test@test.ca", "passwordpassword");
		
		//doReturn(true).when(errors).isEmpty();
		
		doReturn(0).when(target).modifyUserAccount(user);
		
		doNothing().when(target).redirect(request, response);
		
		//J'execute la methode que je dois tester
		target.doPost(request, response);
		
		verify(target,times(1)).modifyUserAccount(user);
		verify(target,times(1)).redirect(request, response);
		assertTrue(target.getErrors().containsKey("UpdateFail"));
	}
	
	public void testUpdateUserAccountSucces() throws Exception 
	{
		UpdateUserAccount target = spy(new UpdateUserAccount());
		UserAccountBean user = mock(UserAccountBean.class);
		 HttpServletRequest request = mock(HttpServletRequest.class);       
	     HttpServletResponse response = mock(HttpServletResponse.class);    
	     HttpSession session = mock(HttpSession.class); 
	     Map<String,String> errors = new HashMap<String, String>();
		
		doReturn(user).when(target).userAccountBeanFactory();
		doReturn(session).when(request).getSession();
		doReturn(user).when(session).getAttribute("userSession");
		
		when(user.getEmail()).thenReturn("test@test.ca");
		when(user.getPassword()).thenReturn("passwordpassword");
		when(user.getPhoneNumber()).thenReturn("0000000000");
		when(user.getHomeAddress()).thenReturn("homeaddress");
		doReturn("passwordpassword").when(target).getPassword(request);
		when(request.getParameter("phone")).thenReturn("0000000000");
		when(request.getParameter("adress")).thenReturn("adresse");
		when(user.getUserId()).thenReturn(1);
		
		doReturn(errors).when(user).verifyValidityOfDatas("test@test.ca", "passwordpassword");
		
		//doReturn(true).when(errors).isEmpty();
		
		doReturn(1).when(target).modifyUserAccount(user);
		
		doNothing().when(target).redirect(request, response);
		
		//J'execute la methode que je dois tester
		target.doPost(request, response);
		
		verify(target,times(1)).modifyUserAccount(user);
		verify(target,times(1)).redirect(request, response);
		assertTrue(target.getErrors().containsKey("UpdateCompleted"));
	}
	
	public void testUpdateUserErreurMailOrPassword() throws Exception 
	{
		UpdateUserAccount target = spy(new UpdateUserAccount());
		UserAccountBean user = mock(UserAccountBean.class);
		 HttpServletRequest request = mock(HttpServletRequest.class);       
	     HttpServletResponse response = mock(HttpServletResponse.class);    
	     HttpSession session = mock(HttpSession.class); 
	     Map<String,String> errors = new HashMap<String, String>();
	     errors.put("error","mail ou password");
		
		doReturn(user).when(target).userAccountBeanFactory();
		doReturn(session).when(request).getSession();
		doReturn(user).when(session).getAttribute("userSession");
		
		when(user.getEmail()).thenReturn("test@test.ca");
		when(user.getPassword()).thenReturn("passwordpassword");
		when(user.getPhoneNumber()).thenReturn("0000000000");
		when(user.getHomeAddress()).thenReturn("homeaddress");
		doReturn("passwordpassword").when(target).getPassword(request);
		when(request.getParameter("phone")).thenReturn("0000000000");
		when(request.getParameter("adress")).thenReturn("adresse");
		when(user.getUserId()).thenReturn(1);
		
		doReturn(errors).when(user).verifyValidityOfDatas("test@test.ca", "passwordpassword");
		
		//doReturn(true).when(errors).isEmpty();
		
		doReturn(1).when(target).modifyUserAccount(user);
		
		doNothing().when(target).redirect(request, response);
		
		//J'execute la methode que je dois tester
		target.doPost(request, response);
		
		verify(target,never()).modifyUserAccount(user);
		verify(target,times(1)).redirect(request, response);
		assertTrue(target.getErrors().containsKey("error"));
	}


}
