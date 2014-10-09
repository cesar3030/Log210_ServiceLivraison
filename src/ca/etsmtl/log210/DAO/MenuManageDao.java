package ca.etsmtl.log210.DAO;

import java.util.ArrayList;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Beans.RestaurantBean;

public interface MenuManageDao {

	void addNewMenu(MenuBean menuRecu);

	void deleteMenu(MenuBean menuRecu);

	ArrayList<MenuBean> showAllActiveMenuForOneResto(int restaurantNumber);

	ArrayList<MenuBean> showAllInactiveMenuForOneResto(int restaurantNumber);

}
