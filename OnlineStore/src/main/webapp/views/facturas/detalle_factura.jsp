<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

<br />
<br />
<div class="container">
	<div class=" shadow-lg box-border bg-body rounded">
		<table class="table table-dark table-striped text-center">
			<thead>
				<th>Descripción</th>
				<th>Precio unitario</th>
				<th>X</th>
				<th>Cantidad</th>
				<th>=</th>
				<th>Total</th>
			</thead>

			<tbody>
				<c:forEach items="${detalle_factura}" var="fila">

					<tr>
						<td><c:out value="${fila.descripcion}" /></td>
						<td><c:out value="${fila.precio_unitario}" /></td>
						<td>x</td>
						<td><c:out value="${ fila.cantidad}" /></td>
						<td>=</td>
						<td><c:out value="$${ fila.precio_total}" /></td>

					</tr>
					
				</c:forEach>
			</tbody>

		</table>
		<p><strong>TOTAL: </strong>$<c:out value="${total}" /></p>
		

		<br /> 
		<a class="btn btn-danger btn-volver" href='<c:url value="/historico/historico" />'><i class="fa-solid fa-left-long"></i></a>
	</div>
</div>

<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp" />