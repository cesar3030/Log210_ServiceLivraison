package ca.etsmtl.log210.Beans;


public class RestaurantBean 
{
	private int idResaturant;
	private int idUserAccountRestaurateur;	//L'identifiant du restaurateur en charge de ce restaurant
	private String address;
	private String phoneNumber;
	private String kindOfFood;
	private boolean visible;	//Si visible = 1 cela veut dire que le restaurant est actif. Si la valeur est 0, cela veut dire que le resaurant est cache car il a ete supprimie.
	
	
	public int getIdResaturant() {
		return idResaturant;
	}

	public void setIdResaturant(int idResaturant) {
		this.idResaturant = idResaturant;
	}

	public int getIdUserAccountRestaurateur() {
		return idUserAccountRestaurateur;
	}

	public void setIdUserAccountRestaurateur(int idUserAccountRestaurateur) {
		this.idUserAccountRestaurateur = idUserAccountRestaurateur;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getKindOfFood() {
		return kindOfFood;
	}

	public void setKindOfFood(String kindOfFood) {
		this.kindOfFood = kindOfFood;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
