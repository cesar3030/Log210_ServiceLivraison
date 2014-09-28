<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	


 
  <!-- Formulaire d'ajout de menu -->
  
			<form name="formulaireAddMenu" class="form-horizontal" role="form" method="post"
				action="<c:url value="/Subscribe"/>">
				<div class="modal-header">
					<h3>Ajouter un nouveau Menu</h3>
				</div>
				
				<div class="modal-body">
							<div class="form-group">
								<label for="nameMenu" class="col-lg-4 control-label">Nom du menu*:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="nameMenu" id="nameMenu"
										placeholder="Nom du menu" required />
								</div>
							</div>
		
							<div class="form-group">
								<label for="descriptionMenu" class="col-lg-4 control-label">Description du menu*:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="descriptionMenu" id="descriptionMenu"
										placeholder="Description du menu" required />
								</div>
							</div>								
							
							<!-- Tableau Ajout des plats -->
								
							<div class="bs-example">
														
							<div class="table-responsive" id="tabMenu">
							      <table class="table table-bordered">
							        <thead>
							          <tr>
							            <th>Nom</th>
							            <th>Description du plat</th>
							            <th>Prix du plat</th>
							           
							          </tr>
							        </thead>
							        <tbody>
							          <tr>
							         	<!-- Ligne UNEE ICI qui serq affecter a une fonction Javascript-->
							            <td>1</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							        
							          </tr>
							     							      
							        </tbody>
							      </table>
							    </div>
							    </div>
							    <!-- Fin Tableau Ajout de Menu -->
						</div>
		
					
						<div class="modal-footer">
		
							<a class="btn btn-default" data-dismiss="modal">Annuler</a>
							<button type="button" class="btn btn-success">Finaliser Création</button>
		
						</div>
						</form>
		  		 
  		 
  		 
<script src="<c:url value="/inc/js/AddMenu.js"/>"></script>
<jsp:include page="/footer.jsp"></jsp:include>