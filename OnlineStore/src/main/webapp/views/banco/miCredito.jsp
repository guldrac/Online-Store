<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

     <div class="container small-box">
     		<div class="box-border shadow-lg bg-body rounded">
     		<a class="btn btn-menu btn-crear-art" href='<c:url value="/ModBanco/transferencias" />'><i class="fa-solid fa-money-check-dollar"></i> Transferencias</a>
     			<form action="ModBanco"  method="POST">

			            	<input value="${usuario.id }" name="id" type="hidden" readonly>
			            	<br />
			            	<h5>Crédito disponible: $<c:out value="${usuario.credito }" /></h5><input value="${usuario.credito }" name="credito" readonly hidden="true"/>
			            	
			            	<br />
			            	<!--Agregar Cantidad <br /><input  value="${usuario.credito }" name="agregarCantidad">-->
			            	Cantidad con la que desea operar <br /><input type="number" min="1" step="0.01" placeholder="$5000.0" name="agregarCantidad" required>
			            	<br />		            	
			            	<br />
			            
				            <div class="d-grid gap-2">
				            	<input type="submit" value="sumar" class="btn btn-menu btns-banco" name="accion"> 
				                <input type="submit" value="remover" class="btn btn-menu btns-banco" name="accion"> 		                
				              	<br>
				                <a class="btn btn-volver" href='<c:url value="/home" />'><i class="fa-solid fa-left-long"></i></a>
				            </div> 	
				</form> 
     		</div>
     </div>
    
<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp"/>