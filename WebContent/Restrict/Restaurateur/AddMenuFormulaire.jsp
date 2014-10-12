<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>



<!-- Formulaire d'ajout de menu -->
<div class="modal fade" id="addMenu" role="dialog" >
	<div class="modal-dialog">
		<div class="modal-content">


			<form name=cmpid class="form-vertical" role="form" method="post"
				action="<c:url value="/AddMenuFormulaire"/>">
				<div class="modal-header">
					<h3>Ajouter un menu au restaurant  </h3>
				</div>

						<div class="form-group">
							<label for="nameMenu" class="col-lg-4 control-label">Nom du Menu:*</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" name="nameMenu" id="nameMenu"
									placeholder="Nom du Menu"  required />
							</div>
						</div>

						<div class="form-group">
							<label for="descriptionMenu" class="col-lg-4 control-label">Description de Menu:*</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="descriptionMenu"
									name="descriptionMenu" placeholder="Unique en son genre" />
							</div>
						</div>
					
					<a class="btn btn-default" data-dismiss="modal">Annuler</a>
					<button class="btn btn-primary" type="submit">Ajouter</button>
			</form>
			
				</div>
			
			</div>
	
	</div>

<script src="<c:url value="/inc/js/addMenu.js"/>"></script>