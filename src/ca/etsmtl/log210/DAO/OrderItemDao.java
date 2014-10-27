package ca.etsmtl.log210.DAO;

import ca.etsmtl.log210.Beans.OrderItemBean;

public interface OrderItemDao 
{
	/**
	 * Methode qui ajout en bd un nouvel item et le lie a une commande
	 * @param orderItem	le bean contenant le plat, la quantite et l'id de la commande
	 * @return 					True si tout s'est correctement passe False si il y a eu un probleme
	 */
	boolean newOrderItem(OrderItemBean orderItem);

}
