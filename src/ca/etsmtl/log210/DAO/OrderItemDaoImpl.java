package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.RestaurantBean;

public class OrderItemDaoImpl implements OrderItemDao 
{
	private DAOFactory daoFactory;
	private static String SQL_NEW_ORDERITEM=""
			+ "INSERT "
			+ "INTO tborderitem( ITM_idMeal, ITM_quantity, ITM_idOrder ) "
			+ "VALUES ( ?, ?, ? )";
	
	private static String SQL_ALL_ORDERITEM=""
			+ "SELECT pl.PLA_name,pl.PLA_description,ord.ITM_quantite,pl.PLA_price "
			+ "FROM tborderitem oi, tborder ord, tbPlat pl "
			+ "WHERE oi.ITM_idOrder=ord.ORD_idOrder "
			+ "AND oi.idMeal=pl.PLA_idPlat "
			+ "AND oi.ITM_idOrder=? ";
	
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

	@Override
	public ArrayList<OrderItemBean> showAllOrderItem(int idOrder) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ArrayList<OrderItemBean> showAllOrderItems = new ArrayList<OrderItemBean>();

		try {
			/* Faire une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			System.out.println(daoFactory.getConnection().toString());

			// Preparation de la requete
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_ALL_ORDERITEM, false, idOrder);


			// Execution de la requete
			resultSet = preparedStatement.executeQuery();

			// On ajoute chaque retour de la requete SQL dans notre Map de
			// MealBean
			while (resultSet.next()) {

				showAllOrderItems.add(mapOrderItemBean(resultSet));

			}

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return showAllOrderItems;
	}
	
	private OrderItemBean mapOrderItemBean(ResultSet resultSet) throws SQLException 
	{
		OrderItemBean orderItem = new OrderItemBean();
		
		orderItem.setIdOrderItem(resultSet.getInt("ITM_idOrderItem"));
		orderItem.setIdOrder(resultSet.getInt("ITM_idOrder"));
		orderItem.setIdMeal(resultSet.getInt("ITM_idMeal"));
		orderItem.setQuantity(resultSet.getInt("ITM_quantity"));

		return orderItem;
	}

}
