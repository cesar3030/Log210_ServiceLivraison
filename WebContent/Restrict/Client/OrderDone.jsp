<%@ page pageEncoding="UTF-8" %>
<%@ page import="ca.etsmtl.log210.Beans.OrderBean, ca.etsmtl.log210.Beans.OrderToDeliverBean,ca.etsmtl.log210.Beans.UserAccountBean,ca.etsmtl.log210.Utils.ExpressLivraisonSms,ca.etsmtl.log210.Utils.EmailUtility,ca.etsmtl.log210.Beans.OrderItemBean,javax.mail.internet.AddressException,javax.mail.MessagingException" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
<div class="row">
 	<div class="col-md-12 col-xs-12"> 		 
			 <h1 class="text-center" >Commande faite !</h1>  	
			 <br>
			 <h4 class="col-md-offset-2 col-xs-offset-1">Un email vient de vous etre envoye a votre adresse: ${sessionScope.userSession.email}</h4>
			 <br>
			 <h4 class="col-md-offset-2 col-xs-offset-1">Voici votre numero de confirmation de commande: <c:out value="${sessionScope.orderDone.order.confirmationCode}"/></h4>
			 <br>
			 <h4 class="col-md-offset-2 col-xs-offset-1">La livraison se fera a l'adresse: 
			 <c:if test="${sessionScope.orderDone.order.idAddress==0}">
			 	<c:out value="${sessionScope.userSession.homeAddress}"/>
			 </c:if>
			 <c:if test="${sessionScope.orderDone.order.idAddress != 0}">
			  	<c:out value="${sessionScope.orderDone.address.address}"/>
			 </c:if>
			 </h4>
			 <br>
			 <h4 class="col-md-offset-2 col-xs-offset-1">Prix total : <c:out value="${sessionScope.orderDone.order.totalPrice}"/>$</h4>
			 <br>
			 <br>
			 <h3 class="col-md-offset-2 col-xs-offset-1">Bon appétit !</h3>
	</div>
</div>
  		 
<%

//Envoie de du courriel
		try 
		{
			// Attributs pour l"envoie de mail
			String host;
			String port;
			String user;
			String pass;
			
			ServletContext context = getServletContext();
			
	        host = context.getInitParameter("host");
	        port = context.getInitParameter("port");
	        user = context.getInitParameter("user");
	        pass = context.getInitParameter("pass");
		        
			UserAccountBean client= (UserAccountBean) session.getAttribute("userSession");
			OrderToDeliverBean orderWithAllTheInformations = (OrderToDeliverBean) session.getAttribute("orderDone");

			String content = this.generateMailContent(orderWithAllTheInformations.getOrder(),
					client);
			
			// Variables utilisees pour l'envoi du courriel
			String recipient = client.getEmail();

			String subject = "Confirmation de votre commande chez ExpressLivraison";
			
			EmailUtility.sendEmail(host, port, user, pass,recipient, subject, content);
			
			 //ENVOIE DU SMS DE CONFIRMATION DE LA COMMANDE POUR LE CLIENT
	        ExpressLivraisonSms sms = new ExpressLivraisonSms();
	        sms.envoyerSmsConfirmationCommande(client.getPhoneNumber(), orderWithAllTheInformations.getOrder().getConfirmationCode());
		} 
		catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	//Je supprime la commande qui etait stocke dans la variable session 
   	session.setAttribute( "orderDone", null);
	
   	%>
	<%!
	/**orderWithAllTheInformations
	 * M�thode qui cree le contenu du mail de confirmation de commande.
	 * @param order		la commande	
	 * @param client	le client qui a effectue la commande
	 * @return			le contenu du courriel contenant le resumer de la commande
	 */
	private String generateMailContent(OrderBean order, UserAccountBean client)
	{
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
%>		 
  		 
  		 

<jsp:include page="/footer.jsp"></jsp:include>