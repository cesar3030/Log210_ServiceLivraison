package ca.etsmtl.log210.DAO;

import java.util.ArrayList;

import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderToDeliverBean;
import ca.etsmtl.log210.Beans.UserAccountBean;

public interface OrderDao 
{
	/**
	 * Methode qui ajoute dans la Bd une nouvelle commande
	 * @param newOrder	Bean contenant les plats, la quantite de chaqun et l'utilisateur lie a la commande 
	 * @return					l'identifiant de la commande venant d'etre cree
	 */
	int newOrder(OrderBean newOrder);
	
	/**
	 * Methode qui va setter le code de confirmation qui a ete genere et stocke dans le orderBean une fois que tous les items
	 * ont ete crees et lies a la commande.
	 * @param 	orderToUpdate: Le bean qu'il faut updater en BD pour setter le numero de confirmation qu'il contient
	 * @return	True si l'operation s'est bien faite False si il y a eu un probleme.
	 */
	boolean setConfirmationCode(OrderBean orderToUpdate);
	
	int updateOrderState(int idOrderRecu, int statutRecu);
	
	ArrayList<OrderBean> getListOrder0(int idRestaurant);
	ArrayList<OrderBean> getListOrder1(int idRestaurant);
	ArrayList<OrderBean> getListOrder2(int idRestaurant);
	
	/**
	 * Methode qui retourne la liste des commandes pretes a etre prises en charge par un livreur.
	 * @return	La liste des commandes � livrer avec toutes les informations requises par le livreur
	 */
	ArrayList<OrderToDeliverBean> getListOrdersReadyForDelivery();
	
	/**
	 * Methode qui retourne la liste des commandes acceptees par le livreur et qui sont en cours de livraison.
	 * @param 	Le livreur dont on veut recuperer les commandes dont il est en charge
	 * @return	La liste des commandes � livrer avec toutes les informations requises par le livreur
	 */
	ArrayList<OrderToDeliverBean> getListOrdersAcceptedOrderToBeDelivered(UserAccountBean deliveryMan);
	
	/**
	 * Methode qui verifie si une commande a deja ete acceptee par un livreur
	 * @param idOrder	l'identifiant de la commande
	 * @return			true si la commande n'a toujours pas ete accepte, sinon false
	 */
	boolean checkOrderNotAcceptedYet(int idOrder);
	
	OrderBean getOrder(int idOrder);
	
	/**
	 * Methode qui va assigner une commande au livreur qui veut la prendre en charge
	 * @param idOrder		l'identifiant de la commande
	 * @param idDeliveryMan	l'identifiant du livreur 
	 * @return	0 si tout s'est bien passe, 1 si la commande est deja prise en chage par un autre livreur, 2 si il y a eu une erreur
	 */
	int assignOrderToDelileveryMan(int idOrder, int idDeliveryMan);
	

}
