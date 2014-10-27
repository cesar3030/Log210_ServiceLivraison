package ca.etsmtl.log210.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;
import ca.etsmtl.log210.DAO.OrderDao;
import ca.etsmtl.log210.DAO.OrderItemDao;

public class ProceedOrder extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String MEAL_MENU = "/ShowAllMealMenu";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	private static String ORDER="order";
	 public static final String SESSION_USER = "userSession";  

	
	private OrderDao orderDao;
	private OrderItemDao orderItemDao ;
	
	public void init() throws ServletException {
		this.orderDao= ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderDao();
		this.orderItemDao= ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderItemDao();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		OrderBean order = null;
		UserAccountBean client=null;
		int idNewOrder;
		
		//Je recupere l'addresse de livraison
		int idAddress = Integer.parseInt(request.getParameter("addressList"));
		
		//je recupere la date et l<heure de livraison
		String hourAndDate="";
		
		//On recupre la variable de session
		HttpSession session = request.getSession(); 
		
		//On va chercher la commande stockee dans la variable de session
		order = (OrderBean) session.getAttribute(ORDER);
		
		//On recupere le client qui est connecte
		client = (UserAccountBean) session.getAttribute(SESSION_USER);
		
		//On set l'id du client
		order.setIdUserAccount(client.getUserId());
		
		//On set l'address
		
		//Je cree la commande et recupere son identifiant pour l'associer aux items de la commande
		idNewOrder = orderDao.newOrder(order);
		
		for(OrderItemBean item : order.getOrderItemsList())
		{
			
		}
	}

}
