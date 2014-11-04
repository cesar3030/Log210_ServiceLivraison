<!-- Page HTML qui permet d'afficher les items contenus dans une commande au Niveau d'un restaurateur -->
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
				<h3>Commande N° ${numCommande}</h3>
			
			<!-- LISTE DES ITEMS -->
			<table class="table table-striped">
			
			<!-- Les noms des différentes colonnes du tableau -->
					<thead>
						<tr>
							<th>Facture N°</th>
							<th>Nom du plat</th>
							<th>Description</th>
							<th>Quantité</th>
							<th>Montant Total Items</th>
						</tr>
					</thead>
					<!--  Le tableau des menus -->
					<tbody>

			
						<!-- Boucle qui permet de récupérer les items d'une commande et de les afficher TB HTML -->			
				   		<c:forEach items="${orderItemList}" var="item">
		  		 				<tr>
		  		 					<td><c:out value="${item.idOrderItem}"/></td>
									<td><c:out value="${item.name}"/></td>
									<td><c:out value="${item.description}"/></td>
									<td><c:out value="${item.quantity}"/></td>
									<td><c:out value="${item.price}"/></td>
							 	</tr>
					  					  		 	
					  	   </c:forEach>
					</tbody>
				</table>
				<div class="modal-footer">

					<button class="btn btn-primary" type="button">
						  Total $ :  <span class="badge">${total}</span>
					</button>
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
