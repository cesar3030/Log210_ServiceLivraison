package ca.etsmtl.log210.DAO;

import java.util.ArrayList;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.MenuBean;

public interface MealDao {
	

	public boolean addNewMeal(MealBean mealRecept);
	public boolean deleteNewMeal(int idMeal);
	
	ArrayList<MealBean> showAllMealFromMenu(int idMenu);


}
