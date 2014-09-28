package ManageMenu;

import ca.etsmtl.log210.Beans.MenuBean;


public interface  MenuManageDao{
	
	
		void  addNewMenu (MenuBean menuRecu);
		void  deleteMenu (MenuBean menuRecu);	
		MenuBean[] showAllMenuForOneResto(int restaurantNumber);
		
	}



