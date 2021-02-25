<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h2>Listado de usuarios</h2>

<div class="table-responsive">
	<table class="table table-striped table-bordered table-hover table-sm">
		<caption>Listado de usuarios</caption>
		<thead>
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Usuario</th>
				<th scope="col">Rol</th>
				<th scope="col">Descripción del rol</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="u">
				<tr>
					<th scope="row">${u.id}</th>
					<td>${u.email}</td>
					<td>${u.rol.nombre}</td>
					<td>${u.rol.descripcion}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>

<script src="js/clienterest.js"></script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>