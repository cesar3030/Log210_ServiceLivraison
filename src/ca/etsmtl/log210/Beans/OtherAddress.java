package ca.etsmtl.log210.Beans;
/**
 * Bean permettant de stocker une adresses supplementaires d'un client.
 * @author Cesar Jeanroy
 */
public class OtherAddress 
{
	//l'id de l'adresse
	private int idOtherAdrress;
	
	//identifiant du client associe a l'adresse
	private int idUserAccount;
	
	//l'adresse
	private String address;
	
	
	public int getIdOtherAdrress() {
		return idOtherAdrress;
	}
	public void setIdOtherAdrress(int idOtherAdrress) {
		this.idOtherAdrress = idOtherAdrress;
	}
	public int getIdUserAccount() {
		return idUserAccount;
	}
	public void setIdUserAccount(int idUserAccount) {
		this.idUserAccount = idUserAccount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
