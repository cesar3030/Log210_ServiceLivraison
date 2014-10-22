/**
 *  Source: http://www.rgagnon.com/javadetails/java-0573.html
 *  Visite le 22-10-2014
 */
 package ca.etsmtl.log210.Controller;

	import javax.xml.parsers.*;

import org.xml.sax.InputSource;
import org.w3c.dom.*;

import ca.etsmtl.log210.Beans.MealBean;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

	public class ParseXMLString {

	  public static Map<MealBean, Integer> parseMeal(String xmlRecords) {

		Map<MealBean, Integer> order= new HashMap<MealBean, Integer>();
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
	         
	           MealBean meal = new MealBean(id);
	   			
	   		
	   			order.put(meal, quantity);
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

