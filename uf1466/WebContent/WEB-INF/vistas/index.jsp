<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />
<h3>Listado de Clientes</h3>

<div class="table-responsive">
	<table class="table table-striped table-bordered table-hover">
		<caption>Listado de clientes</caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">Codigo</th>
				<th scope="col">Nombre</th>
				<th scope="col">Email</th>
				<th scope="col">Teléfono</th>
				<th scope="col">Dirección</th>
				<th scope="col">Población</th>
				<th scope="col">Código Postal</th>
				<th scope="col">Identificador</th>
				<th scope="col">Activo</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${clientes}" var="cliente">
				<tr>
					<th scope="row">${cliente.codigo}</th>
					<td>${cliente.nombre}</td>
					<td>${cliente.email}</td>
					<td>${cliente.telefono}</td>
					<td>${cliente.direccion}</td>
					<td>${cliente.poblacion}</td>
					<td>${cliente.codigopostal}</td>
					<td>${cliente.identificador}</td>
					<td>${ (cliente.activo) ? '1' : '0' }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form action="clientes" method="post">
		<button type="submit" class="btn btn-primary">Exportar a CSV</button>
	</form>
	

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>