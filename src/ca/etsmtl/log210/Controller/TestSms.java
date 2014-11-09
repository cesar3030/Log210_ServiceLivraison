package ca.etsmtl.log210.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.OrderDao;
import ca.etsmtl.log210.Utils.SmsUtility;

public class TestSms extends HttpServlet implements Servlet
{


	public static final String SHOW_PAGE_ORDER_NEEDED_TO_BE_DELIVERED = "testSms.jsp";

	public void init() throws ServletException {
	
	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		SmsUtility.sendTextMessage("Hello world", "+4388315858");
		
	}
	
}