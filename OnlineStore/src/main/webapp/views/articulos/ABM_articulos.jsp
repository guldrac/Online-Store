<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

        <div class="container long-box">
            <div class="shadow-lg box-border bg-body rounded">
            
	            <div class="d-grid gap-2">
	                <a class="btn btn-menu btn-crear-art" href='<c:url value="/articulos/crear" />'>+ Agregar artículo</a>
	            </div>
	       
	            <table class="table table-dark table-striped"> <!-- #22272e -->
	            	<thead>
		            	<th>ID</th>
		            	<th>Descripción</th>
		            	<th>Precio</th>
		            	<th>Stock</th>
		            	<th></th><th></th>	            	
	            	</thead>
	            	<tbody>
		            	<c:forEach var="articulo" items="${articulos}">
		            	<tr>
		            		<td><c:out value="${articulo.id}"/></td>
		            		<td><c:out value="${articulo.descripcion}"/></td>
		            		<td><c:out value="${articulo.precio}"/></td>
		            		<td><c:out value="${articulo.cantidad}"/></td>
		            		<td>
		            		<a class="btn btn-volver" style="width: 3rem;" href='<c:url value="/articulos/editar?id=${articulo.id }" />'>
								<i class="fa-solid fa-pencil"></i>
							</a></td>
							<td>
							<a class="btn btn-volver" style="width: 3rem;" href='<c:url value="/articulos/eliminar?id=${articulo.id }" />'>
								<i class="fa-solid fa-trash"></i>
							</a></td>
		            	</tr>
		            	</c:forEach>
	            	</tbody>
	            </table>

                <div class="d-grid gap-5">
                <a class="btn btn-volver" href='<c:url value="/home" />'><i
			class="fa-solid fa-left-long"></i></a>  
                </div>  
            </div>
        </div>
    
<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp"/>