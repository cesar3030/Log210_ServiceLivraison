package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Beans.RestaurantBean;

public class MenuManageDaoImpl implements MenuManageDao {

	static final String SQL_ADD_NEW_MENU = ""
			+ "INSERT INTO `tbmenu`( `MEN_idRestaurant`,`MEN_name`, `MEN_description`,`MEN_visible`)  "
			+ "VALUES( ?,?,?,?) ";

	static final String SQL_GET_ALL_ACTIVE_MENU_RESTAURANT =
			"SELECT * FROM tbmenu  WHERE MEN_idRestaurant=? and MEN_visible=1";

	static final String SQL_GET_ALL_INACTIVE_MENU_RESTAURANT =
			"SELECT * FROM tbmenu  WHERE MEN_idRestaurant=? and MEN_visible=0";
	
	static final String SQL_GET_ALL_RESTAURANT_NOT_EMPTY =
			"SELECT DISTINCT MEN_idMenu, MEN_idRestaurant, MEN_name,MEN_description,MEN_visible FROM tbplat ,tbmenu, tbrestaurant WHERE PLA_idMenu = MEN_idMenu AND RES_idRestaurant = MEN_idRestaurant AND RES_idRestaurant = ?";

	static final String SQL_MODIFY_MENU_RESTAURANT = 
			"" + "UPDATE tbmenu "
			+ "SET MEN_name=?, MEN_description=?"
			+ "WHERE MEN_idMenu=?";

	static final String SQL_DELETE_MENU_RESTAURANT = 
			 "" + "DELETE FROM `tbmenu` "
			+ "WHERE MEN_idMenu=?";
	
	static final String SQL_DELETE_PLATS_MENU = 
			 "" + "DELETE FROM `tbplat` "
			+ "WHERE PLA_idMenu=?";
	private DAOFactory daoFactory;
	public ArrayList<MenuBean> menusRestaurantList;

	public MenuManageDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public boolean addNewMenu(MenuBean menuRecu) {
		

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int insertion=0;
		boolean etatInsertion=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_ADD_NEW_MENU , false,
					menuRecu.getIdRestaurant(),
					menuRecu.getName(),
					menuRecu.getDescription(),
					1);

			
			insertion = preparedStatement.executeUpdate();
			
			if(insertion==0)
			{
				etatInsertion=false;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return etatInsertion;

	}

	
	public boolean deleteMenu(int menuRecu) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		int codeRetour = 0;
		boolean etatRetour = true;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_MENU_RESTAURANT , false, menuRecu);

			

			codeRetour = preparedStatement.executeUpdate();
			if (codeRetour == 0) {
				etatRetour = false;
			}
			
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_PLATS_MENU , false, menuRecu);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return etatRetour;
		
	}
	public boolean modifyMenu(int idMenuRecu, String nameMenuRecu, String descriptionRecu) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour = 0;
		boolean etatRetour = true;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_MODIFY_MENU_RESTAURANT, false, 
					nameMenuRecu,
					descriptionRecu,
					idMenuRecu);

		

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
	public ArrayList<MenuBean> showAllActiveMenuForOneResto(int restaurantNumber) {
		
	
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		menusRestaurantList = new ArrayList<MenuBean> ();

		try {
			/* Faire une connexion depuis la Factory */
			connexion = daoFactory.getConnection();

			// ON PREPARE LA REQUETE
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ALL_ACTIVE_MENU_RESTAURANT, false, restaurantNumber);

		
			
			resultSet = preparedStatement.executeQuery();
		
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) {
				
				menusRestaurantList.add(mapMenuBean(resultSet));

			}

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		resultSet = null;
	
		return menusRestaurantList;
	}

	public ArrayList<MenuBean> showAllInactiveMenuForOneResto(
			int restaurantNumber) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MenuBean> inactiveMenuRestaurantList = new ArrayList<MenuBean>();
		try {
			/* Faire une connexion depuis la Factory */
			connexion = daoFactory.getConnection();

			// ON PREPARE LA REQUETE
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ALL_ACTIVE_MENU_RESTAURANT, false, restaurantNumber);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) {
				inactiveMenuRestaurantList.add(mapMenuBean(resultSet));
				
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return inactiveMenuRestaurantList;
	}

	// Permet de reformer un Bean Menu de la BD pour pourvoir travailler avec
	// les donn�es
	private MenuBean mapMenuBean(ResultSet resultSet) throws SQLException {
		MenuBean menu = new MenuBean();

		menu.setIdMenu(resultSet.getInt("MEN_idMenu"));
		menu.setIdRestaurant(resultSet.getInt("MEN_idRestaurant"));
		menu.setname(resultSet.getString("MEN_name"));
		menu.setDescription(resultSet.getString("MEN_description"));
		menu.setVisible(resultSet.getInt("MEN_visible"));
		
		return menu;
	}

	@Override
	public ArrayList<MenuBean> showMenuNotEmpty(int restaurantNumber) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MenuBean> RestaurantNotEmptyList = new ArrayList<MenuBean>();
		try {
			/* Faire une connexion depuis la Factory */
			connexion = daoFactory.getConnection();

			// ON PREPARE LA REQUETE
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ALL_RESTAURANT_NOT_EMPTY, false, restaurantNumber);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) {
				RestaurantNotEmptyList.add(mapMenuBean(resultSet));
				
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return RestaurantNotEmptyList;
	}

}
