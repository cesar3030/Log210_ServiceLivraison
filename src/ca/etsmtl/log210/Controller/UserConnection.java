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
     * Cette méthode va traiter la requete Post qui a été envoyé par le formulaire de connexion de la page connection.jsp
     * Elle teste la validité du format de l'email et du mot de passe. Si ils ont un bon format, on fait la requete BD.
     * Sinon on retourne a la page du formulaire et on affiche les erreurs pour que l'utilisateur se corrige.
     */
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
    	
	    	//On créé un UserAccountBean qui va nous servir a stocker le email et le mot de passe saisi dans le formulaire de connexion
	    	UserAccountBean user= new UserAccountBean();
	    	
	    	//On récupère l'email saisi par l'utilisateur
	    	String email = UserAccountBean.getValeurChamp( request, EMAIL );
    	
	    	//On récupère le mot de passe saisi par l'utilisateur
        String password = UserAccountBean.getValeurChamp( request, PASS );
        
        //cette ma va nous servir a socker le résultat du test de la validité du format de l'email et du mot de passe 
        Map<String,String> errors;
        
        //Je vérifie le format de l'email et du mot de passe. Si il y a des erreurs, elles seront enregistrées dans une map 
        // qu'on renverra au formulaire et qui affichera le contenu de la map
		errors=user.verifyValidityOfDatas(email, password);
		
		 

		//Si les données du formulaires sont au bon format, je fais la requete au serveur
		if ( errors.isEmpty() ) 
		{
			//Ici, on va chercher dans la BD si il y a un utilisateur avec le couple email/password saisi.
			//On passe en parrametre le bean qu'on a créé et qui contient le user et le mot de passe.
			//Cette methode va nous retourner un autre bean contenant toutes les données recues de la BD 
			user=userAccountDao.getUserAccount(email, password);
			
			System.out.println(user.getEmail());
			
			//On récupère la variable de session
			HttpSession session = request.getSession(); 

			//Si l'utilisateur est inconnu, null est renvoye par la methode getUserAccount
			if(user.getEmail()==null)
			{
				//Si le user est inconnu, on créé une erreur
				errors.put("unknowUser","L'utilisateur n'est pas connu. Veuillez verifier votre corriel et votre mot de passe.");
				
				//On set la map d'erreurs pour pouvoir les afficher sur le formulaire
	            session.setAttribute( ERRORS_FORM, errors );
				
	            //On retourne sur la page de connection pour afficher le formulaire et l'erreur
	    			this.getServletContext().getRequestDispatcher(FORM).forward( request, response );
			}
			else
			{
				//On lui set le bean user recu et on l'associe au nom contenu dans SESSION_USER.
	            session.setAttribute( SESSION_USER, user );
				
	            //On redirige vers la page de bienvenu des utilisateures connectés
	    			this.getServletContext().getRequestDispatcher(WELCOME_PAGE).forward( request, response );
			}

			
        } 
		else 
		{
			//On récupère la variable de session
			HttpSession session = request.getSession();
			
			//On set la map d'erreurs pour pouvoir les afficher sur le formulaire
            session.setAttribute( ERRORS_FORM, errors );
			
            //On retourne sur la page de connection pour afficher le formulaire et les erreurs
    			this.getServletContext().getRequestDispatcher(FORM).forward( request, response );
        }
		
		
		
		
		
	}
    
}
