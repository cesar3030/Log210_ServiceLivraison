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
import ca.etsmtl.log210.Utils.ExpressLivraisonSms;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */
public class Subscribe extends HttpServlet {

	public static final String FORM = "/connection.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ERRORS_FORM = "form";
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

		// cette ma va nous servir a socker le resultat du test
		// de la validitedu format de l'email et du mot de
		// passe
		Map<String, String> errors=new HashMap<String, String>();

		// On cre un userAccountBean pour stocker les valeurs rentrees dans
		// le formulaire.

		UserAccountBean newUser = new UserAccountBean();

		request.setCharacterEncoding("UTF-8");

		// On set newUser les valeurs rentrees dans le formulaire

		newUser.setName(request.getParameter("name"));
		newUser.setFirstName(request.getParameter("firstname"));
		newUser.setEmail(request.getParameter("email"));
		newUser.setPassword(request.getParameter("password1"));
		newUser.setBirthdayDate(request.getParameter("birthday"));
		newUser.setHomeAddress(request.getParameter("adress"));
		newUser.setPhoneNumber(request.getParameter("phone"));
		newUser.setUserRights(0);
		
		
		//ENVOIE DU SMS DE CONFIRMATION D'INSCRITPION
		ExpressLivraisonSms sms = new ExpressLivraisonSms();
		sms.envoyerSmsConfirmationInsciptionAuSite(request.getParameter("phone"), request.getParameter("adress"));
		
		
		System.out.println(request.getParameter("name"));
		// On ajoute le nouvel utilisateur dans la BDD
		userAccountDao.newUserAccount(newUser);

		errors.put("NewUserCompleted",
				"Votre compte a bien ete cree correctement");
		System.out.println(errors);
		request.setAttribute(ERRORS_FORM, errors);

		// On retourne sur la page de connection pour que l'utilisateur puisse
		// se connecter
		this.getServletContext().getRequestDispatcher(FORM)
				.forward(request, response);

	}
}
