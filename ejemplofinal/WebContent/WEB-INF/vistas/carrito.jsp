<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />
<table class="table table-striped table-bordered table-hover table-sm text-right">
	<caption>Carrito</caption>
	<thead class="thead-dark">
		<tr>
			<th scope="col" class="text-center">Id</th>
			<th scope="col" class="text-left">Nombre</th>
			<th scope="col">Cantidad</th>
			<th scope="col">Precio unidad</th>
			<th scope="col">Importe</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${carrito.lineas}" var="linea">
		<tr>
			<th scope="row" class="text-center">${linea.producto.id}</th>
			<td class="text-left">${linea.producto.nombre}</td>
			<td>${linea.cantidad}</td>
			<td><fmt:formatNumber value="${linea.producto.precio}" type="currency" /></td>						
			<c:set var="importe" value="${(linea.cantidad * linea.producto.precio)}" />
			<c:set var="totalimporte" value="${(linea.cantidad * linea.producto.precio) + totalimporte}" />
			<td><fmt:formatNumber type="currency" value="${importe}"/></td>
		</tr>
		</c:forEach>
	</tbody>
	
	<tfoot>
		<tr>
			<th scope="row" colspan="4">Total</th>
			<td class="font-weight-bold"><fmt:formatNumber
							value="${totalimporte}" type="currency" /></td>
		</tr>
		
	</tfoot>
</table>

<a class="btn btn-primary" href="index">Seguir comprando</a>
<a class="btn btn-primary" href="cliente">Confirmar compra</a>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>>