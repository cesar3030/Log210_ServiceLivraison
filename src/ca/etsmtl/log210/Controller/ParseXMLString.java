/**
 *  Source: http://www.rgagnon.com/javadetails/java-0573.html
 *  Visite le 22-10-2014
 */
 package ca.etsmtl.log210.Controller;

	import javax.xml.parsers.*;

import org.xml.sax.InputSource;
import org.w3c.dom.*;

import ca.etsmtl.log210.Beans.MealBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.DAO.MealDao;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

	public class ParseXMLString {

	  public static List<OrderItemBean> parseMeal(String xmlRecords, MealDao mealDao) {

		List<OrderItemBean> order= new ArrayList<OrderItemBean>();
		System.out.println(xmlRecords);

	    try {
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xmlRecords));

	        Document doc = db.parse(is);
	        NodeList nodes = doc.getElementsByTagName("MEAL");

	        // iterate the employees
	        for (int i = 0; i < nodes.getLength(); i++) {
	           Element element = (Element) nodes.item(i);

	           NodeList id_fromxml = element.getElementsByTagName("ID");
	           Element line = (Element) id_fromxml.item(0);
	           System.out.println("id: " + getCharacterDataFromElement(line));
	           int id=Integer.parseInt(getCharacterDataFromElement(line));
   

	           NodeList title = element.getElementsByTagName("QUANTITY");
	           line = (Element) title.item(0);
//	           System.out.println("Quantité: " + getCharacterDataFromElement(line));
	           int quantity=Integer.parseInt(getCharacterDataFromElement(line));
	         
	           //Je recupere toutes les infos de la BD concernant le plat dont j'ai l'id
	           MealBean meal = mealDao.getInfosOfAMeal(id);
	   			
	           OrderItemBean item=new OrderItemBean(meal,quantity);
	           
	   		
	   			order.add(item);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	 
	    return order;
	  }

	  public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	       CharacterData cd = (CharacterData) child;
	       return cd.getData();
	    }
	    return "0";
	  }
}

