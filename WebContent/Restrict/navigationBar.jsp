<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<nav class="navbar navbar-inverse" id="navigationBar" role="navigation">
  <ul class="nav navbar-nav">
   			<li class="active"><a href="<c:url value="/Restrict/welcome.jsp"/>">Acceuil</a></li>
            <li><a href="<c:url value="/Search"/>">Recherche</a></li>
            <li><a href="<c:url value="/MyAccount"/>">Mon Compte</a></li>
            <li><a href="<c:url value="/Logout"/>">Deconnexion</a></li>
   </ul>
</nav>