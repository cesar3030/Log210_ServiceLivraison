package ca.etsmtl.log210.Beans;

/**
 * Bean qui correspond a un item de la commande. Un item c'est un plat et la quantite voulue
 * @author Cesar Jeanroy
 *
 */
public class OrderItemBean 
{
	
	//Plat voulu
	private MealBean meal;
	
	//Quantite du plat
	private int quantity;
	
	//id de la commande
	private int idOrder;
	
	
	public OrderItemBean() 
	{
		
	}
	
	public OrderItemBean(MealBean meal, int quantity) 
	{
		this.meal=meal;
		this.quantity=quantity;
	}
	public MealBean getMeal() {
		return meal;
	}
	public void setMeal(MealBean plat) {
		this.meal = plat;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantite) {
		this.quantity = quantite;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

}
