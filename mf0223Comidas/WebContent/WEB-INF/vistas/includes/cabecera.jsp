<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="es_ES" />
<!doctype html>
<html lang="es">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<base href="${pageContext.servletContext.contextPath}/" />

<!-- favicon -->
<link rel="shortcut icon" href="favicon.ico">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- FontAwesome -->
<!--  font awesome -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="css/all.min.css"> -->
<!-- Datatables -->
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />


<title>MF0223_3 Comidas</title>

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">MF0223 Comidas</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
      			<li class="nav-item active">
        			<a class="nav-link" href="index">Principal<span class="sr-only">(current)</span></a>
      			</li>
			</ul>
		</div>
		
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="restore">Restaurar BB.DD.</a></li>
		</ul>	
	</nav>

	<c:if test="${alerta != null}">
		<div class="alert alert-${alerta.nivel} alert-dismissible fade show"
				role="alert">
				${alerta.texto}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
		</div>
	</c:if>

<main class="container-fluid pt-3">