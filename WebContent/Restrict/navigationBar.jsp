<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

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
       <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigationbar">
       <span class="sr-only">Toggle navigation</span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
      </button>
     <a class="navbar-brand" href="<c:url value="/Restrict/welcome.jsp"/>">Accueil</a>
    </div>
<div class="collapse navbar-collapse" id="navigationbar">
<ul class="nav navbar-nav">
   <li><a href="<c:url value="/Search"/>">Recherche</a></li>
    <li><a href="<c:url value="/MyAccount"/>">Mon Compte</a></li>    
    <li><a href="<c:url value="/Logout"/>">Deconnexion</a></li>
</ul>
</div><!-- /.navbar-collapse -->
</div>
</nav>
