<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="/header.jsp"></jsp:include>


<!-- Affiche tous les plats par menus-->

<div class="col-md-5 col-md-offset-5">
	<h3>Tous les plats du Menu : ${menuTitleName}</h3>

		<a href="<c:url value="#newMeal"/>" data-toggle="modal">
				<button type="button" class="btn btn-warning">Ajouter un
					plat au menu</button>
					</a>
</div>


<form class="form" method=post action="<c:out value="ShowAllMealMenu"/>">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Nom du plat</th>
							<th>Prix du plat</th>
							<th>Description du plat</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mealMenuList}" var="mealMenuList">
							<tr>
							<tr id="<c:out value="${mealMenuList.idMeal}"/>">
								<td><c:out value="${mealMenuList.name}" /></td>
								<td><c:out value="${mealMenuList.price}" /></td>
								<td><c:out value="${mealMenuList.description}" /></td>

								<td><a href="<c:url value="/DeleteMeal?idMeal=${mealMenuList.idMeal}&nameMeal=${mealMenuList.name}"/>"><span
									class="glyphicon glyphicon-trash text-center"></span>	</a></td>
								
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
<br>
<br>
<div class="col-md-5 col-md-offset-5">
	<a href="<c:url value="/ShowAllMenuResto?idRestaurant=${idRestaurant}"/>" data-toggle="modal">
		<button type="button" class="btn btn-danger">Retourner a la
			liste des menus</button>
	</a>
</div>

	  <c:if test="${sessionScope.retourInt==1}">
	  		  <script>
		  		  	
		  		toastr.options = {
		  			  "closeButton": true,
		  			  "debug": false,
		  			  "positionClass": "toast-top-right",
		  			  "onclick": null,
		  			  "showDuration": "300",
		  			  "hideDuration": "1000",
		  			  "timeOut": "5000",
		  			  "extendedTimeOut": "1000",
		  			  "showEasing": "swing",
		  			  "hideEasing": "linear",
		  			  "showMethod": "fadeIn",
		  			  "hideMethod": "fadeOut"
		  			}
		  		  
		  		 toastr.success('Le plat ${sessionScope.retourString} a été supprimé avec succès !', 'Succès');
		  		
	  		 </script>
		</c:if>
		
		<c:if test="${sessionScope.retourInt==0}">
	  		  <script>
		  		  	
		  		toastr.options = {
		  			  "closeButton": true,
		  			  "debug": false,
		  			  "positionClass": "toast-top-right",
		  			  "onclick": null,
		  			  "showDuration": "300",
		  			  "hideDuration": "1000",
		  			  "timeOut": "5000",
		  			  "extendedTimeOut": "1000",
		  			  "showEasing": "swing",
		  			  "hideEasing": "linear",
		  			  "showMethod": "fadeIn",
		  			  "hideMethod": "fadeOut"
		  			}
		  		  
		  	  toastr.success('Le plat ${sessionScope.retourString} a été ajouté avec succès !', 'Succès');
		  		
	  		 </script>
		</c:if>
		
				
		<c:if test="${sessionScope.retourInt==3}">
	  		  <script>
		  		  	
		  		toastr.options = {
		  			  "closeButton": true,
		  			  "debug": false,
		  			  "positionClass": "toast-top-right",
		  			  "onclick": null,
		  			  "showDuration": "300",
		  			  "hideDuration": "1000",
		  			  "timeOut": "5000",
		  			  "extendedTimeOut": "1000",
		  			  "showEasing": "swing",
		  			  "hideEasing": "linear",
		  			  "showMethod": "fadeIn",
		  			  "hideMethod": "fadeOut"
		  			}
		  		  
		  	  toastr.warning('Le plat ${sessionScope.retourString} a été ajouté avec succès mais sans description !', 'Attention');
		  		
	  		 </script>
		</c:if>
		
		



<jsp:include page="/Restrict/Restaurateur/newMealForm.jsp"></jsp:include>

<jsp:include page="/footer.jsp"></jsp:include>