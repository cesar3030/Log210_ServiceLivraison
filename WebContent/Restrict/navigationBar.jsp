<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>

<% Locale localeUS = new Locale("en", "US"); %>
<% Locale localeFR = new Locale("fr", "FR"); %>
<% Locale locale = new Locale("fr", "FR"); %>

<%
ClassLoader cl = ClassLoader.getSystemClassLoader();

ResourceBundle.clearCache(cl);
ResourceBundle resourceBundle = ResourceBundle.getBundle("ca.etsmtl.log210.Traduction.Bundle", locale);

//ResourceBundle resourceBundle ;//= ResourceBundle.getBundle("ca.etsmtl.log210.Traduction.Bundle", localeUS);

	if (session.getAttribute("langue").equals("en")) {
		System.out.println("EN ANGLAIS");
		resourceBundle = ResourceBundle.getBundle(
				"ca.etsmtl.log210.Traduction.Bundle", localeUS);
	}
	if (session.getAttribute("langue").equals("fr")) {
		System.out.println("EN FRANCAIS");
		resourceBundle = ResourceBundle.getBundle(
				"ca.etsmtl.log210.Traduction.Bundle", localeFR);
	}

%>
<!--  
<nav class="navbar navbar-inverse" id="navigationBar" role="navigation">
  <ul class="nav navbar-nav">
   			<li class="active"><a href="<c:url value="/Restrict/welcome.jsp"/>">Acceuil</a></li>
            <li><a href="<c:url value="/Search"/>">Recherche</a></li>
            <li><a href="<c:url value="/MyAccount"/>">Mon Compte</a></li>
            <li><a href="<c:url value="/Logout"/>">Deconnexion</a></li>
   </ul>
</nav>
-->
<nav class="navbar navbar-inverse" id="navigationBar" role="navigation">
	<div class="container-fluid" id="navfluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navigationbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/Restrict/welcome.jsp"/>">
				<%=resourceBundle.getString("ACCUEIL")%>
			</a>
		</div>
		<div class="collapse navbar-collapse" id="navigationbar">
			<ul class="nav navbar-nav">

				<!--  Barre de navigation des clients ( droit 0) -->
				<c:if test="${sessionScope.userSession.userRights==0}">
					<li><a href="<c:url value="/Restrict/Client/Search.jsp"/>">Faire
							une commande</a></li>
					<li><a href="<c:url value="/MyAccount"/>">Mon Compte</a></li>
					<li><a href="<c:url value="/Logout"/>">Deconnexion</a></li>
				</c:if>



				<!--  Barre de navigation des restaurateurs ( droit 1) -->
				<c:if test="${sessionScope.userSession.userRights==1}">
					<li><a href="<c:url value="/MyAccount"/>">Mon Compte</a></li>
					<li><a href="<c:url value="/HomeRestaurateurManagement"/>">Mes
							Restaurants</a></li>
					<li><a href="<c:url value="/HomeOrderRestaurant"/>">Mes
							Commandes</a></li>
					<li><a href="<c:url value="/Logout"/>">Deconnexion</a></li>
				</c:if>



				<!--  Barre de navigation des administrateurs (2) -->
				<c:if test="${sessionScope.userSession.userRights==2}">
					<li><a href="<c:url value="/MyAccount"/>">Mon Compte</a></li>
					<li><a href="<c:url value="/RestaurantManagement"/>">Gerer
							Restaurants</a></li>
					<li><a href="<c:url value="/RestaurateurManagement"/>">Gerer
							Restaurateurs</a></li>
					<li><a href="<c:url value="/Logout"/>">Deconnexion</a></li>
				</c:if>

				<!--  Barre de navigation des livreurs (3) -->
				<c:if test="${sessionScope.userSession.userRights==3}">
					<li><a href="<c:url value="/MyAccount"/>"> <%=resourceBundle.getString("COMPTE")%>
					</a></li>
					<li><a href="<c:url value="/OrdersNeededToBeDelivered"/>">
							<%=resourceBundle.getString("COMMANDE_LIVRAISON")%>
					</a></li>
					<li><a href="<c:url value="/MyCurrentDeliveryOrders"/>">
							<%=resourceBundle.getString("LIVRAISONS")%>
					</a></li>
					<li><a href="<c:url value="/Logout"/>"> <%=resourceBundle.getString("DECONNEXION")%>
					</a></li>
				</c:if>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
</nav>
