<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h3>Mantenimiento de Platos</h3>

<div class="table-responsive mb-3">
	<table class="table table-striped table-bordered table-hover table-sm">
		<caption>Listado de platos</caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th scope="col">Tiempo</th>
				<th scope="col">Calorías</th>
				<th scope="col">Categoría</th>
				<th scope="col">Origen</th>
				<th scope="col">Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${platos}" var="plato">
				<tr>
					<th scope="row">${plato.id}</th>
					<td>${plato.nombre}</td>
					<td class="text-right"><fmt:formatNumber type ="number" value ="${plato.tiempo}" /> 
					min.</td>
					<td class="text-right"><fmt:formatNumber type ="number" value ="${plato.calorias}" /> cal.</td>
					<td>${plato.categoria.nombre}</td>
					<td>${plato.origen.nombre}</td>	
					<td>
						<div class="btn-group" role="group" aria-label="Opciones">
							<a class="btn btn-primary btn-sm"
							href="add-plato?id=${plato.id}">Editar</a> <a
							onclick="return confirm('¿Estás seguro?')"
							class="btn btn-danger btn-sm"
							href="delete-plato?id=${plato.id}">Borrar</a>					
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<a class="btn btn-primary" href="add-plato">Añadir plato</a>
	
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>