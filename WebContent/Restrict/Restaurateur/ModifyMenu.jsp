<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="modal fade" id="ModifyMenu" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- <form  class="form-horizontal" role="form" method=post action="<c:url value="/Subscribe"/>">    -->
			<form name="cmpid" class="form-horizontal" role="form" method="post" action="<c:url value="/ModifyMenu"/>" >
				<div class="modal-header">
					<h3>Modifier Menu</h3>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-lg-4 control-label">Nom*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="updNameMenu" id="updNameMenu" placeholder="Nom" required />
					</div>
						
					</div>
						<div class="form-group">
						<label for="description" class="col-lg-4 control-label">Description*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="updNameMenu" id="updNameMenu" placeholder="Description" required />
						</div>
					</div>
		
					

					<!-- On stocke dans ce champ cache, l'idRestaurant afin de pouvoir le recuperer depuis le servlet 
					pour faire executer la requete sql -->
					<input type="hidden" class="form-control" id="updIdM" name="updIdM" />

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
