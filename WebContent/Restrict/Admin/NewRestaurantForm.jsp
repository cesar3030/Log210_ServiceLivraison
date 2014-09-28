<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="modal fade" id="newRestaurant" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- <form  class="form-horizontal" role="form" method=post action="<c:url value="/Subscribe"/>">    -->
			<form name="cmpid" class="form-horizontal" role="form" method="post" action="<c:url value="/NewRestaurant"/>" >
				<div class="modal-header">
					<h3>Nouveau restaurant</h3>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-lg-4 control-label">Nom*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="name" id="name" placeholder="Nom" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restaurateurId" class="col-lg-4 control-label">Restaurateur*:</label>
						<select class="selectpicker" id="restaurateurId" name="restaurateurId" required>
							  <option>1</option>				     						
						</select>
					</div>
			
					<div class="form-group">
						<label for="phone" class="col-lg-4 control-label">Telephone*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="phone" name="phone"
								placeholder="5141245678"  onblur="verifierNumeroTel();" required />
						</div>
					</div>		


					<div class="form-group">
						<label for="address" class="col-lg-4 control-label">Adresse*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="address" name="address"
								placeholder="4589 Bd Saint-Denis H3P1O2 Montreal" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="typeFood" class="col-lg-4 control-label">Type nourriture*:</label>
						<select class="selectpicker" id="typeFood" name="typeFood" required>
							  <option>Francais</option>
						      <option>Italien</option>
						      <option>Snack</option>
							  <option>Libanais</option>
						      <option>Québecois</option>
						      <option>Chinois</option>						
						</select>
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

<script src="<c:url value="/inc/js/SignInFormValidator.js"/>"></script>
