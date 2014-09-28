package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.etsmtl.log210.Beans.MenuBean;

public class MenuMangeDaoImpl implements MenuManageDao  {
	
	static final String SQL_ADD_NEW_MENU = ""
			+ "INSERT INTO `tbMenu`( `MEN_idRestaurant`, `MEN_name`, `MEN_description`, `MEN_visible`)  "
			+ "VALUES( ?,?,?,?) ";
	
	
	static final String SQL_GET_ALL_MENU_RESTAURANT = "" 
			+ "SELECT * "
			+ "FROM tbMenu " + "WHERE MEN_idRestaurant=? ";

	
	static final String SQL_MODIFY_MENU_RESTAURANT = ""
			+ "UPDATE tbMenu "
			+ "SET MEN_name=?, MEN_description=?"
			+ "WHERE MEN_idMenu=? and MEN_idRestaurant=?"  ;

	
	static final String SQL_DELETE_MENU_RESTAURANT = "" 
			+ "UPDATE tbMenu "
			+ "SET MEN_visible=?"
			+ "WHERE MEN_idMenu=? and MEN_idRestaurant=?"  ;

	

	@Override
	public void addNewMenu(MenuBean menuRecu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMenu(MenuBean menuRecu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MenuBean[] showAllMenuForOneResto(int restaurantNumber) {
		// TODO Auto-generated method stub
		return null;
	}



}
