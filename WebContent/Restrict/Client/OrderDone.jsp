<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
<div class="row">
 	<div class="col-md-12 col-xs-12"> 		 
			 <h1 class="text-center" >Commande faite !</h1>  	
			 <br>
			 <h4 class="col-md-offset-2 col-xs-offset-1">Un email vient de vous etre envoye a votre adresse: ${sessionScope.userSession.email}</h4>
			 <br>
			 <h4 class="col-md-offset-2 col-xs-offset-1">Voici votre numero de confirmation de commande: <c:out value="${order.confirmationCode}"/></h4>
			 <br>
			 <br>
			 <h3 class="col-md-offset-2 col-xs-offset-1">Bon appétit !</h3>
	</div>

  		 
  		 
  		 
  		 
	
<jsp:include page="/footer.jsp"></jsp:include>