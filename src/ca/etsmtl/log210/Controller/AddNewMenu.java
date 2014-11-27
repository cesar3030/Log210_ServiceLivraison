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

import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MenuManageDao;

/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */

/**
 * Classe qui est affiliee au formulaire d'ajout de menu AddNewMenu.jsp Cette
 * classe permet d'ajouter un menu a la BDD Pour ajouter un menu il faut : un
 * nom de menu (obligatoire) une description de menu : (secondaire)
 *
 */

public class AddNewMenu extends HttpServlet {

	private static final String CONF_DAO_FACTORY = "daofactory";
	public static final String SESSION_USER = "userSession";

	public int ID_RESTAURANT_REFERENCE;
	public static final String MENU_MANAGEMENT_ACCESS = "/ShowAllMenuResto";
	public static final String ID_RESTAURANT_SESSION = "idRestaurantSession";
	private MenuManageDao menuDao;
	public MenuBean menuAajouter;
	public int etatRequeter;

	/**
	 * Method who is executed the fist time that the servlet is create. Here we
	 * get the connection to the DB throw UserAccountDao class. We must get the
	 * connection just once if we don't want to have a too many connection error
	 * in MySql.
	 */
	public void init() throws ServletException {

		this.setMenuDao(((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getMenuRestaurantDao());
	}

	public String getServletInfo() {
		return "Servlet AddNewMenu";

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On renvoie la requete de reponse au bon endroit du restrict

	}

	/**
	 * Methode doPost Cette methode permet de recuperer les valeurs inserees
	 * dans le formulaire AddNewMenu afin de les inserer en BDD par utilisation
	 * de la methode this.menuDao.addNewMenu(newMenu);
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// On recupere la session utilisateur
		HttpSession session = getSession(request);
		etatRequeter = 0;
		//System.out.println("Id restaurant session"+(int) session.getAttribute("restaurantActuel"));
		// Creation d un bean menu qui sera ajouter en bdd
		String name= request.getParameter("nameMenu");
		String description = request.getParameter("descriptionMenu");
		int idResto = Integer.parseInt((String) session.getAttribute("restaurantActuel"));
		
		menuAajouter= menuBeanFactory();
		menuAajouter.setname(name);
		menuAajouter.setDescription(description);
		menuAajouter.setIdRestaurant(idResto);

		// On ajoute le menuBean a la BDD
		this.getMenuDao().addNewMenu(this.getMenuAajouter());

		// GERE L'avertissement si la description d un menu n est pas indiquee
		if (this.getMenuAajouter().getDescription() == "") {
			etatRequeter = 2;
		}
		// On affecte l'information de non indication de la description menu a
		// la requete de reponse
		session.setAttribute("EtatRequete", etatRequeter);

		redirection(request, response);
	}

	public MenuBean menuBeanFactory() 
	{
		return new MenuBean();
	}

	public MenuBean getMenuAajouter() {
		return menuAajouter;
	}

	public HttpSession getSession(HttpServletRequest request)
	{
		return request.getSession();
	}

	public void redirection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// On retourne redirige vers la servlet en question /ShowAllMenuResto
		this.getServletContext().getRequestDispatcher(MENU_MANAGEMENT_ACCESS)
				.forward(request, response);
	}

	public int getID_RESTAURANT_REFERENCE() {
		return ID_RESTAURANT_REFERENCE;
	}

	public MenuManageDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuManageDao menuDao) {
		this.menuDao = menuDao;
	}

	public int getEtatRequeter() {
		return etatRequeter;
	}

	public void setEtatRequeter(int etatRequeter) {
		this.etatRequeter = etatRequeter;
	}


}
