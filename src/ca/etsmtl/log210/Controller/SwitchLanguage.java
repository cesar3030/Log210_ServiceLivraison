package ca.etsmtl.log210.Controller;

import java.io.IOException;

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
		 System.out.println("ici");
		
		 ServletContext context= getServletContext();
		 RequestDispatcher rd= context.getRequestDispatcher(CALL_BACK);
		 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		 response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		 response.setDateHeader("Expires", 0); // Proxies.
		 rd.forward(request, response);
	 
	 }

}
