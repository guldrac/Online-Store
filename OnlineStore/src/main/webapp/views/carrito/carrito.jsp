<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

     <div class="container long-box">
     		<div class="shadow-lg box-border bg-body rounded">
            
					<table class="table table-dark text-center">
		            	<thead>
			            	<th>Descripción</th>
			            	<th>X</th>
			            	<th>Cantidad</th>
			            	<th>=</th>
			            	<th>Precio</th>
			            	<th>Borrar</th>            	
		            	</thead>
						
						<tbody>
						<c:forEach items="${carrito_nuevo.filas }" var="fila">
							
							<tr>
								<td><c:out value="${fila.descripcion}" /></td>
								<td>x</td>
								<td><c:out value="${ fila.cantidad}" /></td>
								<td>=</td>
								<td><c:out value="${fila.precio}" /></td>
								<td><form action='<c:url value="/carrito/remove" />' method="POST">
								<input type="hidden" value="${fila.descripcion}" name="desc_articulo_quitar"></input>
								<button class="btn-close btn-volver" style="width: 2rem;" type="submit" ></button>
								</form></td>
							
							</tr>
							
						</c:forEach>
						</tbody>
						
	            </table>
			         	<strong>TOTAL: </strong>$<c:out value="${carrito_nuevo.monto_total} "/>
						<br>
						<br>
						<a class="btn btn-menu" href='<c:url value="/carrito/catalogo" />'>Seguir comprando</a>
						<a class="btn btn-menu" href='<c:url value="/carrito/pagar" />'>Pagar</a>
						
						<br />
						
     		</div>
     </div>

<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp" />