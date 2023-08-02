<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importar el HEADER: -->
<c:import url="/views/layout/header.jsp"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="background-color: #22272e;">
	  <div class="container-fluid">
	    <a href="#" style="color:#ced4da; text-decoration: none;"><i class="fa-solid fa-circle-user"></i> <c:out value="${usuario.nombre}" /></a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarNav" style="justify-content: space-between;">
	      <ul class="nav navbar-nav navbar-left">
	        <li class="nav-item">
	          <a class="nav-link" href='<c:url value="/carrito/catalogo" />'>| Catálogo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href='<c:url value="/ModBanco/mostrarCredito" />'>| Administrar crédito</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href='<c:url value="/historico/historico" />'>| Mis facturas</a>	      
	        </li>
	        <li class="nav-item">
	          <a class="nav-link">| Crédito: $<c:out value="${usuario.credito}" /><input value="${usuario.credito }" name="credito" readonly hidden="true"/></a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href='<c:url value="/carrito/carrito" />'>| <i class="fa-solid fa-cart-shopping"></i></a>	      
	        </li>
	       </ul>	       
	       <ul class="navbar-nav" style="float:right;">	        
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href='<c:url value="/ingreso/logout" />'><i class="fa-solid fa-right-from-bracket"></i> Log out</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>