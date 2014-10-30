package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.AddressBean;
import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.DAO.AddressDao;
import ca.etsmtl.log210.DAO.DAOFactory;
import ca.etsmtl.log210.DAO.MealDao;
import ca.etsmtl.log210.DAO.OrderDao;
import ca.etsmtl.log210.DAO.OrderItemDao;

public class ProceedOrder extends HttpServlet {
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String SHOW_PAGE_ORDER_DONE = "/Restrict/Client/OrderDone.jsp";
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
		//Le bean permettant de stocker l'adresse de livraison
		
		AddressBean deliveryAddress=null;
		Map<String, String>  returnMessage= new HashMap<String, String>();
		OrderBean order = null;
		UserAccountBean client=null;
		int idNewOrder;
		//variable utilise pour verifier que les requetes sql s'est terminent sans erreurs
		boolean continute=true;
		
		//Je recupere l'addresse de livraison
		int idAddress = Integer.parseInt(request.getParameter("addressList"));
		
		//je recupere la date et l'heure de livraison
		String hourAndDate=request.getParameter("dateAndHour");
		
		//On recupre la variable de session
		HttpSession session = request.getSession(); 
		
		//On va chercher la commande stockee dans la variable de session
		order = (OrderBean) session.getAttribute(ORDER);
		
		//On recupere le client qui est connecte
		client = (UserAccountBean) session.getAttribute(SESSION_USER);
		
		//On set l'id du client
		order.setIdUserAccount(client.getUserId());

		//On set a la commande la date et l'heure de la livraison
		order.setHourAndDate(hourAndDate);
		
		//Si -1 est retourne, cela veut dire que c'est une nouvelle adresse
		if(idAddress==-1)
		{
			//je recupere la la nouvelle adresse
			String newAddress=request.getParameter("newAddress");
			idAddress=addressDao.newAddress(newAddress, order.getIdUserAccount());
			
			//On test si l'ajout s'est bien fait
			if(idAddress<0 || idAddress==0)
			{
				//si il y a eu une erreur, on arrete le processus d'ajout de la commande
				continute=false;
				System.out.println("Une erreur est survenue lors de la création en BD de la nouvelle adresse. Veuillez reessayer.");
				returnMessage.put("fail","Une erreur est survenue lors de la création en BD de la nouvelle adresse. Veuillez reessayer.");
			}

		}
		
		//On set a la commande l'id de l'adresse selectionné par le client
		order.setIdAddress(idAddress);
		
		
		
		//On set l'id de l'adresse
		order.setIdAddress(idAddress);
		
		//Je cree la commande et recupere son identifiant pour l'associer aux items de la commande
		idNewOrder = orderDao.newOrder(order);
		order.setIdOrder(idNewOrder);
		
		//On test si une commande a ete cree avec succes
		if(idNewOrder>0)
		{
			//On cree et ajoute les items a la commande 
			for(OrderItemBean item : order.getOrderItemsList())
			{
				//Si il n'y a pas eu d'erreur lors de l'ajout de precedant items on en ajoute un nouveau
				if(continute==true)
				{
					//On set a l'item, l'identifiant de la commande qui a ete genere lorsque la commande a ete cree en bd
					item.setIdOrder(order.getIdOrder());
					
					//On ajoute l'item a la commande
					continute=orderItemDao.newOrderItem(item);
				}
				else
				{
					break;
				}
			}
			
			if(continute == true)
			{
				//On genere le code de confirmation
				String confirmCode=newConfirmationCode(order.getIdOrder());
				
				//On set au bean le code de confirmation
				order.setConfirmationCode(confirmCode);
				
				//On update en BD le code de confirmation
				continute= orderDao.setConfirmationCode(order);
				
				if(continute == true)
				{
					deliveryAddress = addressDao.getAddress(idAddress);
					
					if(continute == true)
					{
						System.out.println("Commande validé !");
						returnMessage.put("succes","Commande validé !");
						
						//On set en attribut la commande pour pouvoir recuperer le numero de commande
						request.setAttribute(ATTRIBUTE_ORDER, order);
						
						//On set en attribut l'adresse de livraison pour l'afficher
						request.setAttribute(ATTRIBUTE_ADDRESS, deliveryAddress);
					}
					else
					{
						System.out.println("Une erreur est survenue lors de la recuperation en BDde l'adresse de livraison.");
						returnMessage.put("fail","Une erreur est survenue lors de la recuperation en BD de l'adresse de livraison.");
					}
				}
				else
				{
					System.out.println("Une erreur est survenue lors de l'ajout en BD du code de confirmation de la commande.");
					returnMessage.put("fail","Une erreur est survenue lors de l'ajout en BD du code de confirmation de la commande.");
				}
					
			}
			else
			{
				System.out.println("Une erreur est survenue lors de l'ajout en BD d'un item dans la commande. Veuillez reessayer.");
				returnMessage.put("fail","Une erreur est survenue lors de l'ajout en BD d'un item dans la commande. Veuillez reessayer.");
				
			}
			
		}
		else
		{ 
			System.out.println("Une erreur est survenue lors de la création en BD d'une commande. Veuillez reessayer.");
			returnMessage.put("fail","Une erreur est survenue lors de la création en BD d'une commande. Veuillez reessayer.");
			
		}
		
		 request.setAttribute(REQUEST_FINISHED_STATE, returnMessage);
		 this.getServletContext().getRequestDispatcher(SHOW_PAGE_ORDER_DONE ).forward( request, response );
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
