package ca.etsmtl.log210.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.etsmtl.log210.Beans.OrderBean;
import ca.etsmtl.log210.Beans.OrderItemBean;
import ca.etsmtl.log210.Beans.OrderToDeliverBean;
import ca.etsmtl.log210.Beans.UserAccountBean;
import ca.etsmtl.log210.Utils.EmailUtility;
import ca.etsmtl.log210.Utils.ExpressLivraisonSms;

/**
 * Servlet qui envoie un email et un SMS au client lorsque le payeent a ete effectue
 * @author Cesar Jeanroy
 *
 */
public class OrderDone extends HttpServlet
{
	public static final String SESSION_USER = "userSession";
	public static final String ORDER_DONE = "orderDone";
	public static final String REQUEST_FINISHED_STATE = "returnMessage";
	public static final String SHOW_PAGE_ORDER_DONE = "/Restrict/Client/OrderDone.jsp";
	
	// Attributs pour l"envoie de mail
	private String host;
	private String port;
	private String user;
	private String pass;
	

	public void init() throws ServletException 
	{
		// On va chercher les informations du serveur SMTP stockees dans web.xml
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Map<String, String> returnMessage = new HashMap<String, String>();

		//On recupre la variable de session
		HttpSession session = request.getSession(); 
		
		//On recupere le client qui est connecte
		UserAccountBean client = (UserAccountBean) session.getAttribute(SESSION_USER);
		
		//On recupere le client qui est connecte
		OrderToDeliverBean orderWithAllTheInformations = (OrderToDeliverBean) session.getAttribute(ORDER_DONE);
		
		// On genere et stocke le contenu du message a
		

		// Envoie de du courriel
		try 
		{
			// envoyer
			String content = this.generateMailContent(orderWithAllTheInformations.getOrder(),
					client);
			
			// Variables utilisees pour l'envoi du courriel
			String recipient = client.getEmail();

			String subject = "Confirmation de votre commande chez ExpressLivraison";
			
			EmailUtility.sendEmail(host, port, user, pass,recipient, subject, content);
			
			 //ENVOIE DU SMS DE CONFIRMATION DE LA COMMANDE POUR LE CLIENT
	        ExpressLivraisonSms sms = new ExpressLivraisonSms();
	       // sms.envoyerSmsConfirmationCommande(client.getPhoneNumber(), orderWithAllTheInformations.getOrder().getConfirmationCode());

	        returnMessage.put("succes", "Commande validé !");
			
			returnMessage.put("email",
					"Un message vous a ete envoye l'adresse: "
							+ client.getEmail());
		} 
		catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}finally {

			request.setAttribute(REQUEST_FINISHED_STATE,
					returnMessage);
			this.getServletContext()
					.getRequestDispatcher(SHOW_PAGE_ORDER_DONE)
					.forward(request, response);
		}
		
		
		
		
	}
	
	/**orderWithAllTheInformations
	 * M�thode qui cree le contenu du mail de confirmation de commande.
	 * @param order		la commande	
	 * @param client	le client qui a effectue la commande
	 * @return			le contenu du courriel contenant le resumer de la commande
	 */
	private String generateMailContent(OrderBean order, UserAccountBean client) {
		String beginning = "Chere " + client.getFirstName() + " "
				+ client.getName()
				+ ",<br><br> Voici le resume de la commande :"
				+ order.getConfirmationCode();
		String content = "<br>";
		String end = "<br><br>Merci de votre fidelite, au plaisir de vous revoir !";

		for (OrderItemBean item : order.getOrderItemsList()) 
		{
			int totalItemPrice = item.getQuantity() * item.getMeal().getPrice();
			
			content = content + "<br>"+item.getMeal().getName()+" * "+item.getQuantity()+" = "+totalItemPrice;
		}
		
		content = content + "<br><br>Le montant total de votre commande est de "+order.getTotalPrice()+"$";
		
		content=beginning+content+end;
		
		return content;
	}
}
