package ca.etsmtl.log210.Beans;
/**
 * Bean permettant de stocker toutes les informatiosn dont le livreur a besoin:
 * 	Le restaurant ou aller chercher la commande
 * 	Les informations concernant le client
 * 	La commande passee par le client
 * 	Une adresse qui n'est pas l'adresse par defaut li�e au compte du client (adresse avec laquelle il s'est inscrit)
 * 
 * @author Cesar Jeanroy
 *
 */
public class OrderToDeliver 
{
	//Le restaurant ou aller chercher la commande
	RestaurantBean restaurant;
	
	//La commande du client
	OrderBean order;

	//Une adresse qui n'est pas l'adresse par defaut li�e au compte du client
	OtherAddress otherAddress;
	
	//Les informations concernant le client
	UserAccountBean client;
	
	
	public RestaurantBean getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantBean restaurant) {
		this.restaurant = restaurant;
	}
	public OrderBean getOrder() {
		return order;
	}
	public void setOrder(OrderBean order) {
		this.order = order;
	}
	public OtherAddress getOtherAddress() {
		return otherAddress;
	}
	public void setOtherAddress(OtherAddress otherAddress) {
		this.otherAddress = otherAddress;
	}
	public UserAccountBean getClient() {
		return client;
	}
	public void setClient(UserAccountBean client) {
		this.client = client;
	}

}
