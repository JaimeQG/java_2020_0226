<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />
<h2>Migración de BB.DD.</h2>
<br />
<form action="ddbb" method="post" class="needs-validation" novalidate enctype="multipart/form-data">
	
	<!-- Base de datos -->
	<div class="form-group row">
		<label for="dbOrigen" class="col-md-4 col-lg-3 col-form-label">Base de datos ORIGEN</label>
		<div class="col">
			<select class="form-control custom-select" required id="dbOrigen" name="dbOrigen">
				<option value="">Selecione la BB.DD a copiar ...</option>
			
				<c:set var="indice" value="0" />	
				<c:forEach items="${listaDB}" var="db">
					<option value="${indice = indice + 1}">${db}</option>
				</c:forEach>
			</select>
			<div class="valid-feedback">BB.DD correcta</div>
			<div class="invalid-feedback">Es obligatorio introducir la BB.DD</div>
		</div>
	</div>
		
	<!-- Nombre -->
	<div class="form-group row">
		<label for="nombre" class="col-md-4 col-lg-3  col-form-label">Base de datos DESTINO</label>
		<div class="col">
			<input type="text" class="form-control" id="nombre" name="nombre" 
				required minlength="5" pattern="p{Ll}{5}[\p{Ll}]*"
				placeholder="Nombre de la nueva BB.DD. Debe introducir un nombre con solo letras. Mínimo cinco caracteres.">
			<div class="valid-feedback">Nombre correcto</div>
			<div class="invalid-feedback">Debe introducir un nombre de la BB.DD DESTINO con
				como mínimo 5 letras, y solo letras</div>
		</div>
	</div>
	
	<div class="form-group row">
		<div class="offset-md-4 offset-lg-3 col">
			<button type="submit" class="btn btn-primary">Copiar BB.DD</button>
		</div>
	</div>

</form>
<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>