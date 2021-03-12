<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />
<h2>Migración de BB.DD.</h2>
<br />
	<!-- Base de datos -->
	<div class="form-group row">
		<label for="dbDestino" class="col-md-4 col-lg-3 col-form-label">Base de datos DESTINO</label>
		<div class="col">
			<select class="form-control custom-select" id="dbDestino" name="dbDestino">
				<option value="">Listado de BB.DD ...</option>
			<!-- ${indicelistaDB} -->
				<c:set var="indice" value="0" />	
				<c:forEach items="${listaDB}" var="db">
					<option value="${indice = indice + 1}" ${indice == indicelistaDB ? 'selected' : ''}>${db}</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<!-- Tablas -->
	<div class="form-group row">
		<label for="tablas" class="col-md-4 col-lg-3 col-form-label">Tablas de la BB.DD. ${dbDestino}</label>
		<div class="col">
			<select class="form-control custom-select" id="tablas" name="tablas">
				<option value="">Tablas de la BB.DD ... </option>
			
				<c:set var="indice" value="0" />	
				<c:forEach items="${tablas}" var="db">
					<option value="${indice = indice + 1}">${db}</option>
				</c:forEach>
			</select>
		</div>
	</div>
<br />
<div class="form-group row">
	<a class="btn btn-primary" href="ddbb">Volver</a>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>