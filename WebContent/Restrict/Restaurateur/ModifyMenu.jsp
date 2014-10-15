<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="modal fade" id="ModifyMenu" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
					<c:if test="${!empty form.DescriptionOk}">
							<div class="row">
								<div class="alert alert-success" role="alert">
									<h5>${form.UpdateCompleted}</h5>
								</div>
							</div>
						</c:if>
			
						<c:if test="${!empty form.DescriptionFail}">
							<div class="row">
								<div class="alert alert-danger" role="alert">
									<h5>${form.UpdateFail}</h5>
								</div>
							</div>
						</c:if>
			
			<form name="cmpid" class="form-horizontal" role="form" method="get" action="<c:url value="/ModifyMenu?"/>" >
				<div class="modal-header">
					<h3>Modifier Menu</h3>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="updNameM" class="col-lg-4" >Nom*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="updNameM" id="updNameM" required />
						</div>
					</div>
					
			
					<div class="form-group">
						<label for="updDescM" class="col-lg-4">Description*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="updDescM" id="updDescM" >
					</div>
					<input type="hidden" class="form-control" id="updIdM" name="updIdM" required />
				</div>		

					<!-- On stocke dans ce champ cache, l'idRestaurant afin de pouvoir le recuperer depuis le servlet 
					pour faire executer la requete sql -->
					

				</div>
				<div class="modal-footer">

					<a class="btn btn-default" data-dismiss="modal">Annuler</a>
					<button class="btn btn-primary" type="submit">Modifier</button>

				</div>
			</form>
		</div>
	</div>
</div>


