package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.DAO.DAOFactory;

import ca.etsmtl.log210.DAO.MenuManageDao;

public class ModifyMenu extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3405541725228732771L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String All_MENU = "/ShowAllMenuResto";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";

	private MenuManageDao menuDAO;

	
	public void init() throws ServletException {
		this.menuDAO = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getMenuRestaurantDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean insertReturn;
		
		System.out.println("-----Deb MODIDY MENU ------");

		System.out.println("Voici l'ID du MENU en question : "
				+ request.getParameter("updNameM"));
		
		System.out.println(request.getParameterNames());
		
		System.out.println("Voici e nom du MENU en question : "
				+ request.getParameter("updDescM"));
		
		System.out.println("Voici la description du MENU en question : "
				+ request.getParameter("updIdM"));

		insertReturn = menuDAO.modifyMenu(
				Integer.parseInt(request.getParameter("updIdM")),
				request.getParameter("updNameM"),
				request.getParameter("updDescM"));

		Map<String, String> returnMessage = new HashMap<String, String>();
	
		
		if (insertReturn == true) {
			returnMessage.put("succes", "SUPRESSION SUCCES!");
		} else {
			returnMessage
					.put("fail",
							"Impossible a supprimer. Veuillez reessayer.");
		}

		System.out.println("-----FIN DELETE MENU FIN------");
		this.getServletContext().getRequestDispatcher(All_MENU)
				.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean insertReturn;
		
		System.out.println("-----Deb MODIDY MENU ------");

		System.out.println("Voici l'ID du MENU en question : "
				+ request.getParameter("idMenu"));
		
		System.out.println("Voici e nom du MENU en question : "
				+ request.getParameter("nameMenu"));
		
		System.out.println("Voici la description du MENU en question : "
				+ request.getParameter("descriptionMenu"));

		insertReturn = menuDAO.modifyMenu(
				Integer.parseInt(request.getParameter("idMenu")),
				request.getParameter("nameMenu"),
				request.getParameter("descriptionMenu"));

		Map<String, String> returnMessage = new HashMap<String, String>();
	
		
		if (insertReturn == true) {
			returnMessage.put("succes", "SUPRESSION SUCCES!");
		} else {
			returnMessage
					.put("fail",
							"Impossible a supprimer. Veuillez reessayer.");
		}

		System.out.println("-----FIN DELETE MENU FIN------");
		this.getServletContext().getRequestDispatcher(All_MENU)
				.forward(request, response);
	}


}
