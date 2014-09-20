
$("SubmitForm").on('submit',function(){
		   if($('#contact-password1').val()!=$('#contact-password2').val())
		   {
		       alert('Les deux mot de passe ne sont pas identiques');
		       return false;
		   }
		   alert('hep');
		   return true;
	});

public class UserAccountDaoImpl implements UserAccountDao
{

	private DAOFactory daoFactory;
	private static final String SQL_GET_USER_ACCOUNT = ""
		 	+ "SELECT * "
	 		+ "FROM tbUserAccount "  
	 		+ "WHERE USR_email=? "
	 		+ "AND USR_password=?";
	
	
	
	

	
	public UserAccountDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	/**
	 * Méthode qui se connecte a la BD et retourne un objet 
	 * UserAccountBean correspondant au mot de passe et au passeword qui ont été fournis par l'utilisateur.
	 * Si l'utilisateur n'est pas trouvé, la méthode retourne le UserAccountBean mais sans informations setté.
	 */
	public UserAccountBean getUserAccount(String email, String password) {
		
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    UserAccountBean userAccount =new UserAccountBean();
	    
	    try {	        
	  		/* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        
	        /*Nos ajoutons les attributes email et password à la place des ? dans la requetes SQL_GET_USER_ACCOUNT. 
	         * Attention Il faut les rentrer dans le meme ordre qu'ils apparessent dans la requete.
	         */
	        preparedStatement = initialisationRequetePreparee( connexion,SQL_GET_USER_ACCOUNT, false, email, password );
	        
	        //J'execute la requete
	        resultSet = preparedStatement.executeQuery();
	        
	        /* Parcours de la ligne de donn���es de l'���ventuel ResulSet retourn��� */
	        while ( resultSet.next() ) 
	        {
		        	userAccount=mapUserAccount(resultSet);
	        }
	        
	    }
  		catch ( SQLException e ) 
  		{  		
	        throw new DAOException( e );
	    } 
  		finally 
  		{
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }
	    
		return userAccount;
	}
	public UserAccountBean getUserAccount(String email, String password) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		UserAccountBean userAccount = new UserAccountBean();

		try {
			/* R�cup�ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_USER_ACCOUNT, false, email, password);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donn�es de l'�ventuel ResulSet retourn� */
			while (resultSet.next()) {
				userAccount = mapTableauApplication(resultSet);
				System.out.println(userAccount.getEmail());
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return userAccount;
	}	
	
	/**
	 * Methode who create a new {@link UserAccountBean} and who set the values returned by the