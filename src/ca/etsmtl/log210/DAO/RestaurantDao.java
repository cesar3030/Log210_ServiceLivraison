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
	 * Methode qui retourne les restaurants innactifs, c'est a dire ceux qui n'ont pas ete supprimés par le gerant.
	 */
	ArrayList<RestaurantBean> getInnactiveRestaurants();
	
	/**
	 * Methode qui ajoute dans la BD un nouveau restaurant
	 * @param newRestaurant
	 * @return True ou false pour savoir si la requete s'est bien executee.
	 */
	boolean addNewRestaurant(RestaurantBean newRestaurant);
	
	/**
	 * La methode change la visibilite du restaurant dont l'identifiant est passe en parametre.
	 * Cela permet de garder un historique des restaurant au lieu de tout supprimer.
	 * Cette methode passe la visibilite du restaurant, de 1 a 0
	 * @param idRestaurant
	 * @return
	 */
	boolean switchToNotVisibleRestaurant(int idRestaurant);
	
	/**
	 * La methode change la visibilite du restaurant dont l'identifiant est passe en parametre.
	 * Cela permet de garder un historique des restaurant au lieu de tout supprimer.
	 * Cette methode passe la visibilite du restaurant, de 0 a 1
	 * @param idRestaurant
	 * @return
	 */
	boolean switchToVisibleRestaurant(int idRestaurant);
}
