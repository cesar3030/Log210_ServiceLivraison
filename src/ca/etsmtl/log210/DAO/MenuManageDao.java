package ca.etsmtl.log210.DAO;

import ca.etsmtl.log210.Beans.MenuBean;


public interface  MenuManageDao{
	
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

	
		void  addNewMenu (MenuBean menuRecu);
		void  deleteMenu (MenuBean menuRecu);	
		MenuBean[] showAllMenuForOneResto(int restaurantNumber);
		
	}



