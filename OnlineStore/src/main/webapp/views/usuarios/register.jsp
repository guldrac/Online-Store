<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importar el HEADER: -->
<c:import url="/views/layout/header.jsp"/>

<!-- Cuerpo de la vista: -->

    <div class="container small-box">
        <div class="shadow-lg box-border bg-body rounded">
        <img src="/TPFinal_Grupo_7/image/carpinchologo.png">
            <form action='<c:url value="/ingreso/register" />' method="POST">
                <div class="form-group">
                	<i class="fa-solid fa-user"></i>
                    <label for="nombre">Nombre de usuario.</label><br />
                    <input type="text" id="nombre" name="nombre" placeholder="Ingrese su nombre" class="form-control" required pattern="[A-Za-z0-9]+">
                </div>
                <br />
                <div class="form-group">
                	<i class="fa-solid fa-key"></i>
                    <label for="contrasenia">Clave de usuario.</label><br />
                    <input type="password" id="contrasenia" name="contrasenia" placeholder="Ingrese su clave" class="form-control" required pattern="[A-Za-z0-9]+">
                </div>
                <br />
                <hr size="2px" color="black">
                <div class="d-grid gap-2">
                <input value="Registrarse" type="submit" class="btn btn-menu"/>
                <a href='<c:url value="/ingreso" />' class="btn btn-volver"><i class="fa-solid fa-left-long"></i></a>
                </div>
            </form>
        </div>

    </div>
    
<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp"/>