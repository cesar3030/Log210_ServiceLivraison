package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.MenuBean;

public class ShowOrderSummary  extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	 {
		Map<MealBean, Integer> order= new HashMap<MealBean, Integer>();
		String xml = request.getParameter("cart");
		order = parseXmlFile(xml);
		System.out.println(order);

	 }
	
	/**
	 * Methode qui parse le XML recu du client.
	 * @author http://www.java-samples.com/showtutorial.php?tutorialid=152 consulte le 22-10-2014
	 * @param xml
	 * @return	Une map contenant un MealBean et sa quantite voulue
	 */
	private Map<MealBean, Integer> parseXmlFile(String xml){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Map<MealBean, Integer> order= new HashMap<MealBean, Integer>();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse(xml);
			
			order = parseDocument(dom, order);



		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return order;
	}
	

	private Map<MealBean, Integer>  parseDocument(Document dom,Map<MealBean, Integer> order){
		//get the root element
		org.w3c.dom.Element docEle = dom.getDocumentElement();
		
		
		//get a nodelist of elements
		NodeList nl = docEle.getElementsByTagName("CART");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the employee element
				Element el = (Element)nl.item(i);

				//get the Employee object
				order= addMeal(el, order );

			}
		}
		
		return order;
		
	}
	

	/**
	 * I take an employee element and read the values in, create
	 * an Employee object and return it
	 */
	private Map<MealBean, Integer> addMeal(Element empEl, Map<MealBean, Integer> order) {

		//for each <employee> element get text or int values of
		//name ,id, age and name
		int id = getIntValue(empEl,"ID");
		int quantity = getIntValue(empEl,"QUANTITY");


		//Create a new Employee with the value read from the xml nodes
		MealBean meal = new MealBean(id);
		
		
		order.put(meal, quantity);
		

		return order;
	}


	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}


	/**
	 * Calls getTextValue and returns a int value
	 */
	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}

}
