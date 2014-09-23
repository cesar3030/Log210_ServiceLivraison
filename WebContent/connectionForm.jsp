
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!-- Formulaire de connection -->
	<div class='row'>
	<div class="wrapper">
		<form  class="form-signin" role="form" method=post action="<c:url value="/UserConnection"/>">   
		 
		
            <div class="form-group">
            	    <div class='row'>	
	           		<div class="col-md-offset-4 col-md-4">
	            		
	            			<legend>Formulaire de connexion</legend>
	            		
	            			<label for="email">Adresse courriel <span class="requis">*</span></label>
	            			<input type="email" class="form-control" id="email" placeholder="Entrer courriel" name="email" value="<c:out value="cesar.jeanroy@gmail.com"/>" />
	            		
	            			<p class="text-error">${form.email}</p>
	            		</div>
	        		 </div>
	        	</div>
			
			<div class="form-group">
	            <div class='row'>	
	            
	         	   <div class="col-md-offset-4 col-md-4">
	         	   		<label for="password">Mot de passe <span class="requis">*</span></label>
	            			<input type="password" class="form-control" id="password" placeholder="Mot de passe" name="password" value="<c:out value="aaaaaa"/>" size="20" maxlength="20" />
	            	  
	            			<p class="text-error">${form.password}</p>
	            			<p class="text-error">${form.unknowUser}</p>
	            		 </div>
	            	</div>
            </div>
         
            
		    <div class='row'>
		       <div class="col-md-offset-4 col-md-4">
			 	  <div class="form-group">
		            <!-- <input type="submit" value="Connexion" class="sansLabel" /> -->
		            		
	            	     <button type="submit" class="btn btn-default">Connexion</button>
	            		<!-- <a href="<c:url value="/Signin"/>">S'inscrire ?</a> -->
	            		        <a href="#signup" data-toggle="modal">S'inscrire ?</a>
		           </div>
		         </div>
		     </div>            
            
            
            <%-- V������������������rification de la pr������������������sence d'un objet utilisateur en session --%>
            <c:if test="${!empty sessionScope.userSession}">
                <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
              <p class="succes">Vous etes connecte(e) avec l'adresse : ${sessionScope.userSession.email}</p>            
            </c:if>
            
            
 
      	</form>
      	 </div>
      	  </div>
      	  
      	  
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
	      	 					<input type="text" class="form-control" name="name" id="name" placeholder="Nom" />
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="firstname" class="col-lg-4 control-label">Prenom:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="firstname" name="firstname" placeholder="Prenom"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="emai" class="col-lg-4 control-label">Courriel*:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="email" class="form-control" name="email" id="email" placeholder="Courriel" required/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="phone" class="col-lg-4 control-label">Telephone*:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="phone" name="phone" placeholder="514 111 1111" required/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="password1" class="col-lg-4 control-label">Mot de passe*:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="password" class="form-control" id="password1" name="password1" placeholder="Mot de passe" required/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="password2" class="col-lg-4 control-label" >Verification*:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="password" class="form-control" id="password2" name="password2" placeholder="Confirmer mot de passe" required/>
	      	 				</div>	      	 				
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="birthday" class="col-lg-4 control-label">Date de naissance:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="brithday" name="birthday" placeholder="Ex: AAAA-MM-JJ" required/>
	      	 				</div>	      	 				
	      	 			</div>
	      	 			
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="adress" class="col-lg-4 control-label">Adresse*:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="adress" name="adress" placeholder="4589 Bd Saint-Denis H3P1O2 Montreal" required/>
	      	 				</div>	      	 				
	      	 			</div>
	      	 			
	      	 			      	 			
	      	 		</div>
	      	 		<div class="modal-footer">
	      	 		
	      	 			<a class="btn btn-default" data-dismiss="modal" >Annuler</a>
	      	 			<button class="btn btn-primary" type="submit" >Inscription</button>
	      	 			
	      	 		</div>
	      	 		</form>
      	 		</div>
      	 	</div>
      	 </div>
      	 
      	 <!-- J'inclus le script qui v������rifie le formulaire d'inscription lorsqu'il est soumis -->
      	 <script src="<c:url value="/inc/js/SignInFormValidator.js"/>"></script>
      

              
