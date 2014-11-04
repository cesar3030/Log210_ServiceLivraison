package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ca.etsmtl.log210.Beans.MealBean;

/**
 * Classe qui va nous permettre d'executer une requete a partir d'un beanMeal.
 * Elle gere la communication avec la BDD
 * 
 * @author David
 *
 */
public class MealDaoImpl implements MealDao {

	static final String SQL_ADD_NEW_MEAL = ""
			+ "INSERT INTO `tbplat`( `PLA_idPlat`, `PLA_idMenu`, `PLA_price`, `PLA_name`, `PLA_description`)  "
			+ "VALUES( ?,?,?,?,?) ";

	static final String SQL_DELETE_MEAL = "" + "DELETE FROM `tbplat` "
			+ "WHERE PLA_idPlat=?";

	static final String SQL_GET_ALL_MEAL_FROM_MENU = "" + "SELECT * "
			+ "FROM tbplat " + "WHERE PLA_idMenu=? ";

	static final String SQL_GET_INFOS_MEAL = "" + "SELECT * "
			+ "FROM tbplat " + "WHERE PLA_idPlat=? ";
	private DAOFactory daoFactory;

	public MealDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Ajoute un plat dans le menu en question
	 */
	@Override
	public boolean addNewMeal(MealBean mealRecept) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour = 0;
		boolean etatRetour = true;
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_ADD_NEW_MEAL, false, mealRecept.getIdMeal(),
					mealRecept.getIdMenu(), mealRecept.getPrice(),
					mealRecept.getName(), mealRecept.getDescription());

			System.out.println(preparedStatement);

			codeRetour = preparedStatement.executeUpdate();

			if (codeRetour == 0) {
				etatRetour = false;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return etatRetour;
	}

	// Permet de reformer un Bean Meal de la BD pour pourvoir travailler avec
	// les donn�es provenant de la requete SQL
	public static MealBean mapMealBean(ResultSet resultSet) throws SQLException {
		MealBean meal = new MealBean();

		meal.setIdMeal(resultSet.getInt("PLA_idPlat"));
		meal.setIdMenu(resultSet.getInt("PLA_idMenu"));
		meal.setPrice(resultSet.getInt("PLA_price"));
		meal.setName(resultSet.getString("PLA_name"));
		meal.setDescription(resultSet.getString("PLA_description"));

		return meal;
	}

	@Override
	public boolean deleteNewMeal(int idMeal) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour = 0;
		boolean etatRetour = true;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_MEAL, false, idMeal);

			System.out.println(preparedStatement);

			codeRetour = preparedStatement.executeUpdate();

			if (codeRetour == 0) {
				etatRetour = false;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return etatRetour;
	}

	@Override
	public ArrayList<MealBean> showAllMealFromMenu(int idMenu) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ArrayList<MealBean> showAllMealFromMenu = new ArrayList<MealBean>();

		try {
			/* Faire une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			System.out.println(daoFactory.getConnection().toString());

			// Preparation de la requete
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ALL_MEAL_FROM_MENU, false, idMenu);

			System.out.println("Resultset");

			// Execution de la requete
			resultSet = preparedStatement.executeQuery();

			// On ajoute chaque retour de la requete SQL dans notre Map de
			// MealBean
			while (resultSet.next()) {

				showAllMealFromMenu.add(mapMealBean(resultSet));

			}

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return showAllMealFromMenu;
	}

	@Override
	public MealBean getInfosOfAMeal(int idMeal) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		MealBean meal = null;

		try {
			/* Faire une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			System.out.println(daoFactory.getConnection().toString());

			// Preparation de la requete
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_INFOS_MEAL, false, idMeal);

			// Execution de la requete
			resultSet = preparedStatement.executeQuery();

			// On ajoute chaque retour de la requete SQL dans notre Map de
			// MealBean
			while (resultSet.next()) {

				meal=mapMealBean(resultSet);

			}

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return meal;
	}
	

}
