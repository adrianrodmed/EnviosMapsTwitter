<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Encabezado</title>
	</head>
	<body style="text-align: center;">
		<div class="header-container">
			<div class="site-name">Tienda Online</div>
			<div class="header-bar">
				<c:if test="${pageContext.request.userPrincipal.name != null}">Buenos dias
					<a href="${pageContext.request.contextPath}/cuentaInfo">${pageContext.request.userPrincipal.name}</a>&nbsp;|&nbsp;
					<a href="${pageContext.request.contextPath}/cierreSesion">Cerrar Sesion</a>
				</c:if>
				<c:if test="${pageContext.request.userPrincipal.name == null}">
					<a href="${pageContext.request.contextPath}/registro">Registrarse</a>
				</c:if>
			</div>
		</div>
	</body>
</html>