package ca.etsmtl.log210.DAO;



import java.util.ArrayList;

import ca.etsmtl.log210.Beans.UserAccountBean;

public interface UserAccountDao 
{
	UserAccountBean getUserAccount(String email, String password);
	
	/**
	 * Ajoute un nouvel utilisateur dans la BD
	 * @param newUser	le bean contenant les information de l'utilisateur a ajouter en BD
	 * @return				l'identifant du nouvel utilisateur cree
	 */
	int newUserAccount(UserAccountBean newUser);
	
	int modifyUserAccount(UserAccountBean newUser);
	
	//Si le email n'est pas déja utilisé, on retourne false. Si il est déja utilisé on retourne true
	boolean emailAlreadyUsed(String testingEmail);
	
	/**
	 * Retourne la liste des restaurateurs
	 */
	ArrayList<UserAccountBean> getListActiveRestaurateur();

	/**
	 * Retourne la liste des restaurateurs non actifs
	 */
	ArrayList<UserAccountBean> getListInactiveRestaurateur();
	
	/**
	 * Rend un restaurateur invisible, comme si il etait supprime. 
	 * Le fait de le rendre invisible permet de garder un historique.
	 * @param idRestaurateur
	 * @return true si l'opperation s'est bien effectuee. Fale si il y a eu un probleme
	 */
	boolean swtichRestaurateurToNotVisible(int idRestaurateur);
	
	/**
	 * Rend un restaurateur visible, cela permet de reutiliser le meme restaurateur si il souhaite se reinscrire. 
	 * Le fait de le rendre invisible permet de garder un historique.
	 * @param idRestaurateur
	 * @return true si l'opperation s'est bien effectuee. Fale si il y a eu un probleme
	 */
	boolean swtichRestaurateurToVisible(int idRestaurateur);
	 

	/**
	 * Methode qui va updater en bd le champ contenant l'identifiant de la dernière adresse
	 * utilisée.
	 * @param 	user le compte utilisateur a aller updater en BD
	 * @return	true si l'opération s'est bien faite, false sinon
	 */
	boolean updateIdMainAddressUsed(UserAccountBean user);
	
}
