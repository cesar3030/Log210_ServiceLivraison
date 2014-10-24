package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;

public class ShowOrderSummary  extends HttpServlet {
	
	private static String ORDER="order";
	private static String SUMMARY_PAGE="/Restrict/Client/ShowOrderSummary.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";
	// Instance de plat qui va nous permettre de faire des requetes sur la BD
	private MealDao mealDao;
	
	public void init() throws ServletException {

		this.mealDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getMealMenuDao();
	}
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		Map<MealBean, Integer> order= new HashMap<MealBean, Integer>();
		String xml = request.getParameter("cart");
		order=ParseXMLString.parseMeal(xml,mealDao);
		
		
		//On recupre la variable de session
		HttpSession session = request.getSession(); 
		session.setAttribute(ORDER, order);
		
//		 this.getServletContext().getRequestDispatcher( SUMMARY_PAGE  ).forward( request, response );
		 response.sendRedirect( request.getContextPath() + SUMMARY_PAGE );
	 }
}