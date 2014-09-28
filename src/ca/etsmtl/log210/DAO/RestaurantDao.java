package ca.etsmtl.log210.DAO;

import java.util.ArrayList;

import ca.etsmtl.log210.Beans.RestaurantBean;

public interface RestaurantDao 
{
	/**
	 * Methode qui retourne les restaurants actifs, c'est a dire ceux qui n'ont pas ete rendu invisible par le gerant.
	 */
	ArrayList<RestaurantBean> getActiveRestaurants();
	
	/**
	 * Methode qui retourne les restaurants innactifs, c'est a dire ceux qui n'ont pas été supprimés par le gerant.
	 */
	ArrayList<RestaurantBean> getInnactiveRestaurants();
	
}
