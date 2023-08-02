<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

<div class="container small-box">
	<div class="box-border shadow-lg bg-body rounded">

		<form action="ModBanco" method="post">

				<input value="transferir" name="accion" hidden="true">
				<h4>
				<c:out value="Disponible: $ ${usuario.credito}" /><input value="${usuario.credito }" name="credito" readonly hidden="true"/>
				</h4>
				<br>
				Ingrese el nombre del usuario al que desea
				transferir: <input placeholder="Ej: pepe" name="nombreUsuario"
					required pattern="[A-Za-z0-9]+"> <br /> <br /> Ingrese la
				Cantidad: <br>$<input type="number" min="1" step="0.01"
					placeholder="0.0" name="cantidadTransf" required> <br /> <br />

				<div class="d-grid gap-5">
					<input type="submit" value="Confirmar" class="btn btn-success btn-confirmar">
					<a class="btn btn-volver" href='<c:url value="/home" />'><i
						class="fa-solid fa-left-long"></i></a>
				</div>
	</form>
</div>
</div>

<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp"/>