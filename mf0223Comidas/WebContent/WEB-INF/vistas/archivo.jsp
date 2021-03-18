<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />
<h2>Restaurar Base de Datos</h2>
    
	<form action="" method="post" class="needs-validation" enctype="multipart/form-data">
		<div class="form-group">
			<div class="input-group input-file" name="fichero">
    			<input type="text" class="form-control" placeholder='Selecciona archivo .sql para restaurar la BB.DD ...' required/>			
            	<span class="input-group-btn">
        			<button type="button" class="btn btn-light btn-choose" >Buscar</button>
    			</span>
			</div>
		</div>
	
		<div class="form-group">
			<button type="submit" class="btn btn-primary pull-right">Aceptar</button>
			<button type="reset" class="btn btn-danger">Borrar</button>
		</div>
	</form>
	
	<script>
	function bs_input_file() {
		$(".input-file").before(
			function() {
				if ( ! $(this).prev().hasClass('input-ghost') ) {
					var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
					element.attr("name",$(this).attr("name"));
					element.change(function(){
						element.next(element).find('input').val((element.val()).split('\\').pop());
					});
					$(this).find("button.btn-choose").click(function(){
						element.click();
					});
					$(this).find("button.btn-reset").click(function(){
						element.val(null);
						$(this).parents(".input-file").find('input').val('');
					});
					$(this).find('input').css("cursor","pointer");
					$(this).find('input').mousedown(function() {
						$(this).parents('.input-file').prev().click();
						return false;
					});
					return element;
				}
			}
		);
	}
	$(function() {
		bs_input_file();
	});
	</script>
	
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>