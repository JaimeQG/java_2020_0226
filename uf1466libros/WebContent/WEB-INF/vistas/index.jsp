<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />
<h3>Listado de Libros</h3>
<br />

<form action="buscar" method="post" class="form-inline my-2 my-lg-0">
	<select class="form-control mr-sm-2" name="criterio" id="criterio" required>
        		<option value="">Buscar por ...</option>
       			 <option value="ISBN">ISBN</option>
        		<option value="Titulo">Título</option>
	</select>
      <input class="form-control mr-sm-2" type="search" id="texto" name="texto" required 
				placeholder="Texto a buscar ...">
      <button class="btn btn-primary my-2 my-sm-0" type="submit">Buscar</button>
</form>

<br />
<div class="table-responsive">
	<table class="table table-striped table-bordered table-hover table-sm">
		<caption>Listado de libros</caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">Código</th>
				<th scope="col">Título</th>
				<th scope="col">ISBN</th>
				<th scope="col">Autor</th>
				<th scope="col">Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${libros}" var="libro">
				<tr>
					<th scope="row">${libro.id}</th>
					<td>${libro.titulo}</td>
					<td>${libro.ISBN}</td>
					<td>${libro.autor.nombre} ${libro.autor.apellidos}</td>
					<td>
						<div class="btn-group" role="group" aria-label="Opciones">
							<a class="btn btn-primary btn-sm"
									href="libro-detalle?id=${libro.id}">Ver</a> 						
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>