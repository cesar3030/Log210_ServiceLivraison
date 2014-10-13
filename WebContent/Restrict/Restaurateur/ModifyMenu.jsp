<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>



<!-- Formulaire d'ajout de menu -->
<div class="modal fade" id="modifyMenu" role="dialog" >
	<div class="modal-dialog">
		<div class="modal-content">


			<form name=cmpid class="form-vertical" role="form" method="post"
				action="<c:url value="/ModifyMenu"/>">
				<div class="modal-header">
					<h3>Modifier menu  </h3>
				</div>

						<div class="form-group">
							<label for="nameMenu" class="col-lg-4 control-label">Nom du Menu:*</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" name="nameMenuF" id="nameMenuF"
									placeholder="Nom du Menu"  required />
							</div>
						</div>

						<div class="form-group">
							<label for="descriptionMenu" class="col-lg-4 control-label">Description de Menu:*</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="descriptionMenuF"
									name="descriptionMenuF" placeholder="Unique en son genre" />
							</div>
						</div>
					
					<a class="btn btn-default" data-dismiss="modal">Annuler</a>
					<button class="btn btn-primary" type="submit">Modifier</button>
			</form>
			
				</div>
			
			</div>
	
	</div>

<script src="<c:url value="/inc/js/modifyMenu.js"/>"></script>