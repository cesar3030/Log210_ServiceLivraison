package ca.etsmtl.log210.Tests.BlackBox;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mockito;

import ca.etsmtl.log210.Beans.MenuBean;
import ca.etsmtl.log210.Controller.AddNewMenu;
import ca.etsmtl.log210.DAO.MenuManageDao;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
public class TestAddNewMenu extends TestCase {
	
	
public void testAddNewMenuAvecDescription()throws Exception{
		
		 HttpServletRequest request = mock(HttpServletRequest.class);
		 HttpServletResponse response = mock(HttpServletResponse.class); 
		 HttpSession session = mock(HttpSession.class); 
		 MenuBean menu = mock(MenuBean.class);
		 MenuManageDao menuDao = mock(MenuManageDao.class);
		 
		 Mockito.when(request.getParameter("nameMenu")).thenReturn("testMenu");
		 Mockito.when(request.getParameter("descriptionMenu")).thenReturn("descriptionTest");
		 Mockito.when(session.getAttribute("restaurantActuel")).thenReturn("1");
	 	
	 	 System.out.println(request.getParameter("nomMenu"));
	 	 System.out.println(request.getParameter("descriptionMenu"));
	 	 System.out.println(session.getAttribute("restaurantActuel"));
	 	 
	 	 AddNewMenu testTargetAddMenu = Mockito.spy(new AddNewMenu());
	 	 
	 	 doNothing().when(testTargetAddMenu).redirection(request, response);
	 	 doReturn(1).when(testTargetAddMenu).getID_RESTAURANT_REFERENCE();
	 	 doReturn(session).when(testTargetAddMenu).getSession(request);
	 	 doReturn(menu).when(testTargetAddMenu).menuBeanFactory(); 	
	 	 doReturn(menuDao).when(testTargetAddMenu).getMenuDao();
	 	 doReturn(menu).when(testTargetAddMenu).getMenuAajouter();
	 	 
	 	 when(menu.getDescription()).thenReturn("une description");
	 	 
	 	 testTargetAddMenu.doPost(request, response);
	 	 
	 	 verify(testTargetAddMenu, times(2)).getMenuAajouter();
	 	 verify(testTargetAddMenu, times(1)).getMenuDao();
	 	 verify(testTargetAddMenu, times(1)).redirection(request, response);
	 	 assertEquals(testTargetAddMenu.getEtatRequeter(),0);
	 	 
	}
	
	public void testAddNewMenuSansDescription()throws Exception{
		
		 HttpServletRequest request = mock(HttpServletRequest.class);
		 HttpServletResponse response = mock(HttpServletResponse.class); 
		 HttpSession session = mock(HttpSession.class); 
		 MenuBean menu = mock(MenuBean.class);
		 MenuManageDao menuDao = mock(MenuManageDao.class);
		 
		 Mockito.when(request.getParameter("nameMenu")).thenReturn("testMenu");
		 Mockito.when(request.getParameter("descriptionMenu")).thenReturn("descriptionTest");
		 Mockito.when(session.getAttribute("restaurantActuel")).thenReturn("1");
	 	
	 	 System.out.println(request.getParameter("nomMenu"));
	 	 System.out.println(request.getParameter("descriptionMenu"));
	 	 System.out.println(session.getAttribute("restaurantActuel"));
	 	 
	 	 AddNewMenu testTargetAddMenu = Mockito.spy(new AddNewMenu());
	 	 
	 	 doNothing().when(testTargetAddMenu).redirection(request, response);
	 	 doReturn(1).when(testTargetAddMenu).getID_RESTAURANT_REFERENCE();
	 	 doReturn(session).when(testTargetAddMenu).getSession(request);
	 	 doReturn(menu).when(testTargetAddMenu).menuBeanFactory(); 	
	 	 doReturn(menuDao).when(testTargetAddMenu).getMenuDao();
	 	 doReturn(menu).when(testTargetAddMenu).getMenuAajouter();
	 	 
	 	 when(menu.getDescription()).thenReturn("");
	 	 
	 	 testTargetAddMenu.doPost(request, response);
	 	 
	 	 verify(testTargetAddMenu, times(2)).getMenuAajouter();
	 	 verify(testTargetAddMenu, times(1)).getMenuDao();
	 	 verify(testTargetAddMenu, times(1)).redirection(request, response);
	 	 assertEquals(testTargetAddMenu.getEtatRequeter(),2);
	 	 
	}
	
	

}
