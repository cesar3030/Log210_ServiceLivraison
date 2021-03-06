<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
  		 
  		<div class="col-md-offset-2 col-md-8 "> 	
  		 <h1>Mon Compte</h1> 
  		 <br>
  		 
  		 
  		 <div class="col-md-offset-1 col-md-11 "> 	
  		 
  		 
  		 <c:if test="${!empty form.UpdateCompleted}">
				<div class="row">
		  		 	<div class="alert alert-success" role="alert">
		  		 		<h5>${form.UpdateCompleted}</h5>
		  		 	</div> 		 
		  		 </div>
		</c:if>
  		 
  		 <c:if test="${!empty form.UpdateFail}">
				<div class="row">
		  		 	<div class="alert alert-danger" role="alert">
		  		 		<h5>${form.UpdateFail}</h5>
		  		 	</div> 		 
		  		 </div>
		</c:if>
  		 
  		 <div class="row">
  		 	<label for="name" class="col-md-3 control-label">Nom:</label>
      	 	<div class="col-md-8">
      	 		<h5>${sessionScope.userSession.name}</h5>
      	 	</div>
      	 	</div>
	      	 	
	 	<div class="row">
			<label for="firstname" class="col-md-3 control-label">Prenom:</label>
			<div class="col-md-9">
				<h5>${sessionScope.userSession.firstName}</h5>
			</div>
			</div>
		
		
		<div class="row">
			<label for="emai" class="col-md-3 control-label">Courriel:</label>
			<div class="col-md-9">
				<h5>${sessionScope.userSession.email}</h5>
			</div>
			</div>
		
		
	<div class="row">
			<label for="phone" class="col-md-3 control-label">Telephone:</label>
			<div class="col-md-9">
				<h5 >${sessionScope.userSession.phoneNumber}</h5>
			</div>
			</div>

	<div class="row">
			<label for="birthday" class="col-md-3 control-label">Date de naissance:</label>
			<div class="col-md-9">
				<h5>${sessionScope.userSession.birthdayDate}</h5>
			</div>	 
			</div>     	 				
		
		<div class="bs-callout bs-callout-info">
		
		<div class="row">
			<label for="adress" class="col-md-3 control-label">Adresse:</label>
			<div class="col-md-9">
				<h5>${sessionScope.userSession.homeAddress}</h5>
			</div>	 
			</div>     	 				
	 </div>
  		 
  		<br>
  		<br>
  		 <div class="row">
  		 
  		 <a href="#signup" data-toggle="modal">
  		 	<button class="btn btn-primary"  >Modifier mon compte</button>
  		 </a>
  		 </div>
  		 
  		  </div>
  		
  		 
  		 
  		 <!-- Formulaire de modification de compte-->
      	 <div class="modal fade" id="signup" role="dialog">
      	 	<div class="modal-dialog">
      	 		<div class="modal-content">

			<!-- <form  class="form-horizontal" role="form" method=post action="<c:url value="/Subscribe"/>">    -->	
				<form  name=cmpid class="form-horizontal" role="form" method="post"  action="<c:url value="/UpdateUserAccount"/>">   	      	 		 <div class="modal-header">
	      	 			<h3>Modification</h3>
	      	 		</div>
	      	 		<div class="modal-body">

	      	 			
	      	 			<div class="form-group">
	      	 				<label for="adress" class="col-lg-4 control-label">Adresse:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="adress" name="adress" placeholder="4589 Bd Saint-Denis H3P1O2 Montreal" value="${sessionScope.userSession.homeAddress}" required/>
	      	 				</div>	      	 				
	      	 			</div> 
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="phone" class="col-lg-4 control-label">Telephone:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="phone" name="phone" placeholder="5141245678" value="${sessionScope.userSession.phoneNumber}"  onblur="verifierNumeroTel();" required/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="password1" class="col-lg-4 control-label">Mot de passe:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="password" class="form-control" id="password1" name="password1" placeholder="Mot de passe" value="${sessionScope.userSession.password}"  required/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="password2" class="col-lg-4 control-label" >Verification:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="password" class="form-control" id="password2" name="password2" placeholder="Confirmer mot de passe"  onblur="concordanceMdp();" required/>
	      	 				</div>	      	 				
	      	 			</div>
		      	 			
	      	 			      	 			
	      	 		</div>
	      	 		<div class="modal-footer">
	      	 		
	      	 			<a class="btn btn-default" data-dismiss="modal" >Annuler</a>
	      	 			<button class="btn btn-primary" type="submit" >Confirmer</button>
	      	 			
	      	 		</div>
	      	 		</form>
      	 		</div>
      	 	</div>
      	 </div>
      	</div>
      	<!-- J'inclus le script qui v������������������rifie le formulaire d'inscription lorsqu'il est soumis -->
<script src="<c:url value="/inc/js/SignInFormValidator.js"/>"></script>
	
<jsp:include page="/footer.jsp"></jsp:include>