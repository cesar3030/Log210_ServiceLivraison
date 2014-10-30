<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="modal fade" id="ModifyMenu" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			
			<form name="cmpid" class="form-horizontal" role="form" method="get"  >
				<div class="modal-header">
				<h3>Items Menu</h3>
				</div>
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
			</form>
		</div>
	</div>
</div>
