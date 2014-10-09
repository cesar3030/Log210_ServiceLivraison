<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="modal fade" id="newRestaurateur" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- <form  class="form-horizontal" role="form" method=post action="<c:url value="/Subscribe"/>">    -->
			<form name=cmpid class="form-horizontal" role="form" method="post" action="<c:url value="/NewRestaurateur"/>" >
				<div class="modal-header">
					<h3>Nouveau restaurateur</h3>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name"  class="col-lg-4 control-label">Nom*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="name" id="name"
								placeholder="Nom" required />
						</div>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-lg-4 control-label">Prenom:*</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="firstname"
								name="firstname" placeholder="Prenom" required />
						</div>
					</div>

					<div id="email_part" class="form-group">
						<label for="email_subscribe" class="col-lg-4 control-label">Courriel*:</label>
						<div class="col-lg-8">
							<input type="email" class="form-control" name="email" id="email_subscribe"
								placeholder="Courriel" onblur="Requete_AJAX_Email();" required />
							<div id="error-message"></div>
						</div>
					</div>

					<div class="form-group">
						<label for="phone" class="col-lg-4 control-label">Telephone*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="phone" name="phone"
								placeholder="5141245678"  onblur="verifierNumeroTel();" required />
						</div>
					</div>

					<div class="form-group">
						<label for="password1" class="col-lg-4 control-label">Mot de passe*:</label>
						<div class="col-lg-8">
							<input type="password" class="form-control" id="password1"
								name="password1" placeholder="Mot de passe" required />
						</div>
					</div>

					<div class="form-group">
						<label for="password2" class="col-lg-4 control-label">Verification*:</label>
						<div class="col-lg-8">
							<input onblur="concordanceMdp();" type="password" class="form-control" id="password2"
								name="password2" placeholder="Confirmer mot de passe" required />
						</div>
					</div>

					<div class="form-group">
						<label for="birthday" class="col-lg-4 control-label">Date de naissance*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="brithday" name="birthday" placeholder="AAAA-MM-JJ"
								onblur="verifierDate();" required />
						</div>
					</div>


					<div class="form-group">
						<label for="adress" class="col-lg-4 control-label">Adresse*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="adress" name="adress"
								placeholder="4589 Bd Saint-Denis H3P1O2 Montreal" required />
						</div>
					</div>


				</div>
				<div class="modal-footer">

					<a class="btn btn-default" data-dismiss="modal">Annuler</a>
					<button class="btn btn-primary" type="submit">Ajouter</button>

				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
$('#newRestaurateur').on('shown.bs.modal', function () {
	$('#email_subscribe').prop('disabled', false);
	})
</script>
<script src="<c:url value="/inc/js/SignInFormValidator.js"/>"></script>
<!-- Scripte qui implement les methodes pour verifier en ajax si l'email inscrit dans le formulaire d'inscription n'est pas deja utilise -->
<script src="<c:url value="/inc/js/ajax.js"/>"></script>