package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.UserAccountDao;

public class UpdateUserAccount extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String EMAIL  = "email";
    private static final String PASS   = "password2"; 
    public static final String MYACCOUNT_PAGE = "/Restrict/MyAccount.jsp";
    public static final String SESSION_USER = "userSession";    
    public static final String ERRORS_FORM         = "form";
    
    
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
     * Cette m������thode va traiter la requete Post qui a ������t������ envoy������ par le formulaire de de modification de compte de la page ��myAccount.jsp
     * Elle teste la validit������ du format de l'email et du mot de passe. Si ils ont un bon format, on fait la requete BD.
     * Sinon on retourne a la page du formulaire et on affiche les erreurs pour que l'utilisateur se corrige.
     */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
	    	//On cr������������ un UserAccountBean qui va nous servir a stocker le email et le mot de passe saisi dans le formulaire de connexion
	    	UserAccountBean user= new UserAccountBean();
	 
	    			
	    	request.setCharacterEncoding("UTF-8");
	    	
	    	//On r������cup������re la variable de session
		HttpSession session = request.getSession(); 
	   	UserAccountBean userAlreadyConnected= (UserAccountBean) session.getAttribute("userSession");
			
			// On set newUser les valeurs rentr������es dans le formulaire ok nickel.

			user.setEmail(UserAccountBean.getValeurChamp( request, EMAIL ));
			user.setPassword(UserAccountBean.getValeurChamp( request, PASS ));
			user.setPhoneNumber(request.getParameter("phone"));
			user.setHomeAddress(request.getParameter("adress"));
			user.setUserId( userAlreadyConnected.getUserId());
	    	
        
        //cette ma va nous servir a socker le r������sultat du test de la validit������ du format de l'email et du mot de passe 
        Map<String,String> errors;
        
        //Je v������rifie le format de l'email et du mot de passe. Si il y a des erreurs, elles seront enregistr������es dans une map 
        // qu'on renverra au formulaire et qui affichera le contenu de la map
		errors=user.verifyValidityOfDatas(user.getEmail(), user.getPassword());
		
		 

		//Si les donn������es du formulaires sont au bon format, je fais la requete au serveur
		if ( errors.isEmpty() ) 
		{
			//Ici on va stocker le retour de l'update(si 0 tout s'est bien pass��, si 1 il y a eu un probl��me)
			int returnedValue=0;		
			
			returnedValue=userAccountDao.modifyUserAccount(user);			
			
			

			//Si la requete retourne une erreur
			if(returnedValue==1)
			{
				//on cree une erreur a afficher et on l'ins��re dans la map qu'on utilise pour afficher les erreurs
				errors.put("UpdateFail","Votre compte n'a pas pu etre mis a jours. Veuillez reessayer.");
				
				//On set la map d'erreurs pour pouvoir les afficher sur le formulaire
	            session.setAttribute( ERRORS_FORM, errors );
				
	            //On retourne sur la page de connection pour afficher le formulaire et l'erreur
	    			this.getServletContext().getRequestDispatcher(MYACCOUNT_PAGE).forward( request, response );
			}
			else
			{
				
				//Si les informations ont ��t�� updat��es dans la BD alors on set le user avec ses valeurs modifi��s 
				//a la variable global qui contient le bean de l'utilisateur connect��
				
				
				//On r��cup��re l'ancienne version
				UserAccountBean userConnected= (UserAccountBean) session.getAttribute("userSession");
				
				//On lui set les nouvelles valeurs
				userConnected.setEmail(user.getEmail());
				userConnected.setPassword(user.getPassword());
				userConnected.setPhoneNumber(user.getPhoneNumber());
				
				//On set le bean user avec les valeurs modifi��es dan la variable globale
	            session.setAttribute( SESSION_USER, userConnected );
				
	            //On redirige vers la page MyAccount.jsp
	    			this.getServletContext().getRequestDispatcher(MYACCOUNT_PAGE).forward( request, response );
			}

			
        } 
		else 
		{
			
			
			//On set la map d'erreurs pour pouvoir les afficher sur le formulaire
            session.setAttribute( ERRORS_FORM, errors );
			
            //On retourne au formulaire et on affiche les erreurs
    			this.getServletContext().getRequestDispatcher(MYACCOUNT_PAGE  ).forward( request, response );
        }
		
	}
		
    
    
}
