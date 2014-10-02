<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	


		 <div class="row">
  		  	<div class="col-md-11 col-md-offset-1"> 		 
  		 		 <h1 class="text-center" >Ma Liste de restaurants</h1>  		 		 
  		 	</div>
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
					   			<th>Nom</th>
					   			<th>Adresse</th>
					   			<th>Numéro de téléphone</th>
					   			<th>Type de nourriture</th>
					   			<th>Visualiser les menus resturant</th>
					   		</tr>
					   	</thead>
					   	<tbody>
					   		<c:forEach items="${activeRestaurantList}" var="activeResto">
		  		 				
		  		 				<tr>
		  		 					
						  		 	<td><a href="<c:url value="/ShowAllMenuResto?idRestaurant=${activeResto.idRestaurant}"/>"><c:out value="${activeResto.name}"/></a></td>
						  		 	<td><c:out value="${activeResto.address}"/></td>
						  		 	<td> <c:out value="${activeResto.phoneNumber}"/></td>
						  		 	<td><c:out value="${activeResto.kindOfFood}"/></td>
						  		 	<td><a href="<c:url value="/ShowAllMenuResto?idRestaurant=${activeResto.idRestaurant}"/>"><span class="glyphicon glyphicon-eye-open"></span></a></td>						  		 	
					  		
					  		 	</tr>
					  					  		 	
					  	   </c:forEach> 		 
		  		 		</tbody>			   
					 </table>
				   </div>
				</div>
			</div>
		
					
<jsp:include page="/footer.jsp"></jsp:include>