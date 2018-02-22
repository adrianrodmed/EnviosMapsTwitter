<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Acceso Denegado</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body style="text-align: center;">
		<jsp:include page="_encabezado.jsp" />
		<jsp:include page="_menu.jsp" />
		<div class="page-title">¡Acceso Denegado!</div>
		<h3 style="color: red;">Lo siento, no puedes acceder a esta pagina.</h3>
		<jsp:include page="_piePagina.jsp" />
	</body>
</html>
