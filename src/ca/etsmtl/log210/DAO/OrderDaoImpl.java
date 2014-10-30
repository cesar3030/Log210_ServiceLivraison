package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.etsmtl.log210.Beans.OrderBean;

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

}
