package ca.etsmtl.log210.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SwitchLanguage extends HttpServlet 
{

	public static final String CALL_BACK     = "/OrdersNeededToBeDelivered";
	
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		 HttpSession session = request.getSession();
		 
		 if(session.getAttribute("langue")=="fr")
		 {
			 session.setAttribute("langue","en");
		 }
		 else
		 {
			 session.setAttribute("langue","fr");
		 }
		 
		 response.sendRedirect(request.getHeader("referer"));
	 }

}
