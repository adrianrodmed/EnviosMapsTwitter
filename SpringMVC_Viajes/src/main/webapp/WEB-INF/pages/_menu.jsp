<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Menu</title>
	</head>
	<body style="text-align: center;">
		<div class="menu-container">
			<a href="${pageContext.request.contextPath}/">Indice</a>|
			<a href="${pageContext.request.contextPath}/listaDestino">Lista de Destinos</a>|
			<a href="${pageContext.request.contextPath}/carritoCompra">Mi Carrito</a>|
			<security:authorize access="hasAnyRole('MANAGER','EMPLOYEE')">
				<a href="${pageContext.request.contextPath}/listaCompra">Lista de la Compra</a>|
			</security:authorize>
			<security:authorize access="hasRole('MANAGER')">
				<a href="${pageContext.request.contextPath}/destino">Crear Destino</a>|
			</security:authorize>
		</div>
	</body>
</html>
