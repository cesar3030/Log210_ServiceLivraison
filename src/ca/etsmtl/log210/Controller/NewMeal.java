package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;

/**
 * Aissou Idriss Jeanroy Cesar Murat David Permet d'ajouter un plat dans un menu
 */
public class NewMeal extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3405541725228732771L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String MEAL_MENU = "/ShowAllMealMenu";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	public static final String ID_MENU_REQUETE = "idMenuSession";
	public int idMenu;
	Map<String, String> returnMessage;
	private MealBean mealToAdd;
	private MealDao mealDAO;
	private int etatDescription;

	/**
	 * Method who is executed the fist time that the servlet is create. Here we
	 * get the connection to the DB throw UserAccountDao class. We must get the
	 * connection just once if we don't want to have a too many connection error
	 * in MySql.
	 */
	public void init() throws ServletException {
		this.mealDAO = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getMealDao();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		/* Recuperation de la session depuis la requete */
		HttpSession session = this.getSession(request);

		// Creation d'un Bean de plat 
		// On remplit le Bean;
		this.setMealBean(Integer.parseInt((String) session
				.getAttribute("idMenuSession")),
				request.getParameter("name"),
				Integer.parseInt(request.getParameter("price")),
				request.getParameter("description"));
		

		boolean insertReturn;

		// On envoie le bean a  inserer au controlleur qui fait le lien avec la
		// BDD
		insertReturn = this.getMealDao().addNewMeal(this.getMealBean());

		this.setMapError();
		
		if (insertReturn == true) {
			returnMessage.put("succes", "Le plat a ete ajoute avec succes !");
		} else {
			returnMessage
					.put("fail",
							"Une erreur est survenue, le plat n'a pas pu etre ajoute. Veuillez reessayer.");
		}

		request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);


		testDescriptionMissing(request,session);

		this.redirection(request, response);

	}
	
	/**
	 * Methode qui permet de creer un meal bean puis en affectants aux attribut du bean 
	 * les parametres recus lors de l appel de la methode 
	 * @param idMenu
	 * @param name
	 * @param prix
	 * @param description
	 */
	public void setMealBean(int idMenu, String name, int prix, String description){
		mealToAdd = new MealBean();
		mealToAdd.setIdMenu(idMenu);
		mealToAdd.setName(name);
		mealToAdd.setPrice(prix);
		mealToAdd.setDescription(description);
	}
	
	/**
	 * Methode qui permet de retourner le bean qui sera inserer en BDD
	 * @return MealBean
	 */
	public MealBean getMealBean(){
		return this.mealToAdd;
	}
	
	
	/**
	 * Methode qui permet de rediriger vers la page servlet de notre choix
	 * En l'occurence ici nous renvoyons vers /ShowAllMealMenu
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void redirection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// On retourne redirige vers la servlet en question /ShowAllMenuResto
		this.getServletContext().getRequestDispatcher(MEAL_MENU)
				.forward(request, response);
	}
	
	
	/**
	 * Methode qui permet de retourner le gestionnaire mealDao
	 * Liaison avec la BDD niveau des plats
	 * @return MealDao
	 */
	public MealDao getMealDao(){
		return this.mealDAO;
		
	}
	
	/**
	 * Methode qui permet d'initialiser une map error qui servira
	 * pour la gestion des erreurs saisies voir d'erreur d'insertion BDD
	 */
	public void setMapError(){
		this.returnMessage = new HashMap<String, String>();
	}
	
	/**
	 * Methode QUI PERMET DE retourner la map d'erreur liee aux plats
	 * @return Map<String, String>
	 */
	public Map<String, String> getMapError(){
		return this.returnMessage;
	}
	
	/**
	 *MEthode qui permet de retourner la session d'un utilisateur 
	 * @param request (HttpServletRequest)
	 * @return HttpSession
	 */
	public HttpSession getSession(HttpServletRequest request)
	{
		return request.getSession();
	}
	
	/**
	 * Methode qui permet de setter la valeur du menu dans lequel
	 * nous devons ajouter le plat ( récupérer depuis HttpServletRequest resquest)
	 * @param i
	 */
	public void setIdMenuSession(int i){
		this.idMenu=i;
	}
	
	/**
	 * Methode qui permet de retourner idMenu dans lequel nous devons ajouter le plat
	 * @return idMenu (int)
	 */
	public int getIdMenuSession(){
		return this.idMenu;
	}
	
	
	/**
	 * Methode qui permet d'ajouter des variables session afin de gerer les erreurs de BDD
	 * , Manque de description plats ....
	 * @param requestR
	 * @param session
	 */
	public void testDescriptionMissing(HttpServletRequest requestR,HttpSession session){
		this.etatDescription=0;
		//Recuperation d'informations pour les afficher a  l'utilisateur
		if (requestR.getParameter("description").equals("")) {
			session.setAttribute("retourInt", 3);
			session.setAttribute("retourString", requestR.getParameter("name"));
			this.etatDescription=1;
		} else {
			session.setAttribute("retourInt", 0);
			session.setAttribute("retourString", requestR.getParameter("name"));
		}
		
	}
	
	/**
	 * Methode qui permet de savoir si un plat dispose d'une description ou non
	 * 0 = oui
	 * 1 = non
	 * @return int
	 */
	public int getEtatDescription(){
		return this.etatDescription;
	}
	
}