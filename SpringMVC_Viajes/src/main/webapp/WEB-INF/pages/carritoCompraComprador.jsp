<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Enter Customer Information</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body style="text-align: center;">
		<jsp:include page="_encabezado.jsp" />
		<jsp:include page="_menu.jsp" />
		<div class="page-title">Introduzca la Informacion del Comprador</div>
		<form:form method="POST" modelAttribute="compradorForm" action="${pageContext.request.contextPath}/carritoCompraComprador">
			<table>
				<tr>
					<td>Nombre: </td>
					<td><form:input path="nombrecomprador"/></td>
					<td><form:errors path="nombrecomprador" class="error-message"/></td>
				</tr>
				<tr>
					<td>Email: </td>
					<td><form:input path="mailcomprador" /></td>
					<td><form:errors path="mailcomprador" class="error-message"/></td>
				</tr>
				<tr>
					<td>Telefono: </td>
					<td><form:input path="telefonocomprador" /></td>
					<td><form:errors path="telefonocomprador" class="error-message"/></td>
				</tr>
				<tr>
					<td>Direccion: </td>
					<td><form:input path="direccioncomprador"/></td>
					<td><form:errors path="direccioncomprador" class="error-message"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Alta"/> <input type="reset" value="Reinicia"/></td>
				</tr>
			</table>
		</form:form>
		<jsp:include page="_piePagina.jsp" />
	</body>
</html>