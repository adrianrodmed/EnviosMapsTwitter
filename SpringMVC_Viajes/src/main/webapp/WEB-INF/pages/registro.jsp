<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registro</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body style="text-align: center;">
		<jsp:include page="_encabezado.jsp" />
		<jsp:include page="_menu.jsp" />
		<div class="page-title">Registro</div>
		<div class="login-container">
			<h3>Introduzca Nombre y Contraseña</h3><br>
		<!-- /login?error=true -->
			<c:if test="${param.error == 'true'}">
				<div style="color: red; margin: 10px 0px;">
					Registro fallido<br /> Razon : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
				</div>
			</c:if>
			<form method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
				<table>
					<tr>
						<td>Nombre de Usuario: </td>
						<td><input name="nombre"/></td>
					</tr>
					<tr>
						<td>Contraseña: </td>
						<td><input type="password" name="pass"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Registro" /> <input type="reset" value="Reinicio" /></td>
						<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
					</tr>
				</table>
			</form>
			<span class="error-message">${error}</span>
		</div>
		<jsp:include page="_piePagina.jsp" />
	</body>
</html>