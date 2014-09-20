package ca.etsmtl.log210.DAO;



import ca.etsmtl.log210.Beans.UserAccountBean;

public interface UserAccountDao 
{
	UserAccountBean getUserAccount(String email, String password);
	
	void newUserAccount(UserAccountBean newUser);
}
