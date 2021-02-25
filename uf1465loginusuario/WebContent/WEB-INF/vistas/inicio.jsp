<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<br />

<h2>UF1465 – Ejercicio 3</h2>
<br />
<h4>Hola ${sessionScope.usuario.email} !!</h4>
<br />
<p>Bienvenido a la aplicación.</p>
<p>Tienes permisos de <strong>${sessionScope.usuario.rol.descripcion}</strong></p>
<strong>${mensajeTexto}</strong>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>