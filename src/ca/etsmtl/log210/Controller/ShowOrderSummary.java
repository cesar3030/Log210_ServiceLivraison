package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.MenuBean;

public class ShowOrderSummary  extends HttpServlet {
	
	private static String ORDER="order";
	private static String SUMMARY_PAGE="/Restrict/Client/ShowOrderSummary.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		Map<MealBean, Integer> order= new HashMap<MealBean, Integer>();
		String xml = request.getParameter("cart");
		order=ParseXMLString.parseMeal(xml);
		
		request.setAttribute(ORDER, order);
		 
//		 this.getServletContext().getRequestDispatcher( SUMMARY_PAGE  ).forward( request, response );
		 response.sendRedirect( request.getContextPath() + SUMMARY_PAGE );
	 }
}