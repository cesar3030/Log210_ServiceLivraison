<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="/header.jsp"></jsp:include>


<!-- Affiche tous les menus par restaurant-->

<!--<c:set var="insertion" value="false" scope="session" />-->
<!--ID DU RESTAURANT ${sessionScope.restaurantActuel}-->
<div class="col-md-5 col-md-offset-5">
	<h3>${restaurantTitreName}: Tous les menus</h3>
	<a href="<c:url value="#addMenu"/>" data-toggle="modal"> <!-- Affiche tous les menus par restaurant-->
		<button type="button" class="btn btn-success">Ajouter un menu
		</button>

	</a> <a href="<c:url value="/HomeRestaurateurManagement"/>"
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
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Nom du menu</th>
							<th>Description du menu</th>
							<th>Voir les plats</th>
							<th>Modifier</th>
							<th>Supprimer</th>
						</tr>
					</thead>
					<!--  Le tableau des menus -->
					<tbody>

						<!--  Boucle qui va chercher les valeurs des menus -->
						<c:forEach items="${activeMenuRestaurantList}"
							var="ListeMenuActive">
							<tr id="<c:out value="${ListeMenuActive.idMenu}"/>">
								<!--  Nom du menu -->
									<td id="name_<c:out value="${ListeMenuActive.idMenu}"/>">
									<c:out value="${ListeMenuActive.name}"/>
								</td>
								<!--  Description du menu -->
								<td id="desc_<c:out value="${ListeMenuActive.idMenu}"/>">
									<c:out value="${ListeMenuActive.description}" />
								</td>
								<!--  Outils Voir les plats -->
								<td><a
									href="<c:url value="/ShowAllMealMenu?idMenu=${ListeMenuActive.idMenu}
																&idRestaurant=${sessionScope.restaurantActuel}
																&name=${ListeMenuActive.name}"/>"><span
										class="glyphicon glyphicon-eye-open"></span> </a></td> 
										
								<td><a onclick="updateMenu(this.parentNode.parentNode.id);">
									<span  class="glyphicon glyphicon-cog text-center"></span>
									</a> 
								</td>
								<td><a
									href="<c:url value="/DeleteMenu?idMenu=${ListeMenuActive.idMenu}"/>">
										<span class="glyphicon glyphicon-trash text-center"></span>
								</a></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form>
<!--  Affichage des menus -->

<script src="<c:url value="/inc/js/updateMenu.js"/>"></script>
<jsp:include page="/Restrict/Restaurateur/AddMenuFormulaire.jsp"></jsp:include>
<jsp:include page="/Restrict/Restaurateur/ModifyMenu.jsp"></jsp:include>
<jsp:include page="/footer.jsp"></jsp:include>
