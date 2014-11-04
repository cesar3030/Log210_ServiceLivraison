package ca.etsmtl.log210.Beans;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */

/**
 * Bean permettant de creer une structure
 * affichant les items d'une commande
 *
 */
public class OrderDetailsItemsBean {

		private String name;
		private String description;
		private int quantity;
		private int price;
		private int idOrderItem;
		private int idPlat;

		/**
		 * Constructeur par copie d'attribut de la classe OrderDetailsItemsBean
		 * @param name
		 * @param description
		 * @param quantity
		 * @param price
		 * @param idOrderItem
		 * @param idPlat
		 */
		public OrderDetailsItemsBean(String name, String description, int quantity,int price,int idOrderItem,int idPlat) {
			this.name = name;
			this.description = description;
			this.quantity = quantity;
			this.price = price * quantity;
			this.idOrderItem=idOrderItem;
			this.idPlat=idPlat;
		}

		/**
		 * Permet de renvoyer le nom du plat
		 * @return
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * Permet d'affecter ou modifier le nom d'un item
		 * @return
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * Methode qui permet de retourner la quantite d'un produit dans une commande
		 * @return int quantity
		 */
		public int getQuantity() {
			return quantity;
		}

		/**
		 * Methode qui permet d'affecter une valeur à l'attribut quantity
		 * @param quantite (int)
		 */
		public void setQuantity(int quantite) {
			this.quantity = quantite;
		}

		/**
		 * Methode qui permet de retourner la descrition d'un item d'une commande
		 * @return (String) description
		 */
		public String getDescription() {
			return this.description;
		}

		/**
		 * Methode qui permet d'affecter une valeur à l'attribut Description
		 * @param desc (String)
		 */
		public void setDescription(String desc) {
			this.description = desc;
		}

		/**
		 * Methode qui permet de retourner le 
		 * prix total d'un item d'un commande prix initial * sa quantite
		 * @return idOrderItem (int)
		 */
		public int getPrice() {
			return this.price;
		}

		/**
		 * Methode qui permet d'affecter une valeur a la l attribut price
		 * @param (int) price
		 *  price = prix initial * quantite total du produit
		 */
		public void setPrice(int price) {
			this.price = price;
		}

		/**
		 * Methode qui permet de retourner le numéro de commande de l item
		 * @return the idMeal (int)
		 */
		public int getidOrderItem() {
			return idOrderItem;
		}

		/**
		 * Methode qui permet d'affecter une valeur à idMeal
		 * @param idMeal (int)
		 * Ce parametre est generalement recu en parametre depuis le controleur ShowOrderItem
		 */
		public void setidOrderItem(int id) {
			this.idOrderItem = id;
		}
		
		/**
		 * Methode qui permet d'affecter une valeur à idPlat
		 * @param idPlat (int)
		 * Cette valeur refere au numero unique de l item selon le menu du restaurant
		 */
		public void setIdPlat(int idP) {
			this.idPlat = idP;
		}
		
		/**
		 * Methode qui permet de retourner  id du plat
		 * @return (int) idPlat
		 */
		public int getIdPlat() {
			return this.idPlat;
		}

	
}
