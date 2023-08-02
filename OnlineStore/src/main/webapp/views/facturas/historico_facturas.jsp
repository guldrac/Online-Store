<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Cuerpo de la vista: -->

<!-- Importar el Navbar: -->
<c:import url="/views/layout/navbar.jsp"/>

     <div class="container">
     		<div class="shadow-lg box-border bg-body rounded">            
	            <table class="table table-dark table-striped text-center">
		            	<thead>
			            	<th>Nro. Factura</th>
			            	<th>Total</th>
			            	<th>Ver</th>            	
		            	</thead>
						
						<tbody>
						<c:forEach items="${historico_facturas}" var="factura">							
							<tr>
								<td><c:out value="# ${factura.nro_factura}" /></td>
								<td><c:out value="${factura.total}" /></td>
								<td><a class="btn btn-volver" style="width: 3rem;" href='<c:url value="/historico/detalle?id_factura_ver_detalle=${factura.id}" />'><i class="fa-solid fa-eye"></i></a></td>
							</tr>							
						</c:forEach>
						</tbody>						
	            </table>
	            <br/>
            	<a class="btn btn-volver" href='<c:url value="/home" />'><i class="fa-solid fa-left-long"></i></a>
     		</div>
     </div>
     
<!-- Importar el FOOTER: -->
<c:import url="/views/layout/footer.jsp" />