<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

     <div class="container small-box">
     		<div class="box-border shadow-lg bg-body rounded">
     			<div class="d-grid gap-5">
	     			<h3 style="align-text: center;">¡El pago fue recibido con éxito!</h3>
	     			<a class="btn btn-volver" href='<c:url value="/carrito/catalogo" />'><i
			class="fa-solid fa-left-long"></i></a>
     			</div>     			
     		</div>
     </div>
    
<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp"/>