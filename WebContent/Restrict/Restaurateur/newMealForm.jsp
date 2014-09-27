<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="/header.jsp"></jsp:include>



<a href="#newMeal" data-toggle="modal">S'inscrire ?</a>

<!-- Formulaire de creation d un nouveau plat -->
<div class="modal fade" id="newMeal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">


			<form name=cmpid class="form-horizontal" role="form" method="post"
				action="<c:url value="/Subscribe"/>">
				<div class="modal-header">
					<h3>Nouveau plat</h3>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-lg-4 control-label">Nom*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="name" id="name"
								placeholder="Poulet frites" required />
						</div>
					</div>

					<div class="form-group">
						<label for="description" class="col-lg-4 control-label">Description:*</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="description"
								name="description" placeholder="Plat typique de Lyon" required />
						</div>
					</div>

					<div id="email_part" class="form-group">
						<label for="price" class="col-lg-4 control-label">Prix*:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="price"
								id="email_subscribe" placeholder="15" required />
							<div id="error-message"></div>
						</div>
					</div>

					<a class="btn btn-default" data-dismiss="modal">Annuler</a>
					<button class="btn btn-primary" type="submit">Créer</button>

				</div>
			</form>
		</div>
	</div>
</div>





<jsp:include page="/footer.jsp"></jsp:include>