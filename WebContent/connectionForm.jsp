
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

	<div class='row'>
	<div class="wrapper">
		<form  class="form-signin"role="form" method=post action="<c:url value="/UserConnection"/>">   
		 
		
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
            
            
            <%-- Vérification de la présence d'un objet utilisateur en session --%>
            <c:if test="${!empty sessionScope.userSession}">
                <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
              <p class="succes">Vous etes connecté(e) avec l'adresse : ${sessionScope.userSession.email}</p>            
            </c:if>
            
      	</form>
      	 </div>
      	  </div>
      	 <div class="modal fade" id="signup" role="dialog">
      	 	<div class="modal-dialog">
      	 		<div class="modal-content">
      	 			<form class="form-horizontal">
	      	 		 <div class="modal-header">
	      	 			<h3>Inscription</h3>
	      	 		</div>
	      	 		<div class="modal-body">
	      	 		
	      	 			<div class="form-group">
	      	 				<label for="contact-name" class="col-lg-4 control-label">Nom:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="contact-name" placeholder="Nom"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="contcat-firstname" class="col-lg-4 control-label">Prenom:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="contact-firstname" placeholder="Prenom"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="contcat-emai" class="col-lg-4 control-label">Nom:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="contact-email" placeholder="Courriel"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="contcat-password1" class="col-lg-4 control-label">Mot de passe:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="contact-password1" placeholder="Mot de passe"/>
	      	 				</div>
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="contcat-password2" class="col-lg-4 control-label">Verification:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="contact-password2" placeholder="Confirmer mot de passe"/>
	      	 				</div>	      	 				
	      	 			</div>
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="contcat-birthday" class="col-lg-4 control-label">Date de naissance:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="contact-brithday" placeholder="Ex: 01/01/2014"/>
	      	 				</div>	      	 				
	      	 			</div>
	      	 			
	      	 			
	      	 			<div class="form-group">
	      	 				<label for="contcat-adresse" class="col-lg-4 control-label">Adresse:</label>
	      	 				<div class="col-lg-8">
	      	 					<input type="text" class="form-control" id="contact-adresse" placeholder="4589 Bd Saint-Denis H3P 1O2 Montréal"/>
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
      
      

              
