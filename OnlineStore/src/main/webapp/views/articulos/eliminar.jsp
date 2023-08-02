<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

	        <div class="container small-box">
				<form action="articulos" method="post" class="centrar-texto">
					<div class="shadow-lg box-border bg-body rounded">
						<input value="delete" name="accion" hidden="true">

			            <h6>¿Está seguro que desea eliminar el siguiente artículo?</h6>

			            ID<br /><input value="${articulo.id}" name="id" readonly>
			            <br />
			            <br />
			            Descripción<br /><input value="${articulo.descripcion}" name="descripcion" readonly>
			            <br />
			            <br />
			            Precio <br /><input value="${articulo.precio}" name="precio" readonly>
			            <br />
			            <br />
			            Stock<br /><input value="${articulo.cantidad}" name="cantidad" readonly>
			            <br />
			            <br />
		            
			            <div class="d-grid gap-5">
			                <input type="submit" value="Eliminar" class="btn btn-success btn-confirmar">
			                <a class="btn btn-volver" href='<c:url value="/articulos/abmArticulos" />'><i
			class="fa-solid fa-left-long"></i></a>
			            </div> 	
	            	</div>
	            </form>
       		</div>
<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp"/>