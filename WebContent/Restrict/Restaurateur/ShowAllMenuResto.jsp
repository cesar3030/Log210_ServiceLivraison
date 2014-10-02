<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	


  <!-- Affiche tous les menus par restaurant-->
  				
		<div class="modal-header">
				<h3>Tous les menus du Restaurant : Afficher nameRestaurant</h3>
		</div>
				  		
		<form class="form"  method=post  action="<c:out value="/ShowAllMenuResto"/>">		
			<div class="row">
  		 	<div class="col-md-8 col-md-offset-2">
		  		 <div class="table-responsive" >
					  <table class="table table-striped">
					   	<thead>
					   		<tr>
					   			<th>ID du menu</th>
					   			<th>Nom du menu</th>
					   			<th>Id du restaurant</th>
					   			<th>Description du menu</th>
					   		 </tr>
					   	</thead>
					   	<tbody>
					   		<c:forEach items="${activeMenuRestaurantList}" var="ListeMenuActive">
		  		 				<tr>
						  		 	<tr id="<c:out value="${ListeMenuActive.idMenu}"/>">
						  			<td><c:out value="${ListeMenuActive.idMenu}"/></td>
						  			<td><c:out value="${ListeMenuActive.name}"/></td>
						  		 	<td><c:out value="${ListeMenuActive.idRestaurant}"/></td>
						  		 	<td><c:out value="${ListeMenuActive.description}"/></td>
						  		 	<td><c:out value="${ListeMenuActive.visible}"/></td>
						  		 					  		 	
						  		 	<td><span class="glyphicon glyphicon-cog text-center"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-trash text-center"></span></td>						  		 	
					  		 	</tr>			  		 	
					  	   </c:forEach> 		 
		  		 		</tbody>			   
					 </table>
				   </div>
				</div>
			</div>
		</form>
			<!--  Affichage des menus -->
					
		
					
						
		 				<div class="col-md-5 col-md-offset-5">
			  				 <a href="<c:url value="/HomeRestaurateurManagement"/>" data-toggle="modal">
			  				 <button type="button" class="btn btn-info" >Retourner a la liste des restaurants</button></a>		
						</div>
		
		  		 
  		 
  		 
<jsp:include page="/footer.jsp"></jsp:include>