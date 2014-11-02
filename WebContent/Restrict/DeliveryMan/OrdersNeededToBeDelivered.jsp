<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
<div class="row">
	<div class="col-md-12"> 		 
		 <h2 class="text-center" >Liste des commandes a prendre en charge</h2>  		 		 
	</div>
</div>

<c:forEach items="${orderList}" var="order">
<div class="row">
	<div class="panel panel-primary col-md-10 col-md-offset-1">
		<div class="col-md-5 col-md-offset-1">
			<p>Date et heure livraison:</p>
			<p>Nom et prénom du client:</p>
			<p>Adresse de livraison:</p>
			<p>Restaurant: </p>
		</div>
		<div class="col-md-5 col-md-offset-1">
			<p>Carte:</p>
		</div>
	</div>	
</div>
</c:forEach> 	 
  		 
  		 
  		 
  		 
	
<jsp:include page="/footer.jsp"></jsp:include>