<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	


		 <div class="row">
  		  	<div class="col-md-12"> 		 
  		 		 <h2 class="text-center" >Liste des restaurants</h1>  		 		 
  		 	</div>
  		  </div>
  		  <c:if test="${!empty returnMessage.succes}">
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
	  		  
	  			 toastr.success('${returnMessage.succes}', 'Succes');
	  		 </script>
		</c:if>
  		 
  		 <c:if test="${!empty returnMessage.fail}">
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
	  			 toastr.error('${returnMessage.fail}', 'Erreur')
	  		</script>
		</c:if>
		
		 <c:if test="${empty returnMessage}">
				<br>
		 	    <br>
		</c:if>  		  
  		  
  		 <div class="row">
  		 	<div class="col-md-5 col-md-offset-1">  
  				 <h4>Liste des restaurants visibles:</h2> 
  		  	</div>
  		  	<div class="col-md-2 col-md-offset-4">  
  				 <a href="#newRestaurant" data-toggle="modal"><h5>Ajouter un restaurant</h5></a>
  		  	</div>
  		  </div>
  		  
  		  <br>
  		  
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
					   			<th>Restaurateur</th>
					   			<th>Opérations</th>
					   		</tr>
					   	</thead>
					   	<tbody>
					   		<c:forEach items="${activeRestaurantList}" var="activeResto">
		  		 				<tr id="<c:out value="${activeResto.idRestaurant}"/>">
						  		 	<td id="name_<c:out value="${activeResto.idRestaurant}"/>" ><c:out value="${activeResto.name}"/></td>
						  		 	<td id="address_<c:out value="${activeResto.idRestaurant}"/>"><c:out value="${activeResto.address}"/></td>
						  		 	<td id="phoneNumber_<c:out value="${activeResto.idRestaurant}"/>"><c:out value="${activeResto.phoneNumber}"/></td>
						  		 	<td id="kindOfFood_<c:out value="${activeResto.idRestaurant}"/>"><c:out value="${activeResto.kindOfFood}"/></td>
						  		 	<td id="resaurateurName_<c:out value="${activeResto.idRestaurant}"/>"><c:out value="${activeResto.restaurateurName}"/></td>
						  		 	<td><a  onclick="updateRestaurantDatas(this.parentNode.parentNode.id);"><span class="glyphicon glyphicon-cog text-center"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value="/SwitchRestaurantToNotVisible?idRestaurant=${activeResto.idRestaurant}"/>"><span class="glyphicon glyphicon-trash text-center"></span></a></td>						  		 	
					  		 	</tr>			  		 	
					  	   </c:forEach> 		 
		  		 		</tbody>			   
					 </table>
				   </div>
				</div>
			</div>
		
		
			<br>
			<br>
		
		
		  <div class="row">
		  	<div class="col-md-3 col-md-offset-1">  		 
				 <h4>Restaurants supprimés:</h2> 
		  	</div>
		  </div>
  		 
  		 <br>
  		 
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
					   			<th>Restaurateur</th>
					   			<th>Opérations</th>
					   		</tr>
					   	</thead>
					   	<tbody>
					   		<c:forEach items="${inactiveRestaurantList}" var="inactiveResto">
		  		 				<tr  id="<c:out value="${inactiveResto.idRestaurant}"/>">
						  		 	<td id="name_<c:out value="${inactiveResto.idRestaurant}"/>" ><c:out value="${inactiveResto.name}"/></td>
						  		 	<td id="address_<c:out value="${inactiveResto.idRestaurant}"/>"><c:out value="${inactiveResto.address}"/></td>
						  		 	<td id="phoneNumber_<c:out value="${inactiveResto.idRestaurant}"/>"><c:out value="${inactiveResto.phoneNumber}"/></td>
						  		 	<td id="kindOfFood_<c:out value="${inactiveResto.idRestaurant}"/>"><c:out value="${inactiveResto.kindOfFood}"/></td>	
						  		 	<td id="resaurateurName_<c:out value="${inactiveResto.idRestaurant}"/>"><c:out value="${inactiveResto.restaurateurName}"/></td>
						  		 	<td>&nbsp;&nbsp;&nbsp;<a href="<c:url value="/SwitchRestaurantToVisible?idRestaurant=${inactiveResto.idRestaurant}"/>"><span class="glyphicon glyphicon-repeat center"></span></a></td>						  		 						  		 	
					  		 	</tr>			  		 	
					  	   </c:forEach> 		 
		  		 		</tbody>			   
					 </table>
				   </div>
				</div>
			</div>
			
			
<script src="<c:url value="/inc/js/fillRestaurantForm.js"/>"></script>
<jsp:include page="/Restrict/Admin/NewRestaurantForm.jsp"></jsp:include>
<jsp:include page="/Restrict/Admin/UpdateRestaurantForm.jsp"></jsp:include>
<jsp:include page="/footer.jsp"></jsp:include>