package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderToDeliverBean;
import ca.etsmtl.log210.DAO.AddressDao;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.OrderDao;
import ca.etsmtl.log210.DAO.OrderItemDao;
/**
 * Aissou Idriss
 * Jeanroy Cesar
 * Murat David
 */
public class OrdersNeededToBeDelivered extends HttpServlet implements Servlet
{
	public static final String CONF_DAO_FACTORY = "daofactory";

	public static final String SHOW_PAGE_ORDER_NEEDED_TO_BE_DELIVERED = "/Restrict/DeliveryMan/OrdersNeededToBeDelivered.jsp";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	public static final String ATTRIBUTE_ORDER_READY = "orderReady";
	public static final String SESSION_USER = "userSession";  
	
	
	private OrderDao orderDao;
	public void init() throws ServletException {
		this.orderDao= ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderDao();
	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<OrderToDeliverBean> readyOrderList= new ArrayList<OrderToDeliverBean>();
		
		readyOrderList =  orderDao.getListOrdersReadyForDelivery();
		
		request.setAttribute(ATTRIBUTE_ORDER_READY, readyOrderList);
		
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("langue"));
		
		ServletContext context= getServletContext();
		 RequestDispatcher rd= context.getRequestDispatcher(SHOW_PAGE_ORDER_NEEDED_TO_BE_DELIVERED);
		 rd.forward(request, response);
		
	}

}
