package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.UserAccountDao;

public class NewRestaurateur extends HttpServlet {

	public static final String REDIRECTION_PAGE = "/RestaurateurManagement";
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	 
	// The instance of UserAccountDao who give us the possibility to execute
	// requests to the DB about userAccount
	private UserAccountDao userAccountDao;

	/**
	 * Method who is executed the fist time that the servlet is create. Here we
	 * get the connection to the DB throw UserAccountDao class. We must get the
	 * connection just once if we don't want to have a too many connection error
	 * in MySql.
	 */
	public void init() throws ServletException {
		this.userAccountDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getUserAccountDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// cette ma va nous servir a socker le r������������������sultat du test
		// de la validit������������������ du format de l'email et du mot de
		// passe
		Map<String, String> errors=new HashMap<String, String>();

		// On cr���� un userAccountBean pour stocker les valeurs rentr��es dans
		// le
		// formulaire.

		UserAccountBean newUser = new UserAccountBean();

		request.setCharacterEncoding("UTF-8");

		// On set newUser les valeurs rentr��es dans le formulaire ok nickel.

		newUser.setName(request.getParameter("name"));
		newUser.setFirstName(request.getParameter("firstname"));
		newUser.setEmail(request.getParameter("email"));
		newUser.setPassword(request.getParameter("password1"));
		newUser.setBirthdayDate(request.getParameter("birthday"));
		newUser.setHomeAddress(request.getParameter("adress"));
		newUser.setPhoneNumber(request.getParameter("phone"));
		
		//On set le droit 1 car c'est un utilisateur de type restaurateur
		newUser.setUserRights(1);

		// On ajoute le nouvel utilisateur dans la BDD
		userAccountDao.newUserAccount(newUser);

		errors.put("succes",
				"Un compte a ete cree pour "+newUser.getFirstName()+" "+newUser.getName());
		
		request.setAttribute(REQUEST_FINISHED_STATE, errors);

		// On retourne sur la page de connection pour que l'utilisateur puisse
		// se connecter

		this.getServletContext().getRequestDispatcher(REDIRECTION_PAGE).forward( request, response );

	}
}

