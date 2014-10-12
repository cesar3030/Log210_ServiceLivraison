<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="/header.jsp"></jsp:include>


<!-- Affiche tous les menus par restaurant-->

<!--<c:set var="insertion" value="false" scope="session" />-->

<div class="col-md-5 col-md-offset-5">
	<h3>Tous les menus du Restaurant :</h3>
		<a 	href="<c:url value="#addMenu"/>"  data-toggle="modal">
			<!-- Affiche tous les menus par restaurant-->
				<button type="button" class="btn btn-warning">Ajouter un
					menu : ${restaurantTitreName} ID DU RESTAURANT ${sessionScope.restaurantActuel}
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
							<th>Voir</th>
							<th>Modifier</th>
							<th>Supprimer</th>
						</tr>
					</thead>
					<tbody>
					<!--  Le tableau des menus -->
							<!--  Boucle qui va chercher les valeurs des menus -->
							<c:forEach items="${activeMenuRestaurantList}"
								var="ListeMenuActive">
								<tr>
								<!--  Nom du menu -->
								
									<td><c:out value="${ListeMenuActive.name}" /></td>
				
								<!--  Description du menu -->
									<td><c:out value="${ListeMenuActive.description}" /></td>
									
								<!--  Les outils du menu -->
									<td><a href="<c:url value="/ShowAllMealMenu?idMenu=${ListeMenuActive.idMenu}
																&idRestaurant=${sessionScope.restaurantActuel}
																&name=${ListeMenuActive.name}"/>"><span
											class="glyphicon glyphicon-eye-open"></span>
										</a>
									</td>
									<td><span class="glyphicon glyphicon-cog text-center"></span></td>
									
									<td><a href="<c:url value="/DeleteMenu?idMenu=${ListeMenuActive.idMenu}"/>">
										<span class="glyphicon glyphicon-trash text-center"></span></a>
									</td>
									
								</tr>
							</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form>
<!--  Affichage des menus -->


<br>
<br>

<div class="col-md-5 col-md-offset-5">
	<a href="<c:url value="/HomeRestaurateurManagement"/>"
		data-toggle="modal">
		<button type="button" class="btn btn-danger">
		Retour vers liste des restaurants
		</button>
	</a>
</div>


<jsp:include page="/Restrict/Restaurateur/AddMenuFormulaire.jsp"></jsp:include>

<jsp:include page="/footer.jsp"></jsp:include>
