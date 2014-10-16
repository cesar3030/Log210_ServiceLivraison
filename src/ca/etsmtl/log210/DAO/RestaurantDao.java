
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
	 * @return true si l'update s'est bien fait, false si il y a eu un soucis
	 */
	boolean switchToNotVisibleRestaurant(int idRestaurant);
	
	/**
	 * La methode change la visibilite du restaurant dont l'identifiant est passe en parametre.
	 * Cela permet de garder un historique des restaurant au lieu de tout supprimer.
	 * Cette methode passe la visibilite du restaurant, de 0 a 1
	 * @param idRestaurant
	 * @return true si l'update s'est bien fait, false si il y a eu un soucis
	 */
	boolean switchToVisibleRestaurant(int idRestaurant);
	
	/**
	 * Update la table restaurant avec les valeurs du restaurant contenues dans le RestaurantBean
	 * @param restaurantToUpdate
	 * @return true si l'update s'est bien fait, false si il y a eu un soucis
	 */
	boolean updateResataurant(RestaurantBean restaurantToUpdate);
	
	/**
	 * Methode qui retourne les informations completes du restaurant dont l'id est passe en parametre
	 * @param idRestaurantReceved  		L'identifiant du restaurant dont on veut les informations
	 * @return 										Un restaurantBean contenant toutes les informations du restaurant
	 */
	RestaurantBean getNomRestaurant(int idRestaurantReceved);
	
	/**
	 * Methode qui retourne la liste des restaurants assignes a un restaurateur
	 * @param idRestaurateur		Id du restaurateur
	 * @return 							ArrayList contenant les restaurants du restaurateur
	 */
	ArrayList<RestaurantBean> getActiveRestaurantsForRestaurateur(int idRestaurateur);
	
	/**
	 * Cette methode va chercher la liste des restaurants visibles. Cette methode est utilisee 
	 * pour peupler la liste deroullante du formulaire d'inscription d'un nouveau restaurant. 
	 * @return La liste des restaurants qui n'ont pas de restaurateur
	 */
	ArrayList<RestaurantBean> getListRestaurantsWithoutRestaurateur();
	
	/**
	 * Methode qui va updater le restaurateur lie au restaurant
	 * @param idRestaurant  	restaurant a modifier
	 * @return 						true si le changement s'est bien termine, false sinon
	 */
	boolean linkRestaurateurToARestaurant(int idRestaurant,int idRestaurateur);

}
