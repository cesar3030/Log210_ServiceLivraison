<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
<div class="row">
	  	<div class="col-md-12 col-xs-12"> 		 
	 			 <h1 class="text-center" >Résumé de votre commande</h1>  	
	 			 <br>
	 			 <br>
	 	</div>     
</div> 
<div class="row">
	<fieldset class="col-md-8">
	<div class="panel panel-primary">
      <div class="panel-heading">
		<div class="table-responsive">
			<table class="table table-striped">
			 	<thead>
			 		<tr>
			 			<th>Nom du Plat</th>
			 			<th>Description</th>
			 			<th>Prix</th>
			 			<th>Quantité</th>
			 		</tr>
			 	</thead>
			 	</table>
		</div>
		</div>
		<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-striped">
			 	<tbody>
			 		<c:forEach items="${order.orderItemsList}" var="item">
					<tr>
			 		 	<td><c:out value="${item.meal.name}"/></td>
			 		 	<td><c:out value="${item.meal.description}"/></td>
			 		 	<td><c:out value="${item.meal.price}"/>$</td>
			 		 	<td><c:out value="${item.quantity}"/></td>
					 </tr>			  		 	
				   </c:forEach> 
			 		</tbody>	
			 		<tfoot>
			 		<tr>
			 			<th colspan="3">Total</th>
			 			<th><c:out value="${order.totalPrice}"/>$</th>
			 		</tr>
			 		</tfoot>	   
			</table>
		</div>
		</div>
		</div>
	</fieldset>  
	<fieldset class="col-md-4">
	<div class="panel panel-primary">
      <div class="panel-heading">
        <h3 class="panel-title">Finaliser votre commande</h3>
      </div>
      <div class="panel-body">
	  <form name=cmpid class="form-horizontal" role="form" id="ProceedOrder" method="post" action="<c:url value="/ProceedOrder"/>"> 
		       <div class="form-group">
		       		<label for="dtp_input1" class="col-md-4 control-label">Date et heure de livraison: </label>
		            <div id="id" class="input-group date form_datetime col-md-7" data-date="1979-09-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
		                    <input name="dateAndHour"class="form-control" size="16" type="text" value="" required >
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		            </div>
					<input type="hidden" id="dtp_input1" value="" /><br/>
		       </div>	
		      <div class="form-group">
		      <label for="dtp_input1" class="col-md-4 control-label">Choisir l'adresse de livraison: </label>
			      <div class="col-md-7 ">
					<select class="form-control"   id="addressList" name="addressList"  required>
						<option value="0" >${sessionScope.userSession.homeAddress}</option><!--  On affiche l'adresse par defaut du compte-->
						<c:forEach items="${addressUser}" var="out">
						<option value="<c:out value="${out.idAddress}"/>" ><c:out value="${out.address}"/></option>
						</c:forEach>
						<option value="-1" >Nouvelle adresse</option><!--  On affiche l'adresse par defaut du compte-->
					</select>
					<input id="newAddress"  name="newAddress" class="form-control" type="text" value="" required>
				 </div>
			  </div>
			 <div class='col-md-10'>
				 <button class="btn btn-primary"  id="confirm" type="submit">Confirmer la commande</button> 
			 </div>
		</form>
		</div>
    </div>
	</fieldset>
</div>
<jsp:include page="/Restrict/Client/ModalOrderDone.jsp"></jsp:include>	
<script type="text/javascript">
$( "select" )
.change(function () {
  var str;
  $( "select option:selected" ).each(function() {
    str= $( this ).val();
    if(str==-1)
	{
    		$("#newAddress").show();
    		$("#newAddress").prop('required',true);
	}
    else
    	{
    		$("#newAddress").hide();
    		$("#newAddress").prop('required',false);
    	}
  });
 
})
.change();

$('.form_datetime').datetimepicker({
    language:  'fr',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	forceParse: 0,
    showMeridian: 0
});

$('#id').datetimepicker('setStartDate', new Date());

$('#ProceedOrder').submit(function() 
{
	$('#confirm').hide();
});



</script>	 
<script src="<c:url value="/inc/js/jquery-1.8.3.min.js"/>"></script>
<script src="<c:url value="/inc/css/bootstrap/js/fr.js"/>"></script> 		 
	
<jsp:include page="/footer.jsp"></jsp:include>