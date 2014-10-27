package ca.etsmtl.log210.DAO;

import ca.etsmtl.log210.Beans.OrderBean;

public interface OrderDao 
{
	/**
	 * Methode qui ajoute dans la Bd une nouvelle commande
	 * @param newOrder	Bean contenant les plats, la quantite de chaqun et l'utilisateur lie a la commande 
	 * @return					True si tout s'est correctement passe False si il y a eu un probleme
	 */
	boolean newOrder(OrderBean newOrder);

}
