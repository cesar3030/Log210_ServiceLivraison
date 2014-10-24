package ca.etsmtl.log210.Beans;

/**
 * Bean qui correspond a un item de la commande. Un item c'est un plat et la quantite voulue
 * @author Cesar Jeanroy
 *
 */
public class OrderItemBean 
{
	MealBean meal;
	int quantite;
	
	
	public OrderItemBean() 
	{
		
	}
	
	public OrderItemBean(MealBean meal, int quantity) 
	{
		this.meal=meal;
		this.quantite=quantity;
	}
	public MealBean getMeal() {
		return meal;
	}
	public void setMeal(MealBean plat) {
		this.meal = plat;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
