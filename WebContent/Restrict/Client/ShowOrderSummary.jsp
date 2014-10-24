<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	

<div class="row">
	  	<div class="col-md-12 col-xs-12"> 		 
	 			 <h1 class="text-center" >Résumé de votre commande</h1>  	
	 			 <br>
	 	</div>
</div> 
<div class="row">
	<div class="col-md-6 col-md-offset-2">
		<div class="table-responsive">
			<table class="table table-striped">
			 	<thead>
			 		<tr>
			 			<th>Nom du Plat</th>
			 			<th>Description</th>
			 			<th>Prix</th>
			 			<th>Quantité</th>
			 		</tr>
			 	</thead>
			 	<tbody>
			 		<c:forEach items="${order.mealListAndQuantity}" var="meal">
					<tr>
			 		 	<td><c:out value="${activeResto.name}"/></td>
			 		 	<td><c:out value="${activeResto.description}"/></td>
			 		 	<td><c:out value="${activeResto.phoneNumber}"/></td>
			 		 	<td><c:out value="${activeResto.kindOfFood}"/></td>
			 		 	<td><c:out value="${activeResto.restaurateurName}"/></td>
					 </tr>			  		 	
				   </c:forEach> 
			 		</tbody>	
			 		<tfoot>
			 		<tr>
			 			<th colspan="4">Total</th>
			 			<th><c:out value="${order.totalPrice}"/>$</th>
			 		</tr>
			 		</tfoot>	   
			</table>
		</div>
 	 </div>
</div>  		 
  		 
  		 
  		 
	
<jsp:include page="/footer.jsp"></jsp:include>