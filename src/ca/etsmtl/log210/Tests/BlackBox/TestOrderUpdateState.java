package ca.etsmtl.log210.Tests.BlackBox;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.Controller.OrderUpdateState;
import ca.etsmtl.log210.Utils.ExpressLivraisonSms;
import junit.framework.TestCase;

public class TestOrderUpdateState extends TestCase
{
	public void testState0ToState1()throws Exception
	{
		/*
		 * 
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class); 
		HttpSession session = mock(HttpSession.class); 
		OrderBean order = mock(OrderBean.class);
		OrderUpdateState target = spy(new OrderUpdateState());
		UserAccountBean client = mock(UserAccountBean.class);
		ExpressLivraisonSms sms = mock(ExpressLivraisonSms.class);
		
		when(request.getParameter("idOrder")).thenReturn("10");
		when(request.getParameter("status")).thenReturn("0");
		when(client.getPhoneNumber()).thenReturn("0000000000");
		doReturn(session).when(request).getSession();
		
		doReturn(client).when(target).getUserAccount(8);
		doReturn(order).when(target).getOrder(10);
		
		when(client.getEmail()).thenReturn("test@test.ca");
		doReturn("contenu du message").when(target).generateMailContent(order, client,"0");
		//doNothing().when(target).sendEmail(target.getHost(), target.getPort(), target.getUser(),target.getPass(),anyString(),anyString(),anyString());
		doReturn(sms).when(target).expressLivraisonSmsFabrique();
		doNothing().when(sms).envoyerSmsCommandeEnPreparation("0000000000");
		
		doReturn(0).when(target).updateOrderState(10, 0);
		
		target.doGet(request, response);
		
		
		//verify(target,times(1)).sendEmail(target.getHost(), target.getPort(), target.getUser(),target.getPass(),"test@test.ca", "Changement de statut de votre commande chez ExpressLivraison","contenu du message");
		verify(target,times(1)).generateMailContent(order, client,"en preparation");
	*/
	}
}