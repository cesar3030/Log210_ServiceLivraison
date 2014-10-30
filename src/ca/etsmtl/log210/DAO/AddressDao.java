package ca.etsmtl.log210.DAO;

import java.util.ArrayList;

import ca.etsmtl.log210.Beans.AddressBean;

public interface AddressDao 
{
	/**
	 * Methode qui ajoute une nouvelle adresse a un utilisateur
	 * @param newAdresse			la nouvelle adresse
	 * @param idUserAccount		l'identifiant de l'utilisateur
	 * @return l'id de la nouvelle adresse
	 */
	int newAddress(String newAddress,int idUserAccount);
	
	/**
	 * Methode qui retourne toutes les informations d'une adresse grace a son identifiant
	 * @param idAddress	identifiant de l'adresse
	 * @return					AddressBean contenant toutes les informations de l'adresse voulue
	 */
	AddressBean getAddress(int idAddress);
	
	/**
	 * Methode qui retourne toutes les adresses supplementaires d'un utilisateur
	 * @param idUser		L'utilisateur dont on veut les adresses
	 * @return				Les adresses
	 */
	ArrayList<AddressBean> getAllAddressOfUser(int idUserAccount);

}
