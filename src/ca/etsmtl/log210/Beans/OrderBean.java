package ca.etsmtl.log210.Beans;

import java.util.HashMap;
import java.util.Map;

public class OrderBean {

	//Liste des paires: idMeal et quantite voulu
	Map<MealBean, Integer> mealListAndQuantity= new HashMap<MealBean, Integer>();
	
	//Prix total de le commande	
	int totalPrice;
	
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Map<MealBean, Integer> getMealListAndQuantity() {
		return mealListAndQuantity;
	}

	public void setMealListAndQuantity(Map<MealBean, Integer> mealListAndQuantity) {
		this.mealListAndQuantity = mealListAndQuantity;
	}
}
