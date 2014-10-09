package ca.etsmtl.log210.Beans;


public class RestaurantBean 
{
	private int idRestaurant;

	private int idUserAccountRestaurateur;	//L'identifiant du restaurateur en charge de ce restaurant
	private String name;
	private String address;
	private String phoneNumber;
	private String kindOfFood;
	private String restaurateurName;
	private boolean visible;	//Si visible = 1 cela veut dire que le restaurant est actif. Si la valeur est 0, cela veut dire que le resaurant est cache car il a ete supprimie.
	
	
	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
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
	
	public int getIdRestaurant() {
		return idRestaurant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRestaurateurName() {
		return restaurateurName;
	}

	public void setRestaurateurName(String restaurateurName) {
		this.restaurateurName = restaurateurName;
	}

	@Override
	public String toString() {
		return "RestaurantBean idRestaurant:\n\t- " + idRestaurant
				+ ": idUserAccountRestaurateur:\n\t- "
				+ idUserAccountRestaurateur + ": name:\n\t- " + name
				+ ": address:\n\t- " + address + ": phoneNumber:\n\t- "
				+ phoneNumber + ": kindOfFood:\n\t- " + kindOfFood
				+ ": restaurateurName:\n\t- " + restaurateurName
				+ ": visible:\n\t- " + visible;
	}

	
	


}
