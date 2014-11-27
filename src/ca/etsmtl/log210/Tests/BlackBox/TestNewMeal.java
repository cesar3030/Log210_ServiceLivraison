package ca.etsmtl.log210.Tests.BlackBox;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

import org.mockito.Mockito;
import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Controller.NewMeal;
import ca.etsmtl.log210.DAO.MealDao;

public class TestNewMeal extends TestCase {
	
	public void testNewMealWithDesc()throws Exception{
		
		 HttpServletRequest request = mock(HttpServletRequest.class);
		 HttpServletResponse response = mock(HttpServletResponse.class); 
		 HttpSession session = mock(HttpSession.class); 
		 MealBean meal = mock(MealBean.class);
		 MealDao mealDao = mock(MealDao.class);
		
		 
		 Mockito.when(request.getParameter("name")).thenReturn("testMealname");
		 Mockito.when(request.getParameter("description")).thenReturn("descriptionMealFactice");
		 Mockito.when(request.getParameter("price")).thenReturn("10");
		 Mockito.when(session.getAttribute("idMenuSession")).thenReturn("1");
		 
		 System.out.println("Voici le test avec la description d'un plat");
		 System.out.println("Mock Meal.name "+request.getParameter("name"));
	 	 System.out.println("Mock Meal.description "+request.getParameter("description"));
	 	 System.out.println("Mock Meal.price "+request.getParameter("price"));
	 	 System.out.println("Mock Session idMenuSessionAttribute "+session.getAttribute("idMenuSession"));
	 	 
	 	 NewMeal testTargetNewMeal = Mockito.spy(new NewMeal());
	 	
	 	 doNothing().when(testTargetNewMeal).redirection(request, response);
	 	 doReturn(1).when(testTargetNewMeal).getIdMenuSession();
	 	 doReturn(session).when(testTargetNewMeal).getSession(request);	
	 	 doReturn(mealDao).when(testTargetNewMeal).getMealDao();
	 	 doReturn(meal).when(testTargetNewMeal).getMealBean();
	 	
	 	 testTargetNewMeal.doPost(request, response);
	 	 
	 	 verify(testTargetNewMeal, times(1)).getMealBean();
	 	 verify(testTargetNewMeal, times(1)).getMealDao();
	 	 verify(testTargetNewMeal, times(1)).getMealDao();
	 	 verify(testTargetNewMeal, times(1)).setMapError();
	 	 verify(testTargetNewMeal, times(1)).testDescriptionMissing(request, session);
	 	 verify(testTargetNewMeal, times(1)).redirection(request, response);
	 	 assertEquals(testTargetNewMeal.getEtatDescription(),0);
	}

	public void testNewMealWithoutDesc()throws Exception{
		
		 HttpServletRequest request = mock(HttpServletRequest.class);
		 HttpServletResponse response = mock(HttpServletResponse.class); 
		 HttpSession session = mock(HttpSession.class); 
		 MealBean meal = mock(MealBean.class);
		 MealDao mealDao = mock(MealDao.class);
		
		 
		 Mockito.when(request.getParameter("name")).thenReturn("testMealname");
		 Mockito.when(request.getParameter("description")).thenReturn("");
		 Mockito.when(request.getParameter("price")).thenReturn("22");
		 Mockito.when(session.getAttribute("idMenuSession")).thenReturn("1");

		 System.out.println("Voici le test sans la description d'un plat");
		 System.out.println("Mock Meal.name "+request.getParameter("name"));
	 	 System.out.println("Mock Meal.description "+request.getParameter("description"));
	 	 System.out.println("Mock Meal.price "+request.getParameter("price"));
	 	 System.out.println("Mock Session idMenuSessionAttribute "+session.getAttribute("idMenuSession"));;
	 	 
	 	 NewMeal testTargetNewMeal = Mockito.spy(new NewMeal());
	 	
	 	 doNothing().when(testTargetNewMeal).redirection(request, response);
	 	 doReturn(1).when(testTargetNewMeal).getIdMenuSession();
	 	 doReturn(session).when(testTargetNewMeal).getSession(request);	
	 	 doReturn(mealDao).when(testTargetNewMeal).getMealDao();
	 	 doReturn(meal).when(testTargetNewMeal).getMealBean();
	 	
	 	 testTargetNewMeal.doPost(request, response);
	 	 
	 	 verify(testTargetNewMeal, times(1)).getMealBean();
	 	 verify(testTargetNewMeal, times(1)).getMealDao();
	 	 verify(testTargetNewMeal, times(1)).getMealDao();
	 	 verify(testTargetNewMeal, times(1)).setMapError();
	 	 verify(testTargetNewMeal, times(1)).testDescriptionMissing(request, session);
	 	 verify(testTargetNewMeal, times(1)).redirection(request, response);
	 	 assertEquals(testTargetNewMeal.getEtatDescription(),1);
	 	
	}

	

}
