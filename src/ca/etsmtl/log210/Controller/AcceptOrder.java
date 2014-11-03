package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.OrderDao;

public class AcceptOrder extends HttpServlet implements Servlet {
	
	public static final String SHOW_PAGE_ORDERS_FOR_DELIVERY = "/OrdersNeededToBeDelivered";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	public static final String CONF_DAO_FACTORY = "daofactory";
	private OrderDao orderDao;
	
	public void init() throws ServletException 
	{
		this.orderDao= ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderDao();
	}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map<String, String>  returnMessage= new HashMap<String, String>();

		int idOrder= Integer.parseInt(request.getParameter("idOrder"));
		//On rcupre la variable de session
		HttpSession session = request.getSession(); 
	   	UserAccountBean userConnected= (UserAccountBean) session.getAttribute("userSession");
		int idDeliveryMan = userConnected.getUserId();
		
		//On modifie l'etat de la commande pour passer son statut a: en cours de livraison (statut 2)
		int returnCode = orderDao.assignOrderToDelileveryMan(idOrder, idDeliveryMan);
		
		//SI le code de retour est 0, cela veut dire que l'update s'est bien fait et que la commande n'etait pas deja assignee
		if(returnCode == 0)
		{
			returnMessage.put("succes","La livraison vous a ete assigne");
		}
		else if (returnCode == 1) 
		{
			returnMessage.put("warning","Trop tard, la livraison a deja ete assigne a un autre livreur ...");
		}
		else
		{
			returnMessage.put("fail","Une erreur est survenue lors de la tantative de prise en charge de la commande");
		}
		
		request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
		
		 ServletContext context= getServletContext();
		 RequestDispatcher rd= context.getRequestDispatcher(SHOW_PAGE_ORDERS_FOR_DELIVERY);
		 rd.forward(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map<String, String>  returnMessage= new HashMap<String, String>();

		int idOrder= Integer.parseInt(request.getParameter("idOrder"));
		//On rcupre la variable de session
		HttpSession session = request.getSession(); 
	   	UserAccountBean userConnected= (UserAccountBean) session.getAttribute("userSession");
		int idDeliveryMan = userConnected.getUserId();
		
		//On modifie l'etat de la commande pour passer son statut a: en cours de livraison (statut 2)
		int returnCode = orderDao.assignOrderToDelileveryMan(idOrder, idDeliveryMan);
		
		//SI le code de retour est 0, cela veut dire que l'update s'est bien fait et que la commande n'etait pas deja assignee
		if(returnCode == 0)
		{
			returnMessage.put("succes","La livraison vous a ete assigne");
		}
		else if (returnCode == 1) 
		{
			returnMessage.put("warning","Trop tard, la livraison a deja ete assigne a un autre livreur ...");
		}
		else
		{
			returnMessage.put("fail","Une erreur est survenue lors de la tantative de prise en charge de la commande");
		}
		
		request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
		
		 ServletContext context= getServletContext();
		 RequestDispatcher rd= context.getRequestDispatcher(SHOW_PAGE_ORDERS_FOR_DELIVERY);
		 rd.forward(request, response);
	}
	

}
