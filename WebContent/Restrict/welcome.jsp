<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
  		 
  		 <p>Bonjour vous etes desormais connecte au site<p> 
  		 <p>${sessionScope.userSession.email}<p>
  		 
  		<!--  Client ( droit 0) -->
  		<c:if test="${sessionScope.userSession.userRights==0}">
 		</c:if> 
 
 		<!--  restaurateurs ( droit 1) -->
  		<c:if test="${sessionScope.userSession.userRights==1}">
 		</c:if>
 		
 		<!--  administrateurs (2) -->
 		 <c:if test="${sessionScope.userSession.userRights==2}">
 		</c:if>
  
 
 
  <script>

	  toastr.options = {
			  "closeButton": true,
			  "debug": false,
			  "positionClass": "toast-top-left",
			  "onclick": null,
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
		  toastr.success('${sessionScope.userSession.firstName} ${sessionScope.userSession.name} !', 'Bonjour ');

 </script>
	
<jsp:include page="/footer.jsp"></jsp:include>