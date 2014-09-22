package ca.etsmtl.log210.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;
import ca.etsmtl.log210.Beans.UserAccountBean;

public class UserAccountDaoImpl implements UserAccountDao {

	private DAOFactory daoFactory;
	private static final String SQL_GET_USER_ACCOUNT = "" + "SELECT * "
			+ "FROM tbUserAccount " + "WHERE USR_email=? "
			+ "AND USR_password=?";

	private static final String SQL_MODIFY_USER_ACCOUNT = ""
			+ "UPDATE 'tbUserAccount'"
			+ "SET 'USR_homeAddress'=?,'USR_phoneNumber'=?,'USR_password'=?"
			+ "WHERE 'USR_id'=?";;

	private static final String SQL_NEW_USER_ACCOUNT = ""

			+ "INSERT INTO `tbUserAccount`( `USR_name`, `USR_firstName`, `USR_homeAddress`, `USR_email`, `USR_phoneNumber`, `USR_password`, `USR_rights`, `USR_birthday`)  "
			+ "VALUES( ?,?,?,?,?,?,?,?) ";

	public UserAccountDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
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

	public void newUserAccount(UserAccountBean newUser) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour;

		try {
			/* R�cup�ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_NEW_USER_ACCOUNT, false, newUser.getName(),
					newUser.getFirstName(), newUser.getHomeAddress(),
					newUser.getEmail(), newUser.getPhoneNumber(),
					newUser.getPassWord(), newUser.getUserRights(),
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
			/* R�cup�ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_MODIFY_USER_ACCOUNT, false, modUser.getUserId(),
					modUser.getHomeAddress(), modUser.getPhoneNumber(),
					modUser.getPassWord());

			System.out.println(preparedStatement);

			codeRetour = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return codeRetour;
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
	private static UserAccountBean mapTableauApplication(ResultSet resultSet)
			throws SQLException {
		UserAccountBean userAccountFromBD = new UserAccountBean();
		userAccountFromBD.setUserId(resultSet.getString("USR_idUser"));
		userAccountFromBD.setName(resultSet.getString("USR_name"));
		userAccountFromBD.setFirstName(resultSet.getString("USR_firstName"));
		userAccountFromBD
				.setHomeAddress(resultSet.getString("USR_homeAddress"));
		userAccountFromBD.setBirthdayDate(resultSet.getString("USR_birthday"));
		userAccountFromBD
				.setPhoneNumber(resultSet.getString("USR_phoneNumber"));
		userAccountFromBD.setUserRights(resultSet.getInt("USR_rights"));
		userAccountFromBD.setEmail(resultSet.getString("USR_email"));
		userAccountFromBD.setPassWord(resultSet.getString("USR_password"));

		return userAccountFromBD;
	}

}
