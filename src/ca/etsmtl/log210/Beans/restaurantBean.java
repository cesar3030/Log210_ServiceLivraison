package ca.etsmtl.log210.Beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class restaurantBean {
	private int idMenu;
	private int idRestaurant;
	private String nameRestaurant;
	private String descriptionRestaurant;
	private Boolean visible;
	


    /**
     * 
     * @param request
     * @param fieldName
     * @return
     */
    public static String getValeurChamp( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
	
 //*******************************************ACCESSEURS******************************
    
	public int getIdMenu() {
		return this.idMenu;
	}
	
	public int getIdRestaurant() {
		return this.idRestaurant;
	}
	
	public String getNameRestaurant() {
			return this.nameRestaurant;
		}
	
	public String getDescriptionRestaurant(){
		return this.descriptionRestaurant;
	}
	
	public Boolean getVisible(){
		return this.visible;
	}
	


	
 //****************************************MUTATEUR*************************************
	
	public void setIdMenu(int idMenuRecu) {
		this.idMenu = idMenuRecu;
	}

	public void setIdRestaurant(int idRestaurantRecu) {
		this.idRestaurant = idRestaurantRecu;
	}

	public void setnameRestaurant(String nameRestoRecu) {
		this.nameRestaurant = nameRestoRecu;
	}
	public void setDescriptionRestaurant(String descriptionRestoRecu) {
		this.descriptionRestaurant = descriptionRestoRecu;
	}



}
