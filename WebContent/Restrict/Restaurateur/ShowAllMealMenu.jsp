<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	


  <!-- Affiche tous les menus par restaurant-->
  				
		<div class="modal-header">
				<h3>Tous les plats du Menu :</h3>
				<p class="text-center">${menuName}</p>
		</div>
				  		
		<form class="form"  method=post  action="<c:out value="ShowAllMealMenu"/>">		
			<div class="row">
  		 	<div class="col-md-8 col-md-offset-2">s
		  		 <div class="table-responsive" >
					  <table class="table table-striped">
					   	<thead>
					   		<tr>
					   		 	<th>Nom du plat</th>
					   			<th>Description du plat</th>
					   			<th>Supprimer plat</th>
					   		 </tr>
					   	</thead>
					   	<tbody>
					   		<c:forEach items="${activeMenuRestaurantList}" var="ListeMenuActive">
		  		 				<tr>
						  		 	<tr id="<c:out value="${ListeMenuActive.idMenu}"/>">
						  			<td><c:out value="${ListeMenuActive.name}"/></td>
						  		 	<td><c:out value="${ListeMenuActive.description}"/></td>
						  		 	<!--  ON PEUT AUSSI RECUPERE LID MENU RESTO ET VISIBILITE SI ON VEUT -->			  		 					  		 	
						  		 	<td><span class="glyphicon glyphicon-cog text-center"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-trash text-center"></span></td>						  		 	
					  		 	</tr>			  		 	
					  	   </c:forEach> 	
		  		 		</tbody>			   
					 </table>
					 <button type="button" class="btn btn-warning">Ajouter un menu au restaurant : ${restaurantTitreName}</button>
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
			  				 <a href="<c:url value="/HomeRestaurateurManagement"/>" data-toggle="modal">
			  				 <button type="button" class="btn btn-danger" >Retourner a la liste des restaurants</button></a>		
						</div>
		
		  		 
  		 
  		 
<jsp:include page="/footer.jsp"></jsp:include>