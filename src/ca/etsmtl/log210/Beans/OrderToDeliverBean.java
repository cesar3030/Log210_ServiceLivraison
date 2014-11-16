package ca.etsmtl.log210.Beans;
/**
 * Bean permettant de stocker toutes les informatiosn dont le livreur a besoin:
 * 	Le restaurant ou aller chercher la commande
 * 	Les informations concernant le client
 * 	La commande passee par le client
 * 	Une adresse qui n'est pas l'adresse par defaut liee au compte du client (c-a-d, l'adresse avec laquelle il s'est inscrit)
 * 
 * @author Cesar Jeanroy
 *
 */
public class OrderToDeliverBean 
{
	//Le restaurant ou aller chercher la commande
	RestaurantBean restaurant;
	
	//La commande du client
	OrderBean order;

	//Une adresse qui n'est pas l'adresse par defaut li�e au compte du client
	AddressBean address;
	
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
	public AddressBean getAddress() {
		return address;
	}
	public void setAddress(AddressBean otherAddress) {
		this.address = otherAddress;
	}
	public UserAccountBean getClient() {
		return client;
	}
	public void setClient(UserAccountBean client) {
		this.client = client;
	}

}
