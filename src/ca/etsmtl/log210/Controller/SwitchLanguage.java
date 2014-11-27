package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.RestaurantDao;

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
//		 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//		 response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		 response.setDateHeader("Expires", 0); // Proxies.
		
		 //this.getServletContext().getRequestDispatcher( CALL_BACK ).forward( request, response );
		 response.sendRedirect(request.getHeader("referer"));
	 }

}
