<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
<div class="row">
	<div class="col-md-12"> 		 
		 <h2 class="text-center" >Liste des commandes a prendre en charge</h2>  		 		 
	</div>
</div>

<c:forEach items="${orderReady}" var="orderForDelivery">
<div class="row">
	<div class="panel panel-primary col-md-10 col-md-offset-1">
		<div class="col-md-5 col-md-offset-1">
			<p>Date et heure livraison: <c:out value="${orderForDelivery.order.hourAndDate}"/></p>
			<p>Nom et prénom du client: <c:out value="${orderForDelivery.client.firstName}"/> <c:out value="${orderForDelivery.client.name}"/></p>
			<p>Restaurant: <c:out value="${orderForDelivery.restaurant.name}"/></p>
			<p>Adresse du restaurant: <c:out value="${orderForDelivery.restaurant.address}"/></p>
			<p>Adresse de livraison: <c:out value="${orderForDelivery.address.address}"/></p>
			
			
		</div>
		<div class="col-md-5 col-md-offset-1">
			<p>Carte:</p>
		</div>
	</div>	
</div>
</c:forEach> 	 
  		 
  		 
  		 
  		 
	
<jsp:include page="/footer.jsp"></jsp:include>