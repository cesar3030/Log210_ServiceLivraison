package ca.etsmtl.log210.Beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class MenuBean {
	private int idMenu;
	private int idRestaurant;
	private String name;
	private String description;
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
	
	public String getNameMenu() {
			return this.name;
		}
	
	public String getDescriptionMenu(){
		return this.description;
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

	public void setnameMenu(String nameMenuRecu) {
		this.name = nameMenuRecu;
	}
	public void setDescriptionMenu(String descriptionRestoRecu) {
		this.description = descriptionRestoRecu;
	}



}
