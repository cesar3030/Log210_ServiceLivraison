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
	<div class="col-md-6 col-md-offset-3">
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
<div class="row">
	<div class='col-md-5'>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker9'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div> 
</div>
<script type="text/javascript">
            $(function () {
                $('#datetimepicker9').datetimepicker({
                    language: 'ru'
                });
            });
</script>	 
<script type="text/javascript" src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/src/js/bootstrap-datetimepicker.js"></script>
  		 
  		 
	
<jsp:include page="/footer.jsp"></jsp:include>