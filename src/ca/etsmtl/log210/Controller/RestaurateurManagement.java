package ca.etsmtl.log210.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestaurateurManagement extends HttpServlet 
{

	 public static final String RESTAURATEUR_MANAGEMENT_ACCES     = "/Restrict/Admin/RestaurateurManagement.jsp";
	
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 response.sendRedirect( request.getContextPath() + RESTAURATEUR_MANAGEMENT_ACCES );
	 }
	
	
}
