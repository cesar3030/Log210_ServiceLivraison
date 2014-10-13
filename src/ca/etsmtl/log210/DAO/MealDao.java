package ca.etsmtl.log210.DAO;

import java.util.ArrayList;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.MenuBean;

public interface MealDao {
	
/**
 * Definition de la methode qui va ajouter les plats
 * @param mealRecept bean du plat a supprimer
 * @return
 */
	public boolean addNewMeal(MealBean mealRecept);
	
	/**
	 * Definition de la methode qui va supprimer les plats
	 * @param idMeal id du plat a supprimer
	 * @return
	 */
	public boolean deleteNewMeal(int idMeal);
	
	/**
	 * Definition de la methode qui va afficher les bean de la BDD
	 * @param idMenu
	 * @return
	 */
	ArrayList<MealBean> showAllMealFromMenu(int idMenu);


}
