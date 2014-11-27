package ca.etsmtl.log210.Beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderBean {

	private int idOrder;
	
	//id du client lie a la commande
	private int idUserAccount;
	
	//Liste des items commandes. Un item est un OrderItemBean contenant le couple mealBean et quantite
	List<OrderItemBean> orderItemsList=new ArrayList<OrderItemBean>();
	
	//Prix total de le commande	
	int totalPrice;
	
	//le statut de la commande 0:non pris en charge, 1:En preparation, 2:prete
	int status;
	
	//Si 0 il s'Ajit de l'adresse contenu dans la table useraccount, celle de son inscription
	private int idAddress;
	
	//Date et heure de livraison
	private String hourAndDate;
	
	//Le code de confirmation de la commande, genere une fois que la commande et les items ont ete cree en bd
	private String confirmationCode;
	
	//identifiant du livreur qui prend en charge la commande
	private int idDeliveryMan;
	
	//Date et heure ou la commande a ete prise en charge par un livreur
	private String dateAcceptedByDeliveryMan;
	
	
	public List<OrderItemBean> getOrderItemsList() {
		return orderItemsList;
	}

	/**
	 * Methode quiset un arraylist au Bean et qui calcule le prix total de la commande
	 * en fonction de ce qui est contenu dans la liste d'items commandes.
	 * @param orderItemsList		Liste contenant les items(mealBean et quantite)
	 */
	public void setOrderItemsList(List<OrderItemBean> orderItemsList) {
		this.orderItemsList = orderItemsList;
		calculTotalPrice(orderItemsList);
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Methode qui calcule le prix total de la commande en fonction de ce qu'il est stocke dans 
	 * la liste d'items commandes 
	 * @param orderItemList
	 */
	public void calculTotalPrice(List<OrderItemBean> orderItemList)
	{
		int totalPrice=0;
		int tmpMealPrice;
		int tmpQuantity;
		
		for(OrderItemBean item : orderItemList)
		{
			//Je recupere le prix du plat et la quantite voulue
			tmpMealPrice=item.getMeal().getPrice();
			tmpQuantity=item.getQuantity();
			
			//Je multiplie leprix du plat par la quantite et je l'ajoute au prix total.
			totalPrice=totalPrice+(tmpMealPrice*tmpQuantity);
		}
		
		this.totalPrice=totalPrice;
	}

	public int getIdUserAccount() {
		return idUserAccount;
	}

	public void setIdUserAccount(int idUserAccount) {
		this.idUserAccount = idUserAccount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	public String getHourAndDate() {
		return hourAndDate;
	}

	public void setHourAndDate(String hourAndDate) {
		this.hourAndDate = hourAndDate;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public int getIdDeliveryMan() {
		return idDeliveryMan;
	}

	public void setIdDeliveryMan(int idLivreur) {
		this.idDeliveryMan = idLivreur;
	}

	public String getDateAcceptedByDeliveryMan() {
		return dateAcceptedByDeliveryMan;
	}

	public void setDateAcceptedByDeliveryMan(String dateAcceptedByDeliveryMan) {
		this.dateAcceptedByDeliveryMan = dateAcceptedByDeliveryMan;
	}


	
	
}
