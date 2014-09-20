package ca.etsmtl.log210.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search extends HttpServlet 
{
	public static final String SEARCH_ACCES     = "/Restrict/Search.jsp";

	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 response.sendRedirect( request.getContextPath() + SEARCH_ACCES);
	 }
	
	
	
}
