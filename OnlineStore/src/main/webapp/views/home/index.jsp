<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

    <div class="container order-btns small-box">
        <div class="shadow-lg box-border bg-body rounded">        
            <a class="btn btn-menu" href="articulos?accion=abmArticulos"><i class="fa-solid fa-pencil"></i> ABM articulos</a><br />
            <a class="btn btn-menu" href="articulos?accion=catalogo"><i class="fa-solid fa-shop"></i> Catalogo</a>
        </div>
    </div>

<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp" />