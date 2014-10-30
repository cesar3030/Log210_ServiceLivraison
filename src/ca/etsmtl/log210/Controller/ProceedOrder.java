package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;
import ca.etsmtl.log210.DAO.OrderDao;
import ca.etsmtl.log210.DAO.OrderItemDao;

public class ProceedOrder extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String MEAL_MENU = "/ShowAllMealMenu";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	private static String ORDER="order";
	public static final String SESSION_USER = "userSession";  
	 
	
	private OrderDao orderDao;
	private OrderItemDao orderItemDao ;
	
	public void init() throws ServletException {
		this.orderDao= ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderDao();
		this.orderItemDao= ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOrderItemDao();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map<String, String>  returnMessage= new HashMap<String, String>();
		OrderBean order = null;
		UserAccountBean client=null;
		int idNewOrder;
		
		//Je recupere l'addresse de livraison
		int idAddress = Integer.parseInt(request.getParameter("addressList"));
		
		//je recupere la date et l<heure de livraison
		String hourAndDate=request.getParameter("dateAndHour");
		
		//On recupre la variable de session
		HttpSession session = request.getSession(); 
		
		//On va chercher la commande stockee dans la variable de session
		order = (OrderBean) session.getAttribute(ORDER);
		
		//On set a la commande la date et l'heure de la livraison
		order.setHourAndDate(hourAndDate);
		
		//On set a la commande l'id de l'adresse selectionné par le client
		order.setIdAddress(idAddress);
		
		//On recupere le client qui est connecte
		client = (UserAccountBean) session.getAttribute(SESSION_USER);
		
		//On set l'id du client
		order.setIdUserAccount(client.getUserId());
		
		//On set l'id de l'adresse
		order.setIdAddress(idAddress);
		
		//Je cree la commande et recupere son identifiant pour l'associer aux items de la commande
		idNewOrder = orderDao.newOrder(order);
		order.setIdOrder(idNewOrder);
		
		//On test si une commande a ete cree avec succes
		if(idNewOrder>0)
		{
			//variable utilise pour verifier que la requete sql s'est terminee sans erreurs
			boolean error=false;
			
			//On cree et ajoute les items a la commande 
			for(OrderItemBean item : order.getOrderItemsList())
			{
				//Si il n'y a pas eu d'erreur lors de l'ajout de precedant items on en ajoute un nouveau
				if(error==false)
				{
					//On ajoute l'item a la commande
					error=orderItemDao.newOrderItem(item);
				}
				else
				{
					break;
				}
			}
			
			if(error == false)
			{
				//On genere le code de confirmation
				String confirmCode=newConfirmationCode(order.getIdOrder());
				
				//On set au bean le code de confirmation
				order.setConfirmationCode(confirmCode);
				
				//On update en BD le code de confirmation
				error= orderDao.setConfirmationCode(order);
				
				if(error == false)
				{
					returnMessage.put("succes","Commande validé !");
				}
				else
				{
					returnMessage.put("fail","Une erreur est survenue lors de l'ajout en BD du code de confirmation de la commande.");
				}
					
			}
			else
			{
				returnMessage.put("fail","Une erreur est survenue lors de l'ajout en BD d'un item dans la commande. Veuillez reessayer.");
				
			}
			
		}
		else
		{ 
			returnMessage.put("fail","Une erreur est survenue lors de la création en BD d'une commande. Veuillez reessayer.");
			
		}
		
		 request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
	}
	
	/**
	 * Methode qui genere le code de confirmation de la commande.
	 *	le code de confirmation a une longeur de 10 carracteres: des 0 et l'id de la commande
	 *	ex: 0000000090 (8 zeros et l'id de la commande 90)
	 * @param idCommande
	 * @return le code de confirmation
	 */
	private String newConfirmationCode(int idCommande)
	{
		String confirmCode="";
		
		//J'ajout de l'id de la commande
		confirmCode=confirmCode+idCommande;
		
		//On complete la chaine avec des zeros devant l'identifiant de la commande tant que la 
		//chaine ne fait pas 10 caracteres
		while(confirmCode.length()<10)
		{
			confirmCode="0"+confirmCode;
		}
			
		return confirmCode;
	}

}