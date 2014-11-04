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
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */
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
		int etatRequete=3;
		Map<String, String> returnMessage = new HashMap<String, String>();


		 menuDAO.modifyMenu(
				Integer.parseInt(request.getParameter("updIdM")),
				request.getParameter("updNameM"),
				request.getParameter("updDescM"));


		request.getSession().setAttribute("EtatRequete", etatRequete);
		this.getServletContext().getRequestDispatcher(All_MENU)
				.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
