package ca.etsmtl.log210.Beans;

import java.beans.Beans;


public class MenuBean extends Beans{
	private int idMenu;
	private int idRestaurant;
	private String name;
	private String description;
	private int visible;
	
	
 //*******************************************ACCESSEURS******************************
    
	public int getIdMenu() {
		return this.idMenu;
	}
	
	public int getIdRestaurant() {
		return this.idRestaurant;
	}
	
	public String getName() {
			return this.name;
		}
	
	public String getDescription(){
		return this.description;
	}
	
	public int getvisible(){
		return this.visible;
	}
	


	
 //****************************************MUTATEUR*************************************
	
	public void setIdMenu(int idMenuRecu) {
		this.idMenu = idMenuRecu;
	}

	public void setIdRestaurant(int idRestaurantRecu) {
		this.idRestaurant = idRestaurantRecu;
	}

	public void setname(String nameMenuRecu) {
		this.name = nameMenuRecu;
	}
	public void setDescription(String descriptionRestoRecu) {
		this.description = descriptionRestoRecu;
	}
	public void setVisible(int visibleRecu) {
		this.visible = visibleRecu;
	}



}
