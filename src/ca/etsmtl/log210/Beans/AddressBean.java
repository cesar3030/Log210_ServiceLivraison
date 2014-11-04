package ca.etsmtl.log210.Beans;
/**
 * Bean servant a stocker une adresse supplementaire ajoute 
 * par un client lors du choix de l'adresse de livraison
 * 
 * @author Cesar Jeanroy
 */
public class AddressBean 
{
	private int idAddress;
	private String address;
	private int idUserAccount;
	
	
	public AddressBean() 
	{
		
	}
	
	public AddressBean(String homeAddress, int idUserAccont) 
	{
		idAddress = 0;
		address = homeAddress ;
		idUserAccount = idUserAccont;
	}
	public int getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIdUserAccount() {
		return idUserAccount;
	}
	public void setIdUserAccount(int idUserAccount) {
		this.idUserAccount = idUserAccount;
	}
	
	

}
