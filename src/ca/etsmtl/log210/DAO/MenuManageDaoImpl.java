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

public class MenuManageDaoImpl implements MenuManageDao  {
	
	static final String SQL_ADD_NEW_MENU = ""
			+ "INSERT INTO `tbmenu`( `MEN_idRestaurant`, `MEN_name`, `MEN_description`, `MEN_visible`)  "
			+ "VALUES( ?,?,?,?) ";
	
	
	static final String SQL_GET_ALL_ACTIVE_MENU_RESTAURANT = "SELECT * FROM tbmenu  WHERE MEN_idRestaurant=? and MEN_visible=1";
	
	static final String SQL_GET_ALL_INACTIVE_MENU_RESTAURANT = "SELECT * FROM tbmenu  WHERE MEN_idRestaurant=? and MEN_visible=0"; 
			

	
	static final String SQL_MODIFY_MENU_RESTAURANT = ""
			+ "UPDATE tbmenu "
			+ "SET MEN_name=?, MEN_description=?"
			+ "WHERE MEN_idMenu=?, MEN_idRestaurant=?"  ;

	
	static final String SQL_DELETE_MENU_RESTAURANT = "" 
			+ "UPDATE tbMenu "
			+ "SET MEN_visible=?"
			+ "WHERE MEN_idMenu=?, MEN_idRestaurant=?"  ;

	private DAOFactory daoFactory;
	
	
	
	public MenuManageDaoImpl(DAOFactory daoFactory) 
	{
		this.daoFactory = daoFactory;		
	}
	

	@Override
	public void addNewMenu(MenuBean menuRecu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMenu(MenuBean menuRecu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<MenuBean> showAllActiveMenuForOneResto(int restaurantNumber) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MenuBean> activeMenuRestaurantList = new ArrayList<MenuBean>();
		
		try {
			/* Faire une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			System.out.println(daoFactory.getConnection().toString());
			
			//ON PREPARE LA REQUETE
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ALL_ACTIVE_MENU_RESTAURANT, false,restaurantNumber);
			
			//ICIIII TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
			System.out.println("Resultset");
			resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet.toString()+"ICIIIII");
			
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				System.out.println("Avant");
				activeMenuRestaurantList.add(mapMenuBean(resultSet));
				System.out.println("Apres");
				
				
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
		System.out.println(activeMenuRestaurantList);
		return activeMenuRestaurantList;
	}
	
	public ArrayList<MenuBean> showAllInactiveMenuForOneResto(int restaurantNumber) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MenuBean> inactiveMenuRestaurantList = new ArrayList<MenuBean>();
		try {
			/* Faire une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			
			//ON PREPARE LA REQUETE
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ALL_ACTIVE_MENU_RESTAURANT, false,restaurantNumber);
			
			

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				inactiveMenuRestaurantList.add(mapMenuBean(resultSet));
				System.out.println(mapMenuBean(resultSet).getName());
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
		
		System.out.println(inactiveMenuRestaurantList);
		return inactiveMenuRestaurantList;
	}
	
	
	//Permet de reformer un Bean Menu de la BD pour pourvoir travailler avec les données
	private MenuBean mapMenuBean(ResultSet resultSet) throws SQLException 
	{
		MenuBean menu = new MenuBean();
		
		menu.setIdMenu(resultSet.getInt("MEN_idMenu"));
		menu.setIdRestaurant(resultSet.getInt("MEN_idRestaurant"));
		menu.setname(resultSet.getString("MEN_name"));
		menu.setDescription(resultSet.getString("MEN_description"));
		menu.setVisible(resultSet.getInt("MEN_visible"));
		
		return menu;
	}




}
