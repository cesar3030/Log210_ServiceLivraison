package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.UserAccountDao;

public class UpdateRestaurateurAccount extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String EMAIL  = "emailUpdate";
    private static final String PASS   = "password2"; 
    public static final String RESTAURANT_MANAGEMENT_ACCESS = "/RestaurateurManagement";
    public static final String SESSION_USER = "userSession";    
    public static final String ERRORS_FORM         = "returnMessage";
    
    
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
    
    
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
	    
	    	UserAccountBean user= new UserAccountBean();
	 
	   	
			
			// On set newUser les valeurs rentres dans le formulaire ok nickel.
			user.setEmail(request.getParameter("email_subscribe"));
			user.setPassword(UserAccountBean.getValeurChamp( request, PASS ));
			user.setPhoneNumber(request.getParameter("phone"));
			user.setHomeAddress(request.getParameter("adress"));
			user.setUserId(Integer.parseInt(request.getParameter("idRestaurateurToUpdate")));
		
	    	
        
        //cette ma va nous servir a socker le rsultat du test de la validit du format de l'email et du mot de passe 
        Map<String,String> errors;
        
        //Je vrifie le format de l'email et du mot de passe. Si il y a des erreurs, elles seront enregistres dans une map 
        // qu'on renverra au formulaire et qui affichera le contenu de la map
		errors=user.verifyValidityOfDatas(user.getEmail(), user.getPassword());
		
		 

		//Si les donnes du formulaires sont au bon format, je fais la requete au serveur
		if ( errors.isEmpty() ) 
		{
			//Ici on va stocker le retour de l'update(si 0 tout s'est bien pass, si 1 il y a eu un problme)
			int returnedValue=0;		
			
			returnedValue=userAccountDao.modifyUserAccount(user);			
		
			//Si la requete retourne une erreur
			if(returnedValue==0)
			{
				//on cree une erreur a afficher et on l'insre dans la map qu'on utilise pour afficher les erreurs
				errors.put("fail","Le compte n a pas pu etre mis a jours. Veuillez reessayer.");
				
				//On set la map d'erreurs pour pouvoir les afficher sur le formulaire
	           // session.setAttribute( ERRORS_FORM, errors );
				request.setAttribute(ERRORS_FORM,errors);
				
	            //On retourne sur la page de connection pour afficher le formulaire et l'erreur
				 ServletContext context= getServletContext();
				 RequestDispatcher rd= context.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS);
				 rd.forward(request, response);
			}
			else
			{
	            errors.put("succes","Le compte a ete mis a jours avec succes");
	           
	            request.setAttribute(ERRORS_FORM,errors);
	            //On redirige vers la page MyAccount.jsp
	            ServletContext context= getServletContext();
				 RequestDispatcher rd= context.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS);
				 rd.forward(request, response);
			}

			
        } 
		else 
		{
			
			//on cree une erreur a afficher et on l'insre dans la map qu'on utilise pour afficher les erreurs
			errors.put("fail","Le compte n a pas pu etre mis a jours. Veuillez reessayer.");
			
			//On set la map d'erreurs pour pouvoir les afficher sur le formulaire
            request.setAttribute( ERRORS_FORM, errors );
			
            //On retourne au formulaire et on affiche les erreurs
            ServletContext context= getServletContext();
			 RequestDispatcher rd= context.getRequestDispatcher(RESTAURANT_MANAGEMENT_ACCESS);
			 rd.forward(request, response);
        }
		
	}
		
    
    
}
