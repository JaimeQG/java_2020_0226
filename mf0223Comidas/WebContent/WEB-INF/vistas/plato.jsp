<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />
<h2>${accion}plato</h2>
<br />
<!-- ${plato} -->

<form action="add-plato" method="post" class="needs-validation" novalidate>
	
	<!-- Id -->
	<div class="form-group row">
		<label for="id" class="col-md-4 col-lg-3 col-form-label">Id</label>
		<div class="col">
			<input type="number" class="form-control ${plato.errorId != null ? 'is-invalid' : '' }" id="id" name="id" value="${plato.id}"
				readonly placeholder="Id del plato">
			<div class="valid-feedback">Id correcto</div>
			<div class="invalid-feedback">${plato.errorId}</div>
		</div>
	</div>
	
	<!-- Nombre -->
	<div class="form-group row">
		<label for="nombre" class="col-md-4 col-lg-3 col-form-label">Nombre</label>
		<div class="col">
			<input type="text" class="form-control ${plato.errorNombre != null ? 'is-invalid' : '' }" id="nombre" name="nombre" value="${plato.nombre}"
				required minlength="3" pattern="\p{Lu}\p{Ll}{2}[\p{Ll} ]*"
				placeholder="Debe introducir un nombre con solo letras y mayúscula la primera. Mínimo tres caracteres.">
			<div class="valid-feedback">Nombre correcto</div>
			<div class="invalid-feedback">${plato.errorNombre != null ? plato.errorNombre : 'Debe introducir un nombre con
				como mínimo 3 letras, y solo letras y mayúscula la primera'}</div>
		</div>
	</div>
	
	<!-- Calorias -->
	<div class="form-group row">
		<label for="calorias" class="col-md-4 col-lg-3 col-form-label">Calorías</label>
		<div class="input-group col">		
			<input type="number" class="form-control ${plato.errorCalorias != null ? 'is-invalid' : '' }" id="calorias" value="${plato.calorias}"
				name="calorias" required min="0" placeholder="Debe introducir un número mayor que 0"/>
			<div class="input-group-append">
				<span class="input-group-text rounded-right">cal.</span>
			</div>
			<div class="valid-feedback">Calorías correctas</div>
			<div class="invalid-feedback">${plato.errorCalorias != null ? plato.errorCalorias : 'Las calorias debe ser mayor o igual a cero' }</div>
		</div>
	</div>
	
	<!-- Categoria -->
	<div class="form-group row">
		<label for="categoria" class="col-md-4 col-lg-3 col-form-label">Categoría</label>
		<div class="col">
			<select class="form-control custom-select ${plato.errorCategoria != null ? 'is-invalid' : '' }" required id="categoria" name="categoria">
				<option value="0">Introduzca la categoría ...</option>					
				<c:forEach items="${categorias}" var="categoria">
					<option value="${categoria.id}" ${categoria.id == plato.categoria.id ? 'selected' : ''}>${categoria.nombre}</option>
				</c:forEach>
			</select>
			<div class="valid-feedback">Categoría del plato correcta</div>
			<div class="invalid-feedback">${plato.errorCategoria != null ? plato.errorCategoria : 'Es obligatorio introducir la Categoría del plato'}</div>
		</div>
	</div>
	
	<!-- Origen -->
	<div class="form-group row">
		<label for="origen" class="col-md-4 col-lg-3 col-form-label">Origen</label>
		<div class="col">
			<select class="form-control custom-select ${plato.errorOrigen != null ? 'is-invalid' : '' }" required id="origen" name="origen">
				<option value="0">Introduzca el origen ...</option>					
				<c:forEach items="${origenes}" var="origen">
					<option value="${origen.id}" ${origen.id == plato.origen.id ? 'selected' : ''}>${origen.nombre}</option>
				</c:forEach>
			</select>
			<div class="valid-feedback">Origen del plato correcto</div>
			<div class="invalid-feedback">${plato.errorOrigen != null ? plato.errorOrigen : 'Es obligatorio introducir el Origen del plato'}</div>
		</div>
	</div>
	
	<div class="form-group row">
		<div class="offset-md-4 offset-lg-3 col">
			<button type="submit" class="btn btn-primary">Guardar</button>
			<a class="btn btn-primary" href="index">Salir sin guardar</a>
		</div>
	</div>
</form>


<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>