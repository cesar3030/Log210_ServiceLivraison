package ca.etsmtl.log210.Tests.BlackBox;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

public class Template extends TestCase
{
	public void test()throws Exception
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class); 
		HttpSession session = mock(HttpSession.class); 
		
	}
}
