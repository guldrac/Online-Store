<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

    <div class="container small-box">
        <div class="shadow-lg p-3 mb-5 bg-body rounded order-btns small-box">
        	<img src="/TPFinal_Grupo_7/image/carpinchologo.png">
            <a class="btn btn-menu" href='<c:url value="/carrito/catalogo" />'><i class="fa-solid fa-shop"></i> Catálogo</a>
            <a class="btn btn-menu" href='<c:url value="/articulos/abmArticulos" />'><i class="fa-solid fa-pencil"></i> ABM artículos</a>
            <a class="btn btn-menu" href='<c:url value="/ModBanco/mostrarCredito" />'><i class="fa-solid fa-landmark"></i> Módulo bancario</a>
            <a class="btn btn-menu" href='<c:url value="/historico/historico" />'><i class="fa-solid fa-book"></i> Histórico</a>
            <a class="btn btn-salir" href='<c:url value="/ingreso/logout" />'><i class="fa-solid fa-right-from-bracket"></i> Log out</a>
        </div>
    </div>
    
<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp"/>