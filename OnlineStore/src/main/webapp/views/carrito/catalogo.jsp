<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

        <div class="container long-box">
            <div class="shadow-lg box-border bg-body rounded">
	            <table class="table table-dark table-striped">
	            	<thead>
		            	<th>ID</th>
		            	<th>Descripción</th>
		            	<th>Precio</th>
		            	<th>Cantidad</th>
		            	<th></th>	            	
	            	</thead>
	            	<tbody>
		            	<c:forEach var="articulo" items="${articulos}">
		            	<tr>
		            		<td><c:out value="${articulo.id}"/></td>
		            		<td><c:out value="${articulo.descripcion}"/></td>
		            		<td><c:out value="${articulo.precio}"/></td>
		            		<td><c:out value="${articulo.cantidad}"/></td>
							<td><form action='<c:url value="/carrito/add" />' method="POST">
							<input type="hidden" value="${articulo.id}" name="id_articulo_agregar"></input>
							<button class="btn btn-volver" type="submit" ><i class="fa-solid fa-cart-plus"></i></button>
							</form></td>
		            	</tr>
		            	</c:forEach>
	            	</tbody>
	            </table>

            <a class="btn btn-volver" href='<c:url value="/home" />'><i
			class="fa-solid fa-left-long"></i></a>
            <a class="btn btn-menu btn-ver-carrito" href='<c:url value="/carrito/carrito" />'><i class="fa-solid fa-eye"></i> Ver Carrito</a>
            </div>
        </div>

<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp" />