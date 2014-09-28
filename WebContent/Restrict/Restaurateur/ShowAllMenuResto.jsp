<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	


  <!-- Affiche tous les menus par restaurant-->
  
			<form name="showAllMenuForm" class="form-horizontal" role="form" method="post"
				action="<c:url value="/Subscribe"/>">
				
				<div class="modal-header">
					<h3>Tous les menus du restaurant</h3>
				</div>
				
				<div class="modal-body">
							<div class="form-group">
								<label for="nameMenu" class="col-lg-4 control-label">Nom du menu*:</label>
								<div class="col-lg-8">
									<input type="text" class="form-control" name="Nom du menu" id="nameMenu"
										placeholder="nameMenu" required />
								</div>
							</div>
								
							
							<!-- Liste des restaurants -->
								
							<div class="bs-example">
														
							<div class="table-responsive" id="tabMenu">
							      <table class="table table-bordered">
							        <thead>
							          <tr>
							            <th>Nom du menu</th>				           
							          </tr>
							        </thead>
							        <tbody>
							          <tr>
							         	<!-- Ligne UNEE ICI qui serq affecter a une fonction Javascript-->
							            <td>menu 1 test </td>
													        
							          </tr>
							     							      
							        </tbody>
							      </table>
							    </div>
							    </div>
							    <!--  Affichage des menus -->
						</div>
		
					
						<div class="modal-footer">
		
						<button type="button" class="btn btn-danger">Retourner a la liste des restaurants</button>		
						</div>
						</form>
		  		 
  		 
  		 
<script src="<c:url value="/inc/js/AddMenu.js"/>"></script>
<jsp:include page="/footer.jsp"></jsp:include>