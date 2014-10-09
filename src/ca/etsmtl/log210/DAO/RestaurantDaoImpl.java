<<<<<<< HEAD

/**
 * 
 */
package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ca.etsmtl.log210.Beans.RestaurantBean;

/**
 * @author Iron_Cesar
 *
 */
public class RestaurantDaoImpl implements RestaurantDao 
{
	
	
	private DAOFactory daoFactory;
	
	private static final String SQL_GET_ACTIVE_RESTAURANTS = "" 
			+ "SELECT * "
			+ "FROM tbrestaurant res, tbuseraccount usr " 
			+ "WHERE  RES_visible=1 "
			+ "AND res.RES_idUserAccount= usr.USR_idUser";
	
	private static final String SQL_GET_INACTIVE_RESTAURANTS = "" 
			+ "SELECT * "
			+ "FROM tbrestaurant res, tbuseraccount usr " 
			+ "WHERE  RES_visible=0 "
			+ "AND res.RES_idUserAccount= usr.USR_idUser";
	
	private static final String SQL_NEW_RESTAURANT = "" 
			+ "INSERT INTO `tbrestaurant`"
			+ "(RES_idUserAccount, RES_name, RES_address, "
			+ "RES_phoneNumber, RES_kindOfFood, RES_visible) "
			+ "VALUES (?,?,?,?,?,1)";
	
	private static final String SQL_CHANGE_VISIBILITY_TO_0_RESTAURANT = "" 
			+ "UPDATE tbrestaurant "
			+ "SET RES_visible=0 "
			+ "WHERE RES_idRestaurant=?";
	
	private static final String SQL_CHANGE_VISIBILITY_TO_1_RESTAURANT = "" 
			+ "UPDATE tbrestaurant "
			+ "SET RES_visible=1 "
			+ "WHERE RES_idRestaurant=?";
	
	private static final String SQL_UPDATE_RESTAURANT = "" 
			+ "UPDATE tbrestaurant "
			+ "SET RES_idUserAccount=?,RES_name=?,RES_address=?, "
			+ "RES_phoneNumber=?,RES_kindOfFood=? "
			+ "WHERE RES_idRestaurant=?";
	
	private static final String SQL_GET_ONE_RESTAURANT = "" 
			+ "SELECT * "
			+ "FROM tbrestaurant r, tbuseraccount u " 
			+ "WHERE  RES_idRestaurant=? "
			+ "AND r.RES_idUserAccount=u.USR_idUser";
	
	
	public RestaurantDaoImpl(DAOFactory daoFactory) 
	{
		this.daoFactory = daoFactory;		
	}
	

	@Override
	public ArrayList<RestaurantBean> getActiveRestaurants() {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<RestaurantBean> restaurantList = new ArrayList<RestaurantBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ACTIVE_RESTAURANTS, false);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				restaurantList.add(mapRestaurateur(resultSet));
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
		return restaurantList;
	}



	@Override
	public ArrayList<RestaurantBean> getInnactiveRestaurants() {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<RestaurantBean> restaurantList = new ArrayList<RestaurantBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_INACTIVE_RESTAURANTS, false);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				restaurantList.add(mapRestaurateur(resultSet));
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
		return restaurantList;
	}
	
	
	@Override
	public boolean addNewRestaurant(RestaurantBean newRestaurant) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_NEW_RESTAURANT, false,newRestaurant.getIdUserAccountRestaurateur(),
					newRestaurant.getName(),newRestaurant.getAddress(),newRestaurant.getPhoneNumber(),newRestaurant.getKindOfFood());

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

	
	private RestaurantBean mapRestaurateur(ResultSet resultSet) throws SQLException 
	{
		RestaurantBean restaurant = new RestaurantBean();
		
		restaurant.setIdRestaurant(resultSet.getInt("RES_idRestaurant"));
		restaurant.setIdUserAccountRestaurateur(resultSet.getInt("RES_idUserAccount"));
		restaurant.setName(resultSet.getString("RES_name"));
		restaurant.setAddress(resultSet.getString("RES_address"));
		restaurant.setPhoneNumber(resultSet.getString("RES_phoneNumber"));
		restaurant.setKindOfFood(resultSet.getString("RES_kindOfFood"));
		restaurant.setVisible(resultSet.getBoolean("RES_visible"));		
		restaurant.setRestaurateurName(resultSet.getString("USR_firstName")+" "+resultSet.getString("USR_name"));
		
		return restaurant;
	}


	@Override
	/**
	 * La methode change la visibilite du restaurant dont l'identifiant est passe en parametre.
	 * Cela permet de garder un historique des restaurant au lieu de tout supprimer.
	 * Cette methode passe la visibilite du restaurant, de 1 a 0
	 * @param idRestaurant
	 * @return
	 */
	public boolean  switchToNotVisibleRestaurant(int idRestaurant) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_CHANGE_VISIBILITY_TO_0_RESTAURANT, false, idRestaurant);

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
	 * La methode change la visibilite du restaurant dont l'identifiant est passe en parametre.
	 * Cela permet de garder un historique des restaurant au lieu de tout supprimer.
	 * Cette methode passe la visibilite du restaurant de 0 a 1
	 * @param idRestaurant
	 * @return
	 */
	public boolean switchToVisibleRestaurant(int idRestaurant) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_CHANGE_VISIBILITY_TO_1_RESTAURANT, false, idRestaurant);

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


	@Override
	public boolean updateResataurant(RestaurantBean restaurantToUpdate) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE_RESTAURANT, false, restaurantToUpdate.getIdUserAccountRestaurateur(),
															restaurantToUpdate.getName(),restaurantToUpdate.getAddress(), 
															restaurantToUpdate.getPhoneNumber(),restaurantToUpdate.getKindOfFood(),restaurantToUpdate.getIdRestaurant());

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
	
public RestaurantBean getNomRestaurant(int idRestaurantReceved) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		RestaurantBean nameRestaurant = null ;
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ONE_RESTAURANT, false,idRestaurantReceved);
System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				//System.out.println(mapRestaurateur(resultSet).getName());
				nameRestaurant = mapRestaurateur(resultSet);
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
		return nameRestaurant;
	}


}
=======

/**
 * 
 */
package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ca.etsmtl.log210.Beans.RestaurantBean;

/**
 * @author Iron_Cesar
 *
 */
public class RestaurantDaoImpl implements RestaurantDao 
{
	
	
	private DAOFactory daoFactory;
	
	private static final String SQL_GET_ACTIVE_RESTAURANTS = "" 
			+ "SELECT * "
			+ "FROM tbrestaurant res, tbuseraccount usr " 
			+ "WHERE  RES_visible=1 "
			+ "AND res.RES_idUserAccount= usr.USR_idUser";
	
	private static final String SQL_GET_INACTIVE_RESTAURANTS = "" 
			+ "SELECT * "
			+ "FROM tbrestaurant res, tbuseraccount usr " 
			+ "WHERE  RES_visible=0 "
			+ "AND res.RES_idUserAccount= usr.USR_idUser";
	
	private static final String SQL_NEW_RESTAURANT = "" 
			+ "INSERT INTO `tbrestaurant`"
			+ "(RES_idUserAccount, RES_name, RES_address, "
			+ "RES_phoneNumber, RES_kindOfFood, RES_visible) "
			+ "VALUES (?,?,?,?,?,1)";
	
	private static final String SQL_CHANGE_VISIBILITY_TO_0_RESTAURANT = "" 
			+ "UPDATE tbrestaurant "
			+ "SET RES_visible=0 "
			+ "WHERE RES_idRestaurant=?";
	
	private static final String SQL_CHANGE_VISIBILITY_TO_1_RESTAURANT = "" 
			+ "UPDATE tbrestaurant "
			+ "SET RES_visible=1 "
			+ "WHERE RES_idRestaurant=?";
	
	private static final String SQL_UPDATE_RESTAURANT = "" 
			+ "UPDATE tbrestaurant "
			+ "SET RES_idUserAccount=?,RES_name=?,RES_address=?, "
			+ "RES_phoneNumber=?,RES_kindOfFood=? "
			+ "WHERE RES_idRestaurant=?";
	
	private static final String SQL_GET_ONE_RESTAURANT = "" 
			+ "SELECT * "
			+ "FROM tbrestaurant " 
			+ "WHERE  RES_idRestaurant=?";
	
	
	public RestaurantDaoImpl(DAOFactory daoFactory) 
	{
		this.daoFactory = daoFactory;		
	}
	

	@Override
	public ArrayList<RestaurantBean> getActiveRestaurants() {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<RestaurantBean> restaurantList = new ArrayList<RestaurantBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ACTIVE_RESTAURANTS, false);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				restaurantList.add(mapRestaurateur(resultSet));
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
		return restaurantList;
	}



	@Override
	public ArrayList<RestaurantBean> getInnactiveRestaurants() {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<RestaurantBean> restaurantList = new ArrayList<RestaurantBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_INACTIVE_RESTAURANTS, false);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				restaurantList.add(mapRestaurateur(resultSet));
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
		return restaurantList;
	}
	
	
	@Override
	public boolean addNewRestaurant(RestaurantBean newRestaurant) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_NEW_RESTAURANT, false,newRestaurant.getIdUserAccountRestaurateur(),
					newRestaurant.getName(),newRestaurant.getAddress(),newRestaurant.getPhoneNumber(),newRestaurant.getKindOfFood());

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

	
	private RestaurantBean mapRestaurateur(ResultSet resultSet) throws SQLException 
	{
		RestaurantBean restaurant = new RestaurantBean();
		
		restaurant.setIdRestaurant(resultSet.getInt("RES_idRestaurant"));
		restaurant.setIdUserAccountRestaurateur(resultSet.getInt("RES_idUserAccount"));
		restaurant.setName(resultSet.getString("RES_name"));
		restaurant.setAddress(resultSet.getString("RES_address"));
		restaurant.setPhoneNumber(resultSet.getString("RES_phoneNumber"));
		restaurant.setKindOfFood(resultSet.getString("RES_kindOfFood"));
		restaurant.setVisible(resultSet.getBoolean("RES_visible"));		
		restaurant.setRestaurateurName(resultSet.getString("USR_firstName")+" "+resultSet.getString("USR_name"));
		
		return restaurant;
	}


	@Override
	/**
	 * La methode change la visibilite du restaurant dont l'identifiant est passe en parametre.
	 * Cela permet de garder un historique des restaurant au lieu de tout supprimer.
	 * Cette methode passe la visibilite du restaurant, de 1 a 0
	 * @param idRestaurant
	 * @return
	 */
	public boolean  switchToNotVisibleRestaurant(int idRestaurant) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_CHANGE_VISIBILITY_TO_0_RESTAURANT, false, idRestaurant);

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
	 * La methode change la visibilite du restaurant dont l'identifiant est passe en parametre.
	 * Cela permet de garder un historique des restaurant au lieu de tout supprimer.
	 * Cette methode passe la visibilite du restaurant de 0 a 1
	 * @param idRestaurant
	 * @return
	 */
	public boolean switchToVisibleRestaurant(int idRestaurant) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_CHANGE_VISIBILITY_TO_1_RESTAURANT, false, idRestaurant);

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


	@Override
	public boolean updateResataurant(RestaurantBean restaurantToUpdate) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE_RESTAURANT, false, restaurantToUpdate.getIdUserAccountRestaurateur(),
															restaurantToUpdate.getName(),restaurantToUpdate.getAddress(), 
															restaurantToUpdate.getPhoneNumber(),restaurantToUpdate.getKindOfFood(),restaurantToUpdate.getIdRestaurant());

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
	
public RestaurantBean getNomRestaurant(int idRestaurantReceved) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		RestaurantBean nameRestaurant = null ;
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ONE_RESTAURANT, false,idRestaurantReceved);
			
			System.out.println(preparedStatement);
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				System.out.println(mapRestaurateur(resultSet).getName());
				nameRestaurant = mapRestaurateur(resultSet);
			}
			
			
		} 
		catch (SQLException e) 
		{
			
		} 
		finally 
		{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return nameRestaurant;
	}


}
>>>>>>> 3b6ceb43d53924ebcf678f1cd7c5267dc26681ad

