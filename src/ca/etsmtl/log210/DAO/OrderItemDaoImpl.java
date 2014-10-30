package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.RestaurantBean;

public class OrderItemDaoImpl implements OrderItemDao 
{
	private DAOFactory daoFactory;
	private static String SQL_NEW_ORDERITEM=""
			+ "INSERT "
			+ "INTO tborderitem( ITM_idMeal, ITM_quantity, ITM_idOrder ) "
			+ "VALUES ( ?, ?, ? )";
	
	public OrderItemDaoImpl(DAOFactory daoFactory) 
	{
		this.daoFactory = daoFactory;		
	}

	@Override
	public boolean newOrderItem(OrderItemBean orderItem) 
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;
		
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_NEW_ORDERITEM, false,  orderItem.getMeal().getIdMeal(), orderItem.getQuantity(), orderItem.getIdOrder());

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
	
	private OrderItemBean mapOrderItem(ResultSet resultSet) throws SQLException 
	{
		OrderItemBean orderItem = new OrderItemBean();
		
		orderItem.setIdOrder(resultSet.getInt("ITM_idOrder"));
		orderItem.setMeal(mapMealBean(resultSet));
		orderItem.setQuantity(resultSet.getInt("ITM_quantity"));
		
		return  orderItem;
	}
	
	private MealBean mapMealBean(ResultSet resultSet) throws SQLException {
		MealBean meal = new MealBean();

		meal.setIdMeal(resultSet.getInt("PLA_idPlat"));
		meal.setIdMenu(resultSet.getInt("PLA_idMenu"));
		meal.setPrice(resultSet.getInt("PLA_price"));
		meal.setName(resultSet.getString("PLA_name"));
		meal.setDescription(resultSet.getString("PLA_description"));

		return meal;
	}

}
