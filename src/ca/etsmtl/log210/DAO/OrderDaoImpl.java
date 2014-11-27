package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import ca.etsmtl.log210.Beans.AddressBean;
import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.OrderToDeliverBean;
import ca.etsmtl.log210.Beans.RestaurantBean;
import ca.etsmtl.log210.Beans.UserAccountBean;

public class OrderDaoImpl implements OrderDao {

	private DAOFactory daoFactory;
	private static final String SQL_GET_ORDER = "" + "SELECT * "
			+ "FROM tborder " + "WHERE  ORD_idOrder=?";
	private static final String SQL_NEW_ORDER = "" + "INSERT "
			+ "INTO tborder (ORD_idUserAccount, ORD_address, ORD_date) "
			+ "VALUES (?,?,?)";
	private static final String SQL_SET_CONFIRAMTION_CODE = ""
			+ "UPDATE tborder " + "SET ORD_confirmationCode=? "
			+ "WHERE ORD_idOrder=?";

	private static final String SQL_UPDATE_ORDER_STATE = "" + "UPDATE tborder "
			+ "SET ORD_status=? " + "WHERE ORD_idOrder=?";

	private static final String SQL_GET_0_ORDER = ""
			+ "SELECT DISTINCT ord.ORD_idOrder, ord.ORD_idUserAccount, ord.ORD_address, ord.ORD_date, ord.ORD_idDeliveryMan, ord.ORD_status, ord.ORD_confirmationCode, ord.ORD_dateAcceptedByDeliveryMan "
			+ "FROM tborder ord, tborderitem it, tbplat pl, tbmenu me, tbrestaurant re "
			+ "WHERE  ord.ORD_idOrder = it.ITM_idOrder "
			+ "AND  it.ITM_idMeal = pl.PLA_idPlat "
			+ "AND  pl.PLA_idMenu = me.MEN_idMenu "
			+ "AND  me.MEN_idRestaurant = re.RES_idRestaurant "
			+ "AND  ord.ORD_status = 0 " + "AND  re.RES_idRestaurant = ? "
			+ "ORDER BY ord.ORD_idOrder ";

	private static final String SQL_GET_1_ORDER = ""
			+ "SELECT DISTINCT ord.ORD_idOrder, ord.ORD_idUserAccount, ord.ORD_address, ord.ORD_date, ord.ORD_idDeliveryMan, ord.ORD_status, ord.ORD_confirmationCode, ord.ORD_dateAcceptedByDeliveryMan  "
			+ "FROM tborder ord, tborderitem it, tbplat pl, tbmenu me, tbrestaurant re "
			+ "WHERE  ord.ORD_idOrder = it.ITM_idOrder "
			+ "AND  it.ITM_idMeal = pl.PLA_idPlat "
			+ "AND  pl.PLA_idMenu = me.MEN_idMenu "
			+ "AND  me.MEN_idRestaurant = re.RES_idRestaurant "
			+ "AND ord.ORD_status = 1 " + "AND  re.RES_idRestaurant = ? "
			+ "ORDER BY ord.ORD_idOrder";

	private static final String SQL_GET_2_ORDER = ""
			+ "SELECT DISTINCT ord.ORD_idOrder, ord.ORD_idUserAccount, ord.ORD_address, ord.ORD_date, ord.ORD_idDeliveryMan, ord.ORD_status, ord.ORD_confirmationCode, ord.ORD_dateAcceptedByDeliveryMan  "
			+ "FROM tborder ord, tborderitem it, tbplat pl, tbmenu me, tbrestaurant re "
			+ "WHERE  ord.ORD_idOrder = it.ITM_idOrder "
			+ "AND  it.ITM_idMeal = pl.PLA_idPlat "
			+ "AND  pl.PLA_idMenu = me.MEN_idMenu "
			+ "AND  me.MEN_idRestaurant = re.RES_idRestaurant "
			+ "AND ord.ORD_status = 2 " + "AND  re.RES_idRestaurant = ? "
			+ "ORDER BY ord.ORD_idOrder ";

	private static final String SQL_GET_ORDER_READY_FOR_DELIVERY = ""
			+ "SELECT * "
			+ "FROM tborder ord, tborderitem it, tbplat pl, tbmenu me, tbrestaurant re, tbuseraccount usr, tbotheraddress adr "
			+ "WHERE  ord.ORD_idOrder = it.ITM_idOrder "
			+ "AND  it.ITM_idMeal = pl.PLA_idPlat "
			+ "AND  pl.PLA_idMenu = me.MEN_idMenu "
			+ "AND  adr.ADR_idAddress = ord.ORD_address "
			+ "AND  me.MEN_idRestaurant = re.RES_idRestaurant "
			+ "AND  usr.USR_idUser = ord.ORD_idUserAccount	"
			+ "AND	ord.ORD_status = 2 "
			+ "GROUP BY ord.ORD_idOrder";

	private static final String SQL_UPDATE_SET_DELIVERY_MAN_TO_ORDER = ""
			+ "UPDATE tborder " + "SET ORD_idDeliveryMan=?, ORD_status=?, ORD_dateAcceptedByDeliveryMan=? "
			+ "WHERE ORD_idOrder=? ";

	private static final String SQL_GET_ORDER_WITH_ID = "" + "SELECT * "
			+ "FROM tborder " + "WHERE ORD_idOrder = ?;";

	private static final String SQL_GET_ORDER_BEING_DELIVERED=""
			+ "SELECT * "
			+ "FROM tborder ord, tborderitem it, tbplat pl, tbmenu me, tbrestaurant re, tbuseraccount usr, tbotheraddress adr "
			+ "WHERE  ord.ORD_idOrder = it.ITM_idOrder "
			+ "AND  it.ITM_idMeal = pl.PLA_idPlat "
			+ "AND  pl.PLA_idMenu = me.MEN_idMenu "
			+ "AND  adr.ADR_idAddress = ord.ORD_address "
			+ "AND  me.MEN_idRestaurant = re.RES_idRestaurant "
			+ "AND  usr.USR_idUser = ord.ORD_idUserAccount	"
			+ "AND	ord.ORD_status = 3 "
			+ "AND ord.ORD_idDeliveryMan =? "
			+ "ORDER BY ord.ORD_dateAcceptedByDeliveryMan ASC ";
	
	/**
	 * Methodde qui permet de recuperer la DAO
	 * 
	 * @param daoFactory
	 */
	public OrderDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Methode qui permet d'inserer une nouvelle commande dans la BD param
	 * newOrder ( OrderBean )
	 * 
	 * L orderBEan permet de garder en memoire le choix fais par le client lors
	 * de la commande puis va etre inserer en bd
	 */
	public int newOrder(OrderBean newOrder) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int idOrder = 0;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_NEW_ORDER, true, newOrder.getIdUserAccount(),
					newOrder.getIdAddress(), newOrder.getHourAndDate());
  
			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet != null && resultSet.first()) {
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
		int codeRetour = 0;
		boolean etatRetour = true;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_SET_CONFIRAMTION_CODE, true,
					orderToUpdate.getConfirmationCode(),
					orderToUpdate.getIdOrder());

			System.out.println(preparedStatement);

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

	/**
	 * Methode qui va mettre ajout le statut d un commande
	 * 
	 * @param (int) idOrderRecu (ceci est l'id de la commande)
	 * @param (int) statutRecu (ceci est le statut de base a modifier)
	 * 
	 *        Consequence : le statut de la commande selon l id va etre mis a
	 *        jour
	 */
	public int updateOrderState(int idOrderRecu, int statutRecu) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codeRetour = 0;
		int statutUp = -1;
		// A PREPARER
		if (statutRecu == 0) {
			statutUp = 1;
		}
		// Prete a etre livrer
		else if (statutRecu == 1) {
			statutUp = 2;
		}
		// livraison en cours
		else if (statutRecu == 2) {
			statutUp = 3;
		}
		// LIVREE
		else if (statutRecu == 3) {
			statutUp = 4;
		}

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE_ORDER_STATE, true, statutUp, idOrderRecu);

			System.out.println(preparedStatement);

			codeRetour = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return statutUp;

	}

	/**
	 * Methode qui permet de recuprer depuis la BDD la liste de commande a
	 * preparer selon le numero de restaurant
	 * 
	 * @param (int) idRestaurant
	 * 
	 * @return (ArrayList<OrderBean>) ListeOrder liste de commande
	 */
	public ArrayList<OrderBean> getListOrder0(int idRestaurant) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_0_ORDER, false, idRestaurant);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) {
				orderList.add(mapOrder(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return orderList;
	}

	/**
	 * Methode qui permet de recuprer depuis la BDD la liste de commande en
	 * preparation selon le numero de restaurant
	 * 
	 * @param (int) idRestaurant
	 * 
	 * @return (ArrayList<OrderBean>) ListeOrder liste de commande
	 */
	public ArrayList<OrderBean> getListOrder1(int idRestaurant) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_1_ORDER, false, idRestaurant);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) {
				orderList.add(mapOrder(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return orderList;
	}

	/**
	 * Methode qui permet de recuprer depuis la BDD la liste de commande a
	 * terminees selon le numero de restaurant
	 * 
	 * @param (int) idRestaurant
	 * 
	 * @return (ArrayList<OrderBean>) ListeOrder liste de commande
	 */
	public ArrayList<OrderBean> getListOrder2(int idRestaurant) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_2_ORDER, false, idRestaurant);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) {
				orderList.add(mapOrder(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return orderList;
	}

	public static OrderBean mapOrder(ResultSet resultSet) throws SQLException {
		OrderBean order = new OrderBean();

		order.setIdOrder(resultSet.getInt("ORD_idOrder"));
		order.setIdUserAccount(resultSet.getInt("ORD_idUserAccount"));
		order.setIdAddress(resultSet.getInt("ORD_address"));
		order.setConfirmationCode(resultSet.getString("ORD_confirmationCode"));
		order.setHourAndDate(resultSet.getString("ORD_date"));
		order.setStatus(resultSet.getInt("ORD_status"));
		order.setIdDeliveryMan(resultSet.getInt("ORD_idDeliveryMan"));
		order.setDateAcceptedByDeliveryMan(resultSet.getString("ORD_dateAcceptedByDeliveryMan"));
	
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
			while (resultSet.next()) {
				orderToDeliver.add(mapOrderToDeliver(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return orderToDeliver;
	}

	public static OrderToDeliverBean mapOrderToDeliver(ResultSet resultSet)
			throws SQLException {
		OrderToDeliverBean orderToDeliver = new OrderToDeliverBean();

		OrderBean order = mapOrder(resultSet);
		RestaurantBean restaurant = RestaurantDaoImpl
				.mapRestaurateur(resultSet);
		UserAccountBean client = UserAccountDaoImpl.mapUserAccount(resultSet);

		orderToDeliver.setClient(client);
		orderToDeliver.setOrder(order);
		orderToDeliver.setRestaurant(restaurant);

		/**
		 * Si l'id de l'adresse de livraison est 0, celaveut dire qu'il s'agit
		 * de l'adresse contenue dans le compte de l'utilisateur
		 */
		if (order.getIdAddress() == 0) {
			AddressBean accountAddress = new AddressBean(
					client.getHomeAddress(), client.getUserId());
			orderToDeliver.setAddress(accountAddress);
		} else {
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
					SQL_GET_ORDER_WITH_ID, false, idOrder);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				// Si le statut de la livraison est livraison en cours ou
				// termin�, on renvoie false
				if (resultSet.getInt("ORD_status") >= 3) {
					canBeAccepted = false;
				}
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return canBeAccepted;
	}

	@Override
	public int assignOrderToDelileveryMan(int idOrder, int idDeliveryMan) {

		int errorCode = 2;
		boolean continueTraiment;

		continueTraiment = checkOrderNotAcceptedYet(idOrder);

		if (continueTraiment == true) {
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			int codeRetour = 0;
			
			String currentTime = getCurrentDate();

			try {
				/* Recuperation d'une connexion depuis la Factory */
				connexion = daoFactory.getConnection();
				preparedStatement = initialisationRequetePreparee(connexion,
						SQL_UPDATE_SET_DELIVERY_MAN_TO_ORDER, true,
						idDeliveryMan, 3, currentTime, idOrder);

				System.out.println(preparedStatement);
				codeRetour = preparedStatement.executeUpdate();

				// Si la requete SQL s'est bien termin�e, je set le code erreur
				// 0 signifiant que tout a fonctionner
				if (codeRetour >= 0) {
					errorCode = 0;
				}

			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				fermeturesSilencieuses(resultSet, preparedStatement, connexion);
			}

		} else {
			errorCode = 1;
		}

		return errorCode;
	}

	
	public String getCurrentDate() 
	{
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Calendar cal = Calendar.getInstance();
		
		String currentTime = sdf.format(cal.getTime());
		
		return currentTime;
	}

	@Override
	public OrderBean getOrder(int idOrder) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		OrderBean orderTmp = new OrderBean();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ORDER, true, idOrder);
			
			System.out.println(preparedStatement);
			
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				orderTmp = mapOrder(resultSet);
			}
			
			

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return orderTmp;
	}

	@Override
	public ArrayList<OrderToDeliverBean> getListOrdersAcceptedOrderToBeDelivered(
			UserAccountBean deliveryMan) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderToDeliverBean> orderToDeliver = new ArrayList<OrderToDeliverBean>();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ORDER_BEING_DELIVERED, false, deliveryMan.getUserId());

			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) {
				orderToDeliver.add(mapOrderToDeliver(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return orderToDeliver;

	}
}
