package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.UserAccountDao;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */


/**
 * This Servlet is use for the connection of a user to the web site. 
 * This Servlet checks in the DB if the couple of email/passwork given by the user of the web site is right.
 * 
 * @author Iron_Cesar
 *
 */
public class UserConnection extends HttpServlet
{
	private static final String EMAIL  = "email";
    private static final String PASS   = "password"; 
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "user";
    public static final String SESSION_USER = "userSession";    
    public static final String ERRORS_FORM         = "form";
    //This attribute is use for the redirection of the web page after the tasks with the DB are done.
    public static final String WELCOME_PAGE              = "/Restrict/welcome.jsp";
    public static final String FORM              = "/connection.jsp";
    
    //cette ma va nous servir a socker le rsultat du test de la validit du format de l'email et du mot de passe Map<String,String> errors;
    private Map<String,String> errors;

    //The instance of UserAccountDao who give us the possibility to execute requests to the DB about userAccount
    private UserAccountDao userAccountDao;
    
    
    /**
     * Method who is executed the fist time that the servlet is create. Here we get the connection to the DB throw UserAccountDao class.
     * We must get the connection just once if we don't want to have a too many connection error in MySql.
     */
    public void init() throws ServletException 
	{
    	this.userAccountDao= ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserAccountDao();
	}
    
    /**
     * Methode qui execute des requetes GET
     * 
     */
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
    	
  		
	}
    
    /**
     * Cette mthode va traiter la requete Post qui a t envoy par le formulaire de connexion de la page connection.jsp
     * Elle teste la validit du format de l'email et du mot de passe. Si ils ont un bon format, on fait la requete BD.
     * Sinon on retourne a la page du formulaire et on affiche les erreurs pour que l'utilisateur se corrige.
     */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 
		
	    	//On cr un UserAccountBean qui va nous servir a stocker le email et le mot de passe saisi dans le formulaire de connexion
	    	UserAccountBean user= new UserAccountBean();
	    	
	    	//On rcupre l'email saisi par l'utilisateur
	    	String email = UserAccountBean.getValeurChamp( request, EMAIL );
    	
	    	//On rcupre le mot de passe saisi par l'utilisateur
        String password = UserAccountBean.getValeurChamp( request, PASS );
        
        //Je vrifie le format de l'email et du mot de passe. Si il y a des erreurs, elles seront enregistres dans une map 
        // qu'on renverra au formulaire et qui affichera le contenu de la map
		errors=user.verifyValidityOfDatas(email, password);
		
		 

		//Si les donnes du formulaires sont au bon format, je fais la requete au serveur
		if ( errors.isEmpty() ) 
		{
			//Ici, on va chercher dans la BD si il y a un utilisateur avec le couple email/password saisi.
			//On passe en parrametre le bean qu'on a cr et qui contient le user et le mot de passe.
			//Cette methode va nous retourner un autre bean contenant toutes les donnes recues de la BD 
			user= getUserAccount(email, password);
			
			//On rcupre la variable de session
			HttpSession session = request.getSession(); 
			
			session.setAttribute( "langue", "fr" );

			//Si l'utilisateur est inconnu, null est renvoye par la methode getUserAccount
			if(user.getEmail()==null)
			{
				//Si le user est inconnu, on cr une erreur
				addInErrorMap("unknowUser","L'utilisateur n'est pas connu. Veuillez verifier votre courriel et votre mot de passe.");
				
				//On set la map d'erreurs pour pouvoir les afficher sur le formulaire
	            session.setAttribute( ERRORS_FORM, errors );
				
            
	            //On retourne sur la page de connection pour afficher le formulaire et l'erreur
	            redirect(request, response);
	    			//this.getServletContext().getRequestDispatcher(FORM).forward( request, response );
			}
			else
			{
				
				//On lui set le bean user recu et on l'associe au nom contenu dans SESSION_USER.
	            session.setAttribute( SESSION_USER, user );
				
	            //Si le user estconnu, on cr une erreur
				addInErrorMap("succes","L'utilisateur est connecte");
				
	            //On redirige vers la page de bienvenu des utilisateures connects
	            redirect(request, response);
	    			//this.getServletContext().getRequestDispatcher(WELCOME_PAGE).forward( request, response );
			}

			
        } 
		else 
		{
			//On rcupre la variable de session
			HttpSession session = request.getSession();
			
			//On set la map d'erreurs pour pouvoir les afficher sur le formulaire
            session.setAttribute( ERRORS_FORM, errors );
			
            //On retourne sur la page de connection pour afficher le formulaire et les erreurs
            redirect(request, response);
            //this.getServletContext().getRequestDispatcher(FORM).forward( request, response );
        }
		
		
		
		
		
	}

	/**
	 * Methode qui retourne les information de l'utilisateur dont l'email et le mot de passe sont passe en parametre. 
	 * @param email			l'email de l'utilisateur
	 * @param password		le mot de passe de l'utilisateur
	 * @return					le bean contenant les information du user si il existe dans la bd, sinon null.
	 */	
	public UserAccountBean getUserAccount(String email, String password)
	{
		return userAccountDao.getUserAccount(email, password);
	}
	
	/**
	 * Methode qui fait la redirection
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(errors.containsKey("unknowUser"))
		{
			//On retourne sur la page de connection pour afficher le formulaire et les erreurs
			this.getServletContext().getRequestDispatcher(FORM).forward( request, response );
			
		}
		else
		{
			//On affiche la page d'accueil de l'application
			this.getServletContext().getRequestDispatcher(WELCOME_PAGE).forward( request, response );
		}
		
	}
	
	/**
	 * Methode qui ajoute dans la map d'erreur
	 * 
	 */
	public void addInErrorMap(String key, String message)
	{
		errors.put(key,message);
	}
    
}
