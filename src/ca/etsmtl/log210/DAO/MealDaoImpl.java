package ca.etsmtl.log210.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.MenuBean;

public class MealDaoImpl  implements MealDao{
	
	private DAOFactory daoFactory;
	
	public MealDaoImpl(DAOFactory daoFactory) 
	{
		this.daoFactory = daoFactory;		
	}

	
	//Permet de reformer un Bean Meal de la BD pour pourvoir travailler avec les donn�es provenant de la requete SQL
	private MealBean mapMealBean(ResultSet resultSet) throws SQLException 
	{
		MealBean meal = new MealBean();
		
		meal.setIdPlat(resultSet.getInt("PLA_idPlat"));
		meal.setIdMenu(resultSet.getInt("PLA_idMenu"));
		meal.setPrice(resultSet.getInt("PLA_price"));
		meal.setName(resultSet.getString("PLA_nom"));
		meal.setDescription(resultSet.getString("PLA_description"));
				
		return meal;
	}
}
