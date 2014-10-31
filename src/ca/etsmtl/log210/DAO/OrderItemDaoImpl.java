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
import ca.etsmtl.log210.Beans.OrderDetailsItemsBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.RestaurantBean;

public class OrderItemDaoImpl implements OrderItemDao 
{
	private DAOFactory daoFactory;
	static final String SQL_NEW_ORDERITEM=""
			+ "INSERT "
			+ "INTO tborderitem( ITM_idMeal, ITM_quantity, ITM_idOrder ) "
			+ "VALUES ( ?, ?, ? )";
	
	static final String SQL_ALL_ORDERITEM=""
			+ "SELECT pl.PLA_name,pl.PLA_idPlat,pl.PLA_description,oi.ITM_idOrder,oi.ITM_quantity,pl.PLA_price "
			+ "FROM tborderitem oi, tborder ord, tbPlat pl "
			+ "WHERE oi.ITM_idOrder=ord.ORD_idOrder "
			+ "AND oi.ITM_idMeal=pl.PLA_idPlat "
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
	public ArrayList<OrderDetailsItemsBean> showAllOrderItem(int idOrder) {
		System.out.println("je vais aller chercher les items pour les afficher");
		Connection connexion = null;
		System.out.println("je vais aller chercher les items pour les afficher");
		PreparedStatement preparedStatement = null;
		System.out.println("je vais aller chercher les items pour les afficher");
		ResultSet resultSet = null;

		ArrayList<OrderDetailsItemsBean> showAllOrderItems = new ArrayList<OrderDetailsItemsBean>();
		System.out.println("je vais aller chercher les items pour les afficher");
		try {
			/* Faire une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			System.out.println(daoFactory.getConnection().toString());

			// Preparation de la requete
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_ALL_ORDERITEM, false, idOrder);

			System.out.println(preparedStatement);

			// Execution de la requete
			resultSet = preparedStatement.executeQuery();

			// On ajoute chaque retour de la requete SQL dans notre Map de
			// MealBean
			while (resultSet.next()) {

				showAllOrderItems.add(mapOrderEnDetailItems(resultSet));

			}
			
			

		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return showAllOrderItems;
	}
	
	private OrderDetailsItemsBean mapOrderEnDetailItems(ResultSet resultSet) throws SQLException{
		
		//OrderDetailsItemsBean(String name, String description, int quantity,int price,int idOrderItem,int idPlat)
		//PLA_name,pl.PLA_idPlat,pl.PLA_description,oi.ITM_idOrder,oi.ITM_quantity,pl.PLA_price
		OrderDetailsItemsBean detailItems = new OrderDetailsItemsBean(resultSet.getString("PLA_name"),
																	resultSet.getString("PLA_description"),
																	resultSet.getInt("ITM_quantity"),
																	resultSet.getInt("PLA_price"),
																	resultSet.getInt("ITM_idOrder"),
																	resultSet.getInt("PLA_idPlat"));
		System.out.println(resultSet.getString("PLA_name")+" "+
			 resultSet.getString("PLA_description")+" "+
				resultSet.getInt("ITM_quantity")+" "+
				resultSet.getInt("PLA_price")+" "+
				resultSet.getInt("ITM_idOrder")+" "+
				resultSet.getInt("PLA_idPlat"));
		return detailItems;
	}
	
	private OrderItemBean mapOrderItemBean(ResultSet resultSet) throws SQLException 
	{
		OrderItemBean orderItem = new OrderItemBean();
		System.out.println(resultSet);
		orderItem.setIdOrderItem(resultSet.getInt("PLA_name"));
		orderItem.setIdOrder(resultSet.getInt("PLA_description"));
		orderItem.setIdMeal(resultSet.getInt("ITM_quantity"));
		orderItem.setQuantity(resultSet.getInt("PLA_price"));

		return orderItem;
	}

}
