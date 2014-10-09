<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="modal fade" id="UpdateRestaurateur" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- <form  class="form-horizontal" role="form" method=post action="<c:url value="/Subscribe"/>">    -->
			<form name=cmpid class="form-horizontal" role="form" method="post" action="<c:url value="/UpdateRestaurateur"/>" >
				<div class="modal-header">
					<h3>Modification d'un restaurateur</h3>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name"  class="col-lg-4 control-label">Nom*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="name" id="nameUpdate"
								placeholder="Nom" disabled/>
						</div>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-lg-4 control-label">Prenom:*</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="firstnameUpdate"
								name="firstname" placeholder="Prenom" disabled />
						</div>
					</div>
 
					<div id="email_part" class="form-group">
						<label for="email_subscribe" class="col-lg-4 control-label">Courriel*:</label>
						<div class="col-lg-8">
							<input type="email" class="form-control" name="email" id="email_subscribeUpdate"
								placeholder="Courriel" onblur="Requete_AJAX_Email();" required />
							<div id="error-message"></div>
						</div>
					</div>
					
					

					<div class="form-group">
						<label for="phone" class="col-lg-4 control-label">Telephone*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="phoneUpdate" name="phone"
								placeholder="5141245678"  onblur="verifierNumeroTel();" required />
						</div>
					</div>

	
<!--
					<div class="form-group">
						<label for="birthday" class="col-lg-4 control-label">Date de naissance*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="brithdayUpdate" name="birthday" placeholder="AAAA-MM-JJ"
								onblur="verifierDate();" required />
						</div>
					</div>

 -->
					<div class="form-group">
						<label for="adress" class="col-lg-4 control-label">Adresse*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="adressUpdate" name="adress"
								placeholder="4589 Bd Saint-Denis H3P1O2 Montreal" required />
						</div>
					</div>
					
					
					
					<div class="form-group">
						<label for="password1" class="col-lg-4 control-label">Mot de passe*:</label>
						<div class="col-lg-8">
							<input type="password" class="form-control" id="password1Update"
								name="password1" placeholder="Mot de passe" required />
						</div>
					</div>
					
					

					<div class="form-group">
						<label for="password2" class="col-lg-4 control-label">Verification*:</label>
						<div class="col-lg-8">
							<input onblur="concordanceMdp();" type="password" class="form-control" id="password2Update"
								name="password2" placeholder="Confirmer mot de passe" required />
						</div>
					</div>
	
				<input type="hidden" id="idRestaurateurToUpdate" name="idRestaurateurToUpdate"/>

				</div>
				<div class="modal-footer">

					<a class="btn btn-default" data-dismiss="modal">Annuler</a>
					<button class="btn btn-primary" type="submit">Modifier</button>

				</div>
			</form>
		</div>
	</div>
</div>

<script src="<c:url value="/inc/js/SignInFormValidator.js"/>"></script>
<!-- Scripte qui implement les methodes pour verifier en ajax si l'email inscrit dans le formulaire d'inscription n'est pas deja utilise -->
<script src="<c:url value="/inc/js/ajax.js"/>"></script>