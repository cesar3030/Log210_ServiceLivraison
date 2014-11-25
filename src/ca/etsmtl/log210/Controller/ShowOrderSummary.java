package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import ca.etsmtl.log210.Beans.AddressBean;
import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.AddressDao;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;

/**
 * Aissou Idriss Jeanroy Cesar Murat David
 */

/**
 * Classe ShowOrderSummary qui permet d afficher un resume detaille d'un commande
 * faite par un client avant le paiement
 *
 */
public class ShowOrderSummary extends HttpServlet {

	private static String ORDER = "order";
	// private static String
	// SUMMARY_PAGE="/Restrict/Client/ShowOrderSummary.jsp";
	private static String SUMMARY_PAGE = "/Restrict/Client/NewFile.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String SESSION_USER = "userSession";
	public static final String ATTRIBUTE_ADDRESS = "addressUser";

	// Instance de plat qui va nous permettre de faire des requetes sur la BD
	private MealDao mealDao;
	private AddressDao addressDao;

	public void init() throws ServletException {

		this.mealDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getMealMenuDao();
		this.addressDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getAddressDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// On recupre la variable de session
		HttpSession session = request.getSession();

		OrderBean order = new OrderBean();

		UserAccountBean client = null;
		// On recupere le client qui est connecte
		client = (UserAccountBean) session.getAttribute(SESSION_USER);

		String xml = request.getParameter("cart");

		order.setOrderItemsList(ParseXMLString.parseMeal(xml, mealDao));

		// On cree un arraylist pour stocker les adresses de livraison de
		// l'utilisateur
		ArrayList<AddressBean> listAddressOfClient = new ArrayList<AddressBean>();

		listAddressOfClient = addressDao
				.getAllAddressOfUser(client.getUserId());
		System.out.println(listAddressOfClient.size());

		session.setAttribute(ATTRIBUTE_ADDRESS, listAddressOfClient);

		session.setAttribute(ORDER, order);
		session.setAttribute("test", "ehediebciebc");
		// this.getServletContext().getRequestDispatcher( SUMMARY_PAGE
		// ).forward( request, response );
		response.sendRedirect(request.getContextPath()
				+ "/Restrict/Client/NewFile.jsp");
	}
}