package ca.etsmtl.log210.Beans;

/**
 * Bean qui correspond a un item de la commande. Un item c'est un plat et la
 * quantite voulue
 * 
 * @author Cesar Jeanroy
 *
 */
public class OrderItemBean {

	// Plat voulu
	private MealBean meal;

	private int idMeal;

	// Quantite du plat
	private int quantity;

	// id de la commande
	private int idOrder;

	// id de la commande
	private int idOrderItem;

	public OrderItemBean() {

	}

	public OrderItemBean(MealBean meal, int quantity) {
		this.meal = meal;
		this.quantity = quantity;
	}

	public OrderItemBean(int idOrder, MealBean meal, int quantity) {
		this.meal = meal;
		this.quantity = quantity;
		this.idOrder = idOrder;
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

	/**
	 * @return the idOrderItem
	 */
	public int getIdOrderItem() {
		return idOrderItem;
	}

	/**
	 * @param idOrderItem
	 *            the idOrderItem to set
	 */
	public void setIdOrderItem(int idOrderItem) {
		this.idOrderItem = idOrderItem;
	}

	/**
	 * @return the idMeal
	 */
	public int getIdMeal() {
		return idMeal;
	}

	/**
	 * @param idMeal
	 *            the idMeal to set
	 */
	public void setIdMeal(int idMeal) {
		this.idMeal = idMeal;
	}

}
