<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
  		 
  		 <p>Mon Compte<p> 
  		 
  		 <a href="#signup" data-toggle="modal">Modifier mon compte</a>
  		 <!-- Formulaire d'inscription -->
      	 <div class="modal fade" id="signup" role="dialog">
      	 	<div class="modal-dialog">
      	 		<div class="modal-content">

			<!-- <form  class="form-horizontal" role="form" method=post action="<c:url value="/Subscribe"/>">    -->	
				<form  name=cmpid class="form-horizontal" role="form" method="post"  action="<c:url value="/Subscribe"/>">   	      	 		 <div class="modal-header">
	      	 			<h3>Inscription</h3>
	      	 		</div>
	      	 		<div class="modal-body">
	      	 		
	      	 			<div class="form-group">
	      	 				<label for="name" class="col-lg-4 control-label">Nom:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" name="name" id="name" placeholder="Nom" value="${sessionScope.userSession.name}"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="firstname" class="col-lg-4 control-label">Prenom:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="firstname" name="firstname" placeholder="Prenom" value="${sessionScope.userSession.name}"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="emai" class="col-lg-4 control-label">Courriel:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="email" class="form-control" name="email" id="email" placeholder="Courriel" value="${sessionScope.userSession.name}"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="phone" class="col-lg-4 control-label">Telephone:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="phone" name="phone" placeholder="514 111 1111" value="${sessionScope.userSession.name}"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="password1" class="col-lg-4 control-label">Mot de passe:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="password" class="form-control" id="password1" name="password1" placeholder="Mot de passe" value="${sessionScope.userSession.name}"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="password2" class="col-lg-4 control-label" >Verification:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="password" class="form-control" id="password2" name="password2" placeholder="Confirmer mot de passe"/>
	      	 				</div>	      	 				
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="birthday" class="col-lg-4 control-label">Date de naissance:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="brithday" name="birthday" placeholder="Ex: 01/01/2014" value="${sessionScope.userSession.name}"/>
	      	 				</div>	      	 				
	      	 			</div>
	      	 			
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="adress" class="col-lg-4 control-label">Adresse:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="adress" name="adress" placeholder="4589 Bd Saint-Denis H3P1O2 Montreal" value="${sessionScope.userSession.name}"/>
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
      	 
	
<jsp:include page="/footer.jsp"></jsp:include>