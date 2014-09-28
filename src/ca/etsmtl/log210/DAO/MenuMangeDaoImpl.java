package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.etsmtl.log210.Beans.MenuBean;

public class MenuMangeDaoImpl implements MenuManageDao  {

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
