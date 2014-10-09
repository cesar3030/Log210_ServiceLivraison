<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="modal fade" id="UpdateRestaurant" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- <form  class="form-horizontal" role="form" method=post action="<c:url value="/Subscribe"/>">    -->
			<form name="cmpid" class="form-horizontal" role="form" method="post" action="<c:url value="/UpdateRestaurant"/>" >
				<div class="modal-header">
					<h3>Modification d'un restaurant</h3>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-lg-4 control-label">Nom*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="updateName" id="updateName" placeholder="Nom" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restaurateurId" class="col-lg-4 control-label">Restaurateur*:</label>
					 	<!-- <select class="selectpicker" id="updateRestaurateurId" name="restaurateurId" required>
							  <option>1</option>				     						
						</select> -->
						<div class="col-lg-8">
							<select class="form-control"   id="updateRestaurateurId" name="updateRestaurateurId"  required>
							 
							</select>
						</div>
					</div>
			
					<div class="form-group">
						<label for="phone" class="col-lg-4 control-label">Telephone*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="updatePhone" name="updatePhone"
								placeholder="5141245678"  onblur="verifierNumeroTel();" required />
						</div>
					</div>		


					<div class="form-group">
						<label for="address" class="col-lg-4 control-label">Adresse*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="updateAddress" name="updateAddress"
								placeholder="4589 Bd Saint-Denis H3P1O2 Montreal" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="typeFood" class="col-lg-4 control-label">Type nourriture*:</label>
						<div class="col-lg-8">
							<select class="form-control" id="updateTypeFood" name="updateTypeFood" required>
							  <option  value="Francais">Francais</option>
						      <option  value="Italien">Italien</option>
						      <option  value="Snack">Snack</option>
							  <option  value="Libanais">Libanais</option>
						      <option  value="Québecois">Québecois</option>
						      <option  value="Chinois">Chinois</option>						
							</select>
						</div>
					</div>

					<!-- On stocke dans ce champ cache, l'idRestaurant afin de pouvoir le recuperer depuis le servlet 
					pour faire executer la requete sql -->
					<input type="hidden" class="form-control" id="updateIdRestaurant" name="updateIdRestaurant" />

				</div>
				<div class="modal-footer">

					<a class="btn btn-default" data-dismiss="modal">Annuler</a>
					<button class="btn btn-primary" type="submit">Modifier</button>

				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#UpdateRestaurant').on('shown.bs.modal', function () {
	Requete_AJAX_GetActiveRestaurateurList();
	})
</script>
<script src="<c:url value="/inc/js/SignInFormValidator.js"/>"></script>
