package ca.etsmtl.log210.Beans;

/**
 * Bean qui represente un plat dans la BDD
 * 
 * @author David
 *
 */
public class MealBean {

	private int idMeal;
	private int idMenu;
	private int price;
	private String name;
	private String description;

	public MealBean(int id) 
	{
		idMeal=id;
	}

	public MealBean() {
		
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the idMenu
	 */
	public int getIdMenu() {
		return idMenu;
	}

	/**
	 * @param idMenu
	 *            the idMenu to set
	 */
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	/**
	 * @return the idMeal
	 */
	public int getIdMeal() {
		return idMeal;
	}

	/**
	 * @param idMeal the idMeal to set
	 */
	public void setIdMeal(int idMeal) {
		this.idMeal = idMeal;
	}


}