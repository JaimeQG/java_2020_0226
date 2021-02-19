<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h2>Listado de Productos</h2>
<div class="table-responsive">
	<table class="table table-striped table-bordered table-hover table-sm">
		<caption>Listado de Productos UF1465 - Ejercicio 2</caption>
		<thead>
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th scope="col">Precio</th>
				<th scope="col">Categoria</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productos}" var="p">
				<tr>
					<th scope="row">${p.id}</th>
					<td>${p.nombre}</td>
					<td><fmt:formatNumber type="currency" value="${p.precio}" /></td>
					<td>${p.categoria.nombre}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>