package ca.etsmtl.log210.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;
import ca.etsmtl.log210.Beans.RestaurantBean;
import ca.etsmtl.log210.Beans.UserAccountBean;

public class UserAccountDaoImpl implements UserAccountDao {

	private DAOFactory daoFactory;
	private static final String SQL_GET_USER_ACCOUNT = "" 
			+ "SELECT * "
			+ "FROM tbUserAccount " 
			+ "WHERE USR_email=? "
			+ "AND USR_password=?";

	private static final String SQL_MODIFY_USER_ACCOUNT = ""
			+ "UPDATE tbUserAccount "
			+ "SET USR_homeAddress=?, USR_phoneNumber=?,  USR_password =? "
			+ "WHERE USR_idUser=?";
	
	private static final String SQL_GET_ACTIVE_RESTAURATEUR = ""
			+ "SELECT * "
			+ "FROM tbUserAccount " 
			+ "WHERE USR_rights=1 "
			+ "AND USR_visible=1";
	
	private static final String SQL_GET_INACTIVE_RESTAURATEUR = ""
			+ "SELECT * "
			+ "FROM tbUserAccount " 
			+ "WHERE USR_rights=1 "
			+ "AND USR_visible=0";

	private static final String SQL_NEW_USER_ACCOUNT = ""
			+ "INSERT INTO `tbUserAccount`( `USR_name`, `USR_firstName`, `USR_homeAddress`, `USR_email`, `USR_phoneNumber`, `USR_password`, `USR_rights`, `USR_birthday`)  "
			+ "VALUES( ?,?,?,?,?,?,?,?) ";
	
	private static final String SQL_TEST_EMAIL = "" 
			+ "SELECT * "
			+ "FROM tbUserAccount " 
			+ "WHERE USR_email=? ";
	
	private static final String SQL_CHANGE_VISIBILITY_TO_0_RESTAURATEUR = "" 
			+ "UPDATE tbUserAccount "
			+ "SET USR_visible=0 "
			+ "WHERE USR_idUser=?";
	
	private static final String SQL_CHANGE_VISIBILITY_TO_1_RESTAURATEUR = "" 
			+ "UPDATE tbUserAccount "
			+ "SET USR_visible=1 "
			+ "WHERE USR_idUser=?";

	public UserAccountDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public UserAccountBean getUserAccount(String email, String password) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		UserAccountBean userAccount = new UserAccountBean();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_USER_ACCOUNT, false, email, password);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) {
				userAccount = mapUserAccount(resultSet);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return userAccount;
	}

	public void newUserAccount(UserAccountBean newUser) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour;

		try {
			/* R���������cup���������ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_NEW_USER_ACCOUNT, false, newUser.getName(),
					newUser.getFirstName(), newUser.getHomeAddress(),
					newUser.getEmail(), newUser.getPhoneNumber(),
					newUser.getPassword(), newUser.getUserRights(),
					newUser.getBirthdayDate());

			System.out.println(preparedStatement);

			codeRetour = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
	}

	public int modifyUserAccount(UserAccountBean modUser) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour;

		try {
			/* R���������cup���������ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_MODIFY_USER_ACCOUNT, false,
					modUser.getHomeAddress(), modUser.getPhoneNumber(),
					modUser.getPassword(),modUser.getUserId());

			System.out.println(preparedStatement);

			codeRetour = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return codeRetour;
	}
	
	@Override
	/**
	 * Methode qui demande a la BD de lui renvoyer le compte lie a l'email passe en parametre.
	 * Si null est renvoyer, cela veut dire qu'il n'y a pas d'utilisateur lie a ce compte donc l'adresse email peut etre utilisee.
	 * Si une instance UserAccountBean est renvoyé, cela veut dit qu'il y a deja un utilisateur qui a cet email. Dans ce cas on retourne true.
	 */
	public boolean emailAlreadyUsed(String testingEmail) 
	{				
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		UserAccountBean userAccount = null;

		try {
			/* R���������cup���������ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_TEST_EMAIL, false, testingEmail);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donn���������es de l'���������ventuel ResulSet retourn��������� */
			while (resultSet.next()) {
				userAccount = mapUserAccount(resultSet);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		System.out.println(userAccount);
		if(userAccount==null)
		{
			return false;
		}
		else
		{
			return true;
		}
		
		
		
	}


	/**
	 * Methode who create a new {@link UserAccountBean} and who set the values
	 * returned by the DB to the bean.
	 * 
	 * @param resultSet
	 *            (The DB answer)
	 * @return userFromBD (The UserAccountBean with the values returned by the
	 *         DB)
	 * @throws SQLException
	 */
	private static UserAccountBean mapUserAccount(ResultSet resultSet)
			throws SQLException {
		UserAccountBean userAccountFromBD = new UserAccountBean();
		userAccountFromBD.setUserId(resultSet.getInt("USR_idUser"));
		userAccountFromBD.setName(resultSet.getString("USR_name"));
		userAccountFromBD.setFirstName(resultSet.getString("USR_firstName"));
		userAccountFromBD
				.setHomeAddress(resultSet.getString("USR_homeAddress"));
		userAccountFromBD.setBirthdayDate(resultSet.getString("USR_birthday"));
		userAccountFromBD
				.setPhoneNumber(resultSet.getString("USR_phoneNumber"));
		userAccountFromBD.setUserRights(resultSet.getInt("USR_rights"));
		userAccountFromBD.setEmail(resultSet.getString("USR_email"));
		userAccountFromBD.setPassword(resultSet.getString("USR_password"));

		return userAccountFromBD;
	}

	@Override
	/**
	 * Methode qui retourne un arrayList de UserAccountBean contenant les restaurateurs actifs
	 */
	public ArrayList<UserAccountBean> getListActiveRestaurateur() {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<UserAccountBean> restaurateurList = new ArrayList<UserAccountBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ACTIVE_RESTAURATEUR, false);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				restaurateurList.add(mapUserAccount(resultSet));
			}

		} 
		catch (SQLException e) 
		{
			throw new DAOException(e);
		} 
		finally 
		{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return restaurateurList;
		
	}

	
	
	@Override
	/**
	 * Methode qui retourne un arrayList de UserAccountBean contenant les restaurateurs actifs
	 */
	public ArrayList<UserAccountBean> getListInactiveRestaurateur() {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<UserAccountBean> restaurateurList = new ArrayList<UserAccountBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_INACTIVE_RESTAURATEUR, false);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				restaurateurList.add(mapUserAccount(resultSet));
			}

		} 
		catch (SQLException e) 
		{
			throw new DAOException(e);
		} 
		finally 
		{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return restaurateurList;
		
	}

	/**
	 * La methode change la visibilite du restaurateur dont l'identifiant est passe en parametre.
	 * Cela permet de garder un historique des restaurateur au lieu de tout supprimer.
	 * Cette methode passe la visibilite du restaurateur de 0 a 1
	 * @param idRestaurant
	 * @return
	 */
	public boolean swtichRestaurateurToNotVisible(int idRestaurateur) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_CHANGE_VISIBILITY_TO_0_RESTAURATEUR, false,idRestaurateur);

			System.out.println(preparedStatement);

			codeRetour = preparedStatement.executeUpdate();
			
			if(codeRetour==0)
			{
				etatRetour=false;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return etatRetour;
	}

	/**
	 * La methode change la visibilite du restaurateur dont l'identifiant est passe en parametre.
	 * Cela permet de garder un historique des restaurateur au lieu de tout supprimer.
	 * Cette methode passe la visibilite du restaurateur de 0 a 1
	 * @param idRestaurant
	 * @return
	 */
	public boolean swtichRestaurateurToVisible(int idRestaurateur) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_CHANGE_VISIBILITY_TO_1_RESTAURATEUR, false, idRestaurateur);

			System.out.println(preparedStatement);

			codeRetour = preparedStatement.executeUpdate();
			
			if(codeRetour==0)
			{
				etatRetour=false;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return etatRetour;
	}

}
