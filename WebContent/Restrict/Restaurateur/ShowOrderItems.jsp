<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="/header.jsp"></jsp:include>


<!-- Affiche tous les menus par restaurant-->

<div class="col-md-5 col-md-offset-5">
	
			

	<a href="<c:url value="/HomeOrderRestaurant"/>"
		data-toggle="modal"> <!-- Affiche tous les menus par restaurant-->
		<button type="button" class="btn btn-warning">Mes restaurants
		</button>

	</a>

</div>

<form class="form" method=post
	action="<c:out value="/ShowAllMenuResto"/>">

		<div class="col-md-8 col-md-offset-2">
			<div class="table-responsive">
				<h3>LISTE ITEMS</h3>
			
			<!-- LISTE DES ITEMS -->
			<table class="table table-striped">
					<thead>
						<tr>
							<th>Nom du plat</th>
							<th>Description</th>
							<th>Quantité</th>
							<th>Prix</th>
						</tr>
					</thead>
					<!--  Le tableau des menus -->
					<tbody>

			
				   		<c:forEach items="${orderItemList}" var="item">
		  		 				
		  		 				<tr>
		  		 					
									<td><c:out value="${item.name}"/></td>
									<td><c:out value="${item.description}"/></td>
									<td><c:out value="${item.quantity}"/></td>
									<td><c:out value="${item.price}"/></td>
							 	</tr>
					  					  		 	
					  	   </c:forEach>
					</tbody>
				</table>
				<div class="modal-footer">

					<a href="<c:url value="/ShowOrderRestaurant"/>"
							data-toggle="modal"> <!-- Affiche tous les menus par restaurant-->
						<button type="button" class="btn btn-warning">OK
						</button>

					</a>
				
				
				
			</div>
		</div>
	</div>
</form>


<!--  Affichage des menus -->

<jsp:include page="/footer.jsp"></jsp:include>
