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
  
 
 
 
 
	
<jsp:include page="/footer.jsp"></jsp:include>