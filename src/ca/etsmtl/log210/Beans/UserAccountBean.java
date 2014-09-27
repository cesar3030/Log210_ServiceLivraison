package ca.etsmtl.log210.Beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ca.etsmtl.log210.DAO.UserAccountDao;

/**
 * Bean who represents the content an entrance of the user table of the DB.
 * 
 * @author Iron_Cesar
 *
 */
public class UserAccountBean 
{
	private String name;
	private String firstName;
	private String birthdayDate;
	private String homeAddress;
	private String email;
	private String phoneNumber;
	private String password;
	//User's rights (0:not connected, 1:client, 2:Restarter, 3:administrator )
	private int    userRights=0;
	private int userId;
	
	
	private static final String EMAIL  = "email";
    private static final String PASS   = "password"; 
	//Errors fund during the tests of the form's validation
	private Map<String, String> errors      = new HashMap<String, String>();
	private String              results;
	

    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * This method verify the values given by the user, if the email is correctly based and the password has more than 6 chars, 
     * we execute the request to the DB. 
     * @param request
     * @param userAccountDao
     * @return
     */
    public Map<String,String> verifyValidityOfDatas(String email, String password) 
    {
        
        

        /* Validation du champ email. */
        try {
            emailValidation( email );
        } catch ( Exception e ) {
            setErreur( EMAIL, e.getMessage() );
        }
        

        /* Validation du champ mot de passe. */
        try {
            passwordvalidation( password );
        } catch ( Exception e ) {
            setErreur( PASS, e.getMessage() );
        }
        

        /* Initialisation du resultat global de la validation. */
        if ( errors.isEmpty() ) 
        {        	
        	results = "Succes de la connexion.";
        } else {
        	results = "echec de la connexion.";
        }
        System.out.println("erreurs du form:"+errors);
        
        return errors;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void emailValidation( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse courriel valide." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void passwordvalidation( String password ) throws Exception {
        if ( password != null ) {
            if ( password.length() < 6 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 6 caracteres." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spe la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        errors.put( champ, message );
    }

    /*
     * M��thode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    public static String getValeurChamp( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getBirthdayDate() {
		return birthdayDate;
	}
	public void setBirthdayDate(String birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passWord) {
		this.password = passWord;
	}
	public int getUserRights() {
		return userRights;
	}

	public void setUserRights(int userRights) {
		this.userRights = userRights;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	

}
