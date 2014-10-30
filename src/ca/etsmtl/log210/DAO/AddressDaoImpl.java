package ca.etsmtl.log210.DAO;

import static ca.etsmtl.log210.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static ca.etsmtl.log210.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ca.etsmtl.log210.Beans.AddressBean;
import ca.etsmtl.log210.Beans.RestaurantBean;

public class AddressDaoImpl implements AddressDao{

	private DAOFactory daoFactory;
	private static final String SQL_NEW_ADDRESS=""
			+ "INSERT "
			+ "INTO tbotheraddress (ADR_idUserAccount, ADR_address) "
			+ "VALUES (?,?)";
	
	private static final String SQL_GET_ADDRESS_WITH_ID=""
			+ "SELECT * "
			+ "FROM tbotheraddress "
			+ "WHERE ADR_idAddress= ?";
	
	private static final String SQL_GET_ADDRESS_WITH_ID_USER_ACCOUNT=""
			+ "SELECT * "
			+ "FROM tbotheraddress "
			+ "WHERE ADR_idUserAccount= ?";
	
	public AddressDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public int newAddress(String newAddress, int idUserAccount) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int idAddress=0;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_NEW_ADDRESS,true,idUserAccount, newAddress);

			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();
			
			resultSet = preparedStatement.getGeneratedKeys();
			
			if (resultSet != null && resultSet.first()) 
			{
			      // on récupère l'id généré
			      idAddress = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return idAddress;
	}

	@Override
	public AddressBean getAddress(int idAddress) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AddressBean address = null;
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ADDRESS_WITH_ID, false,idAddress);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				address=mapAddress(resultSet);
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
		return address;
	}
	
	private AddressBean mapAddress(ResultSet resultSet) throws SQLException 
	{
		AddressBean address = new AddressBean();
		
		address.setIdAddress(resultSet.getInt("ADR_idAddress"));
		address.setAddress(resultSet.getString("ADR_address"));
		address.setIdUserAccount(resultSet.getInt("ADR_idUserAccount"));
		
		return address;
	}


	@Override
	public ArrayList<AddressBean> getAllAddressOfUser(int idUserAccount) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<AddressBean> listAddress = new ArrayList<AddressBean>();
		
		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_GET_ADDRESS_WITH_ID_USER_ACCOUNT, false,idUserAccount);

			resultSet = preparedStatement.executeQuery();

			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while (resultSet.next()) 
			{
				 listAddress.add(mapAddress(resultSet));
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
		return listAddress;
	}
	

}
