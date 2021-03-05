<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />
<h2>Búsqueda</h2>
<br />

<form action="buscar" method="post" class="needs-validation">
	
	<!-- buscarPor -->
	<div class="form-group row">
		<label for="id" class="col-md-4 col-lg-3 col-form-label">Buscar por</label>
		<div class="col">
			<select class="form-control" name="criterio" id="criterio" required>
        		<option value="">Buscar por ...</option>
       			 <option value="ISBN">ISBN</option>
        		<option value="Titulo">Título</option>
      		</select>
		</div>
	</div>
	
	<!-- textoBuscar -->
	<div class="form-group row">
		<label for="nombre" class="col-md-4 col-lg-3  col-form-label">Texto a buscar</label>
		<div class="col">
			<input type="text" class="form-control" id="texto" name="texto" 
				required 
				placeholder="Texto a buscar ...">
		</div>
	</div>
	
	<div class="form-group row">
		<div class="offset-md-4 offset-lg-3 col">
			<button type="submit" class="btn btn-primary">Aceptar</button>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>