<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	


  <!-- Affiche tous les menus par restaurant-->
  				
		<div class="modal-header">
				<h3>Tous les menus du Restaurant : Afficher nameRestaurant</h3>
		</div>
			
				 		  
  		  <c:if test="${!empty returnMessage.succes}">
				<div class="row">
		  		 	<div class="col-md-6 col-md-offset-3 alert alert-success" role="alert">
		  		 		<h5 class="text-center" >${returnMessage.succes}</h5>
		  		 	</div> 		 
		  		 </div>
		 </c:if>
  		 
  		 
  		 <c:if test="${!empty returnMessage.fail}">
				<div class="row">
		  		 	<div class="col-md-6 col-md-offset-3 alert alert-danger" role="alert">
		  		 		<h5 class="text-center">${returnMessage.fail}</h5>
		  		 	</div> 		 
		  		 </div>
		</c:if>
		
		<c:if test="${empty returnMessage}">
				<br>
		 	    <br>
		</c:if>  
										  		
								
			<div class="row">
  		 	<div class="col-md-8 col-md-offset-2">
		  		 <div class="table-responsive">
					  <table class="table table-striped">
					   	<thead>
					   		<tr>
					   			<th>ID du menu</th>
					   			<th>Nom du menu</th>
					   			<th>Description du menu</th>
					   		</tr>
					   	</thead>
					   	<tbody>
					   		<c:forEach items="${activeMenuRestaurantList}" var="activeMenu">
		  		 				<tr>
						  		 	<td><c:out value="${activeMenu.idMenu}"/></td>
						  		 	<td><c:out value="${activeResto.name}"/></td>
						  		 	<td> <c:out value="${activeResto.description}"/></td>
						  		 	<td><span class="glyphicon glyphicon-cog text-center"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-trash text-center"></span></td>						  		 	
					  		 	</tr>			  		 	
					  	   </c:forEach> 		 
		  		 		</tbody>			   
					 </table>
				   </div>
				</div>
			</div>
			<!--  Affichage des menus -->
					
		
					
						<div class="modal-footer">
		
						<button type="button" class="btn btn-danger">Retourner a la liste des restaurants</button>		
						</div>
						</form>
		  		 
  		 
  		 
<script src="<c:url value="/inc/js/AddMenu.js"/>"></script>
<jsp:include page="/footer.jsp"></jsp:include>