<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	

<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
	    		<h3 class="panel-title">Payment</h3>
	    </div>
	    	<div class="panel-body">
	    		<h4>Veuillez choisir votre mode de payement:</h4>
	    		<div class="row">
	    		<script async="async" src="https://www.paypalobjects.com/js/external/paypal-button.min.js?merchant=expresslivraison.montreal-facilitator@gmail.com" 
				    data-button="buynow" 
				    data-name="Commande expresLivraison" 
				    data-quantity="1" 
				    data-amount="<c:out value="${order.totalPrice}"/>" 
				    data-currency="CAD" 
				    data-callback="http://130.211.117.111/Log210_ServiceLivraison/Restrict/Client/OrderDone"  
				    data-return="http://130.211.117.111/Log210_ServiceLivraison/Restrict/Client/OrderDone" 
				    data-env="sandbox"
				></script>
	    		</div>
	    </div>
	</div>
</div>  
 		 
	
<jsp:include page="/footer.jsp"></jsp:include>