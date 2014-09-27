<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	



  		 
  		 <p>Ajouter Menu<p> 
  		
		<!-- Formulaire d'ajout de menu -->
		<div class="modal fade" id="ajouterMenu" role="dialog">
		
			<div class="modal-dialog">
				<div class="modal-content">
		
						
						<div class="modal-header">
							<h3>Formulaire d'ajout de menu</h3>
						</div>
						<div class="modal-body">
		
							<div class="form-group">
								<label for="nameMenu" class="col-lg-4 control-label">Nom du menu*:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="nameMenu" id="nameMenu"
										placeholder="nameMenu" required />
								</div>
							</div>
		
								<div class="form-group">
								<label for="descriptionMenu" class="col-lg-4 control-label">Description du menu*:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="descriptionMenu" id="descriptionMenu"
										placeholder="descriptionMenu" required />
								</div>
								
								<!-- Tableau Ajout des plats -->
								
								<div class="bs-example">
								
							    <div class="table-responsive">
							      <table class="table">
							        <thead>
							          <tr>
							            <th>#</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							          </tr>
							        </thead>
							        <tbody>
							          <tr>
							            <td>1</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							          </tr>
							          <tr>
							            <td>2</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							          </tr>
							          <tr>
							            <td>3</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							          </tr>
							        </tbody>
							      </table>
							    </div><!-- /.table-responsive -->
							
							    <div class="table-responsive">
							      <table class="table table-bordered">
							        <thead>
							          <tr>
							            <th>#</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							            <th>Table heading</th>
							          </tr>
							        </thead>
							        <tbody>
							          <tr>
							            <td>1</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							          </tr>
							          <tr>
							            <td>2</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							          </tr>
							          <tr>
							            <td>3</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							            <td>Table cell</td>
							          </tr>
							        </tbody>
							      </table>
							    </div>
							    <!-- Fin Tableau Ajout des plats -->
							</div>
		
					
						<div class="modal-footer">
		
							<a class="btn btn-default" data-dismiss="modal">Annuler</a>
							<button type="button" class="btn btn-success">Finaliser Création</button>
		
						</div>
				</div>
			</div>
		  		 
  		 
  		 
<script src="<c:url value="/inc/js/verifyAddMenu.js"/>"></script>
<jsp:include page="/footer.jsp"></jsp:include>