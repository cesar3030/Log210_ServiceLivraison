<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


	<!-- Mobile -->
 			<meta name="viewport" content="width=device-width, initial-scale=1">
     		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- Fin -->


	<!--  <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"> -->
	<meta charset="ISO-8859-1">
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/inc/css/style_website.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/inc/css/bootstrap/css/bootstrap.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/inc/js/toastr/toastr.css"/>"/>
	<link href='http://fonts.googleapis.com/css?family=Anton' rel='stylesheet' type='text/css'/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/inc/css/bootstrap-select.min.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/inc/css/DragNDrop/dragNDrop.css"/>" />

	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="<c:url value="/inc/css/bootstrap/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/inc/js/bootstrap-select.js"/>"></script>
	<script src="<c:url value="/inc/js/toastr/toastr.js"/>"></script>
	<script src="<c:url value="/inc/css/bootstrap/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/inc/css/bootstrap/js/moment.js"/>"></script>
	
	
	
	<title>Express Livraison</title>
</head>

<body>

<div class="container-fluid wrap">

	<div class='row' id="orangeHeader">			    	
 		<div class="col-md-12" id="orangeHeader"> 	
 			<h1>Express Livraison</h1>
 	 	 </div>
 	
 	 
	 	 <div class="col-md-8 col-md-offset-2" >
		 	 <c:if test="${!empty sessionScope.userSession}">
		 	 	<jsp:include page="/Restrict/navigationBar.jsp"></jsp:include>	
		 	 </c:if> 
	 	 </div>
	 </div>
 	  

		  

 



