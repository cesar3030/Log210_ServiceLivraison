package ca.etsmtl.log210.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAccount extends HttpServlet 
{
	public static final String MY_ACCOUNT_ACCES     = "/Restrict/Client/MyAccount.jsp";

	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 response.sendRedirect( request.getContextPath() + MY_ACCOUNT_ACCES);
	 }
	
	
	
}
