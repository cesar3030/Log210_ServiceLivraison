package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ca.etsmtl.log210.Beans.AddressBean;
import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.OrderToDeliverBean;
import ca.etsmtl.log210.Beans.RestaurantBean;
import ca.etsmtl.log210.Beans.UserAccountBean;

public class OrderDaoImpl implements OrderDao
{

	private DAOFactory daoFactory;
	private static final String SQL_NEW_ORDER=""
			+ "INSERT "
			+ "INTO tborder (ORD_idUserAccount, ORD_address, ORD_date) "
			+ "VALUES (?,?,?)";
	private static final String SQL_SET_CONFIRAMTION_CODE=""
			+ "UPDATE tborder "
			+ "SET ORD_confirmationCode=? "
			+ "WHERE ORD_idOrder=?";
	
	private static final String SQL_UPDATE_ORDER_STATE = "" 
			+ "UPDATE tborder "
			+ "SET ORD_status=? "
			+ "WHERE ORD_idOrder=?";
	
	private static final String SQL_GET_0_ORDER = ""
			+ "SELECT * "
			+ "FROM tborder ord, tborderitem it, tbplat pl, tbmenu me, tbrestaurant re "
			+ "WHERE  ord.ORD_idOrder = it.ITM_idOrder "
			+ "AND  it.ITM_idMeal = pl.PLA_idPlat "
			+ "AND  pl.PLA_idMenu = me.MEN_idMenu "
			+ "AND  me.MEN_idRestaurant = re.RES_idRestaurant "
			+ "AND  ord.ORD_status = 0 " 
			+ "AND  re.RES_idRestaurant = ?";
	
	private static final String SQL_GET_1_ORDER = ""
			+ "SELECT * "
			+ "FROM tborder ord, tborderitem it, tbplat pl, tbmenu me, tbrestaurant re "
			+ "WHERE  ord.ORD_idOrder = it.ITM_idOrder "
			+ "AND  it.ITM_idMeal = pl.PLA_idPlat "
			+ "AND  pl.PLA_idMenu = me.MEN_idMenu "
			+ "AND  me.MEN_idRestaurant = re.RES_idRestaurant "
			+ "AND ord.ORD_status = 1 " 
			+ "AND  re.RES_idRestaurant = ?";
	
	private static final String SQL_GET_2_ORDER = ""
			+ "SELECT * "
			+ "FROM tborder ord, tborderitem it, tbplat pl, tbmenu me, tbrestaurant re "
			+ "WHERE  ord.ORD_idOrder = it.ITM_idOrder "
			+ "AND  it.ITM_idMeal = pl.PLA_idPlat "
			+ "AND  pl.PLA_idMenu = me.MEN_idMenu "
			+ "AND  me.MEN_idRestaurant = re.RES_idRestaurant "
			+ "AND ord.ORD_status = 2 " 
			+ "AND  re.RES_idRestaurant = ?";
	
	private static final String SQL_GET_ORDER_READY_FOR_DELIVERY = ""
			+ "SELECT * "
			+ "FROM tborder ord, tborderitem it, tbplat pl, tbmenu me, tbrestaurant re, tbuseraccount usr, tbotheraddress adr "
			+ "WHERE  ord.ORD_idOrder = it.ITM_idOrder "
			+ "AND  it.ITM_idMeal = pl.PLA_idPlat "
			+ "AND  pl.PLA_idMenu = me.MEN_idMenu "
			+ "AND  adr.ADR_idAddress = ord.ORD_address "
			+ "AND  me.MEN_idRestaurant = re.RES_idRestaurant " 
			+ "AND  usr.USR_idUser = ord.ORD_idUserAccount	"
			+ "AND	ord.ORD_status = 2 ";
	
	private static final String SQL_UPDATE_SET_DELIVERY_MAN_TO_ORDER = ""
			+ "UPDATE tborder "
			+ "SET ORD_idDeliveryMan=?, ORD_status=? "
			+ "WHERE ORD_idOrder=?";
	
	private static final String SQL_GET_ORDER_WITH_ID = ""
			+ "SELECT * "
			+ "FROM tborder "
			+ "WHERE ORD_idOrder = ?;";
			
	
	public OrderDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public int newOrder(OrderBean newOrder) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int idOrder=0;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_NEW_ORDER,true,newOrder.getIdUserAccount(),newOrder.getIdAddress(),newOrder.getHourAndDate());

			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();
			
			resultSet = preparedStatement.getGeneratedKeys();
			
			if (resultSet != null && resultSet.first()) 
			{
			      // on récupère l'id généré
			      idOrder = resultSet.getInt(1);
			}
			
			

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return idOrder;
	}

	@Override
	public boolean setConfirmationCode(OrderBean orderToUpdate) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		boolean etatRetour=true;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_SET_CONFIRAMTION_CODE,true,orderToUpdate.getConfirmationCode(), orderToUpdate.getIdOrder());

			System.out.println(preparedStatement);

			codeRetour=preparedStatement.executeUpdate();
			
			
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
	public int updateOrderState(int idOrderRecu, int statutRecu) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour=0;
		int statutUp=0;
		// A PREPARER
		if(statutRecu==0){
			statutUp=1;
		}
		// Prete a etre livrer
		else if(statutRecu==1){
			statutUp=2;
		}
		//livraison en cours
		else if(statutRecu==2){
			statutUp=3;
		}
		// LIVREE
		else if(statutRecu==3){
			statutUp=4;
		}

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
																SQL_UPDATE_ORDER_STATE,
																true,
																statutUp,
																idOrderRecu);
																

			System.out.println(preparedStatement);

			
			codeRetour = preparedStatement.executeUpdate();
			
			

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return codeRetour;

	}

	@Override
	public ArrayList<OrderBean> getListOrder0(int idRestaurant) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_0_ORDER, false,idRestaurant);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				orderList.add(mapOrder(resultSet));
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
		return orderList;
	}

	@Override
	public ArrayList<OrderBean> getListOrder1(int idRestaurant) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_1_ORDER, false,idRestaurant);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				orderList.add(mapOrder(resultSet));
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
		return orderList;
	}

	@Override
	public ArrayList<OrderBean> getListOrder2(int idRestaurant){
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_2_ORDER, false,idRestaurant);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				orderList.add(mapOrder(resultSet));
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
		return orderList;
	}
	
	public static OrderBean mapOrder(ResultSet resultSet) throws SQLException 
	{
		OrderBean order = new OrderBean();
		
		order.setIdOrder(resultSet.getInt("ORD_idOrder"));
		order.setIdUserAccount(resultSet.getInt("ORD_idUserAccount"));
		order.setIdAddress(resultSet.getInt("ORD_address"));
		order.setConfirmationCode(resultSet.getString("ORD_confirmationCode"));
		order.setHourAndDate(resultSet.getString("ORD_date"));
		order.setStatus(resultSet.getInt("ORD_status"));
		order.setIdDeliveryMan(resultSet.getInt("ORD_idDeliveryMan"));

		return order;
	}

	@Override
	public ArrayList<OrderToDeliverBean> getListOrdersReadyForDelivery() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderToDeliverBean> orderToDeliver = new ArrayList<OrderToDeliverBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ORDER_READY_FOR_DELIVERY, false);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				orderToDeliver.add(mapOrderToDeliver(resultSet));
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
		return orderToDeliver;
	}

	public static OrderToDeliverBean mapOrderToDeliver(ResultSet resultSet) throws SQLException  
	{
		OrderToDeliverBean orderToDeliver = new OrderToDeliverBean();
		
		OrderBean order = mapOrder(resultSet);
		RestaurantBean restaurant = RestaurantDaoImpl.mapRestaurateur(resultSet);
		UserAccountBean client = UserAccountDaoImpl.mapUserAccount(resultSet);

		orderToDeliver.setClient(client);
		orderToDeliver.setOrder(order);
		orderToDeliver.setRestaurant(restaurant);
		
		/**
		 * Si l'id de l'adresse de livraison est 0, celaveut dire 
		 * qu'il s'agit de l'adresse contenue dans le compte de l'utilisateur
		 */
		if(order.getIdAddress()==0)
		{
			AddressBean accountAddress = new AddressBean(client.getHomeAddress(), client.getUserId());
			orderToDeliver.setAddress(accountAddress);
		}
		else
		{
			AddressBean otherAddress = AddressDaoImpl.mapAddress(resultSet);
			orderToDeliver.setAddress(otherAddress);
		}
		
		return orderToDeliver;
	}

	@Override
	public boolean checkOrderNotAcceptedYet(int idOrder) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean canBeAccepted = true;
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ORDER_WITH_ID, false,idOrder);

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) 
			{
				//Si le statut de la livraison est livraison en cours ou termin�, on renvoie false
				if(resultSet.getInt("ORD_status")>=3)
				{
					canBeAccepted = false;
				}
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
		return canBeAccepted;
	}

	@Override
	public int assignOrderToDelileveryMan(int idOrder, int idDeliveryMan) {
		
		int errorCode=2;
		boolean continueTraiment;
		
		continueTraiment = checkOrderNotAcceptedYet(idOrder);
		
		if(continueTraiment==true)
		{
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			int codeRetour=0;
			
			try {
				/* Recuperation d'une connexion depuis la Factory */
				connexion = daoFactory.getConnection();
				preparedStatement = initialisationRequetePreparee(connexion,SQL_UPDATE_SET_DELIVERY_MAN_TO_ORDER,true,idDeliveryMan,3,idOrder);
				
				System.out.println(preparedStatement);
				codeRetour = preparedStatement.executeUpdate();
				
				//Si la requete SQL s'est bien termin�e, je set le code erreur 0 signifiant que tout a fonctionner
				if( codeRetour >= 0)
				{
					errorCode=0;
				}

			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				fermeturesSilencieuses(resultSet, preparedStatement, connexion);
			}
			
		}
		else
		{
			errorCode = 1;
		}
			
		return errorCode;
	}

}
