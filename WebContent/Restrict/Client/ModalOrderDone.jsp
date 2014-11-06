<!-- Modal affichant le message de confirmation de la commande -->
<div class="modal fade" id="modalOrderDone" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header"> 		 
				<h1 class="text-center" >Commande faite !</h1>
			</div>
			<div class="modal-body">
					<h4 class="col-md-offset-2 col-xs-offset-1">Un email vient de vous etre envoye a votre adresse: ${sessionScope.userSession.email}</h4>
					 <br>
					 <h4 class="col-md-offset-2 col-xs-offset-1">Voici votre numero de confirmation de commande: <c:out value="${order.confirmationCode}"/></h4>
					 <br>
					 <h4 class="col-md-offset-2 col-xs-offset-1">La livraison se fera a l'address: <c:out value="${address.address}"/></h4>
					 <br>
					 <br>
					 <h3 class="col-md-offset-2 col-xs-offset-1">Bon appétit !</h3>
			</div> 
			<div class="modal-footer">
				<button class="btn btn-primary" type="submit">Accueil</button>
			</div> 
		</div>
	</div>
</div>