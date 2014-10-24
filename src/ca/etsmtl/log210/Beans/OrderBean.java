package ca.etsmtl.log210.Beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderBean {

	//Liste des items commandes. Un item est un OrderItemBean contenant le couple mealBean et quantite
	List<OrderItemBean> orderItemsList=new ArrayList<OrderItemBean>();
	
	//Prix total de le commande	
	int totalPrice;
	
	
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
			tmpQuantity=item.getQuantite();
			
			//Je multiplie leprix du plat par la quantite et je l'ajoute au prix total.
			totalPrice=totalPrice+(tmpMealPrice*tmpQuantity);
		}
		
		this.totalPrice=totalPrice;
	}
	
}
