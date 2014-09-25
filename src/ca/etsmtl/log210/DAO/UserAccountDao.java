package ca.etsmtl.log210.DAO;



import ca.etsmtl.log210.Beans.UserAccountBean;

public interface UserAccountDao 
{
	UserAccountBean getUserAccount(String email, String password);
	
	void newUserAccount(UserAccountBean newUser);
	
	int modifyUserAccount(UserAccountBean newUser);
	
	//Si le email n'est pas déja utilisé, on retourne false. Si il est déja utilisé on retourne true
	boolean emailAlreadyUsed(String testingEmail);
}
