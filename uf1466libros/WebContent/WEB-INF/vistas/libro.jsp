<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />
<h1>Detalle de libro</h1>

<!-- ${libro} -->

<form action="" method="post" class="needs-validation" novalidate enctype="multipart/form-data">

	<!-- Código -->
	<div class="form-group row">
		<label for="id" class="col-md-4 col-lg-3 col-form-label">Código</label>
		<div class="col">
			<input type="number" class="form-control" id="id" name="id" value="${libro.id}"
				disabled>
		</div>
	</div>
	
	<!-- Nombre -->
	<div class="form-group row">
		<label for="nombre" class="col-md-4 col-lg-3  col-form-label">Nombre</label>
		<div class="col">
			<input type="text" class="form-control" id="nombre" name="nombre" value="${libro.titulo}" disabled>
		</div>
	</div>
	
	<!-- ISBN -->
	<div class="form-group row">
		<label for="ISBN" class="col-md-4 col-lg-3 col-form-label">ISBN</label>
		<div class="col">
			<input type="text" class="form-control" id="ISBN" name="ISBN" value="${libro.ISBN}" disabled>
		</div>
	</div>
	
	<!-- Autor -->
	<div class="form-group row">
		<label for="autor" class="col-md-4 col-lg-3 col-form-label">Autor</label>
		<div class="col">
			<input type="text" class="form-control" id="autor" name="autor" value="${libro.autor.nombre} ${libro.autor.apellidos}" disabled>
		</div>
	</div>
	
	<div class="form-group row">
		<div class="offset-md-4 offset-lg-3 col">
			<button type="submit" class="btn btn-primary" onclick="window.close()">Aceptar</button>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>