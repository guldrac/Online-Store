<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

<div class="container small-box">

	<form action="articulos" method="post" class="centrar-texto">
		<div class="shadow-lg box-border bg-body rounded">
			<input value="insert" name="accion" hidden="true">

				Descripción <br><input name="descripcion" required pattern="[A-Za-z0-9\-&]+"> <br>
				<br> Precio<br> <input type="number" min="0" step="0.01" name="precio" required> <br>
				<br> Stock <br><input type="number" min="0" name="cantidad"	required>
<br>
<br>
			<div class="d-grid gap-5">
				<input type="submit" value="Crear" class="btn btn-success btn-confirmar"> 
				<a class="btn btn-volver" href='<c:url value="/articulos/abmArticulos" />'>
				<i class="fa-solid fa-left-long"></i></a>
			</div>
		</div>

	</form>
</div>
	
    
<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp"/>