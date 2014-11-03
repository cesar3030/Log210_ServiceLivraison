package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.DAO.AddressDao;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.OrderDao;
import ca.etsmtl.log210.DAO.OrderItemDao;

public class OrdersNeededToBeDelivered extends HttpServlet implements Servlet
{
	public static final String CONF_DAO_FACTORY = "daofactory";

	public static final String SHOW_PAGE_ORDER_NEEDED_TO_BE_DELIVERED = "/Restrict/DeliveryMan/OrdersNeededToBeDelivered.jsp";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	public static final String ATTRIBUTE_ORDER = "order";
	public static final String ATTRIBUTE_ADDRESS = "address";
	private static String ORDER="order";
	public static final String SESSION_USER = "userSession";  
	
	
	private OrderDao orderDao;
	private OrderItemDao orderItemDao ;
	private AddressDao addressDao ;
	
	public void init() throws ServletException {
		this.orderDao= ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderDao();
		this.orderItemDao= ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderItemDao();
		this.addressDao=((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getAddressDao();

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<OrderBean> readyOrderList= new ArrayList<OrderBean>();
		
		readyOrderList =  
		
	}

}
