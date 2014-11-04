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
public class DeleteMenu extends HttpServlet {

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
		int etatRequete=4;

		menuDAO.deleteMenu(Integer.parseInt(request
				.getParameter("idMenu")));

		request.getSession().setAttribute("EtatRequete", etatRequete);	
		this.getServletContext().getRequestDispatcher(All_MENU)
				.forward(request, response);
	}

}
