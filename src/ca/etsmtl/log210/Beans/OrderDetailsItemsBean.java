package ca.etsmtl.log210.Beans;

public class OrderDetailsItemsBean {

		private String name;
		private String description;
		private int quantity;
		private int price;
		private int idOrderItem;
		private int idPlat;

		
		public OrderDetailsItemsBean(String name, String description, int quantity,int price,int idOrderItem,int idPlat) {
			this.name = name;
			this.description = description;
			this.quantity = quantity;
			this.price = price * quantity;
			this.idOrderItem=idOrderItem;
			this.idPlat=idPlat;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantite) {
			this.quantity = quantite;
		}

		public String getDescription() {
			return this.description;
		}

		public void setDescription(String desc) {
			this.description = desc;
		}

		/**
		 * @return the idOrderItem
		 */
		public int getPrice() {
			return this.price;
		}

		/**
		 * @param idOrderItem
		 *            the idOrderItem to set
		 */
		public void setPrice(int price) {
			this.price = price;
		}

		/**
		 * @return the idMeal
		 */
		public int getIdOrder() {
			return idOrderItem;
		}

		/**
		 * @param idMeal
		 *            the idMeal to set
		 */
		public void setIdOrder(int id) {
			this.idOrderItem = id;
		}
		
		public void setIdPlat(int idP) {
			this.idPlat = idP;
		}
		
		public int getIdPlat() {
			return this.idPlat;
		}

	
}
