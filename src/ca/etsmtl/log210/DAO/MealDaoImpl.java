package ca.etsmtl.log210.DAO;

public class MealDaoImpl  implements MealDao{
	
	private DAOFactory daoFactory;
	
	public MealDaoImpl(DAOFactory daoFactory) 
	{
		this.daoFactory = daoFactory;		
	}

}
