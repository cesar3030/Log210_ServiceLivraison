<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="/header.jsp"></jsp:include>


<!-- Affiche tous les menus par restaurant-->

<div class="col-md-5 col-md-offset-5">
	
			
	<h3>${restaurantTitreName}: Tous les menus</h3>

	<a href="<c:url value="/HomeOrderRestaurant"/>"
		data-toggle="modal"> <!-- Affiche tous les menus par restaurant-->
		<button type="button" class="btn btn-warning">Mes restaurants
		</button>

	</a>

</div>

<form class="form" method=post
	action="<c:out value="/ShowAllMenuResto"/>">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="table-responsive">
			
				<p class="text-center">A PREPARER.</p>
				<!-- LISTE DES COMMANDES A PREPARER -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th>N° Commande</th>
							<th>Statut</th>
							<th>Date</th>
							<th>Voir Items</th>
							<th>Mettre en préparation</th>
							
							
						</tr>
					</thead>
					<!--  Le tableau des menus -->
					<tbody>
				   		<c:forEach items="${orderList0}" var="Apreparer">
		  		 				
		  		 				<tr>
		  		 					
						  		 	<td><c:out value="${Apreparer.idOrder}"/></td>
						  		 	<td><c:out value="${Apreparer.status}"/></td>
						  		 	<td> <c:out value="${Apreparer.hourAndDate}"/></td>
						  		 	<td><a href="<c:url value="/ShowOrderItems?idOrder=${Apreparer.idOrder}"/>"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
						  		 	<td><a href="<c:url value="/OrderUpdateState?idOrder=${Apreparer.idOrder}&status=${Apreparer.status}"/>"><span class="glyphicon glyphicon-thumbs-up"></span></a></td>						  		 	
					  		 	</tr>
					  					  		 	
					  	   </c:forEach>
					</tbody>
				</table>
				
				<p class="text-center">EN PREPARATION.</p>
				<!-- LISTE DES COMMANDES EN PREPARATION -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th>N° Commande</th>
							<th>Statut</th>
							<th>Date</th>
							<th>Voir Items</th>
							<th>Prête</th>
							
						</tr>
					</thead>
					<!--  Le tableau des menus -->
					<tbody>

			
				   		<c:forEach items="${orderList1}" var="orderEnPrep">
		  		 				
		  		 				<tr>
		  		 					
									<td><c:out value="${orderEnPrep.idOrder}"/></td>
						  		 	<td><c:out value="${orderEnPrep.status}"/></td>
						  		 	<td> <c:out value="${orderEnPrep.hourAndDate}"/></td>
						  		 	<td><a href="<c:url value="/ShowOrderItems?idOrder=${orderEnPrep.idOrder}"/>"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
						  		 	<td><a href="<c:url value="/OrderUpdateState?idOrder=${orderEnPrep.idOrder}&status=${orderEnPrep.status}"/>"><span class="glyphicon glyphicon-cutlery"></span></a></td>								  		 	
					  				
					  		 	</tr>
					  					  		 	
					  	   </c:forEach>
					</tbody>
				</table>
				
					<p class="text-center">TERMINEES.</p>
				<!-- LISTE DES COMMANDES FAITES -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th>N° Commande</th>
							<th>Statut</th>
							<th>Date</th>
							<th>Voir Items</th>
						</tr>
					</thead>
					<!--  Le tableau des menus -->
					<tbody>

			
				   		<c:forEach items="${orderList2}" var="end">
		  		 				
		  		 				<tr>
		  		 					
									<td><c:out value="${end.idOrder}"/></td>
						  		 	<td><c:out value="${end.status}"/></td>
						  		 	<td> <c:out value="${end.hourAndDate}"/></td>
				  				    <td><a href="<c:url value="/ShowOrderItems?idOrder=${end.idOrder}"/>"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
					  		 	</tr>
					  					  		 	
					  	   </c:forEach>
					</tbody>
				</table>
				
				
				
			</div>
		</div>
	</div>
</form>


<!--  Affichage des menus -->

<jsp:include page="/footer.jsp"></jsp:include>
