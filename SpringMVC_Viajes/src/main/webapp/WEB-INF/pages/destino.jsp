<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body style="text-align: center;">
		<jsp:include page="_encabezado.jsp" />
		<jsp:include page="_menu.jsp" />
		<div class="page-title">Destino</div>
		<c:if test="${not empty errorMessage}">
			<div class="error-message">${errorMessage}</div>
		</c:if>
		<form:form modelAttribute="destinoForm" method="POST" enctype="multipart/form-data">
			<table style="text-align: left;">
				<tr>
					<td>Id: </td>
					<td style="color: red;">
						<c:if test="${not empty destinoForm.iddestino}">
							<form:hidden path="iddestino"/>${destinoForm.iddestino}
						</c:if>
						<c:if test="${empty destinoForm.iddestino}">
							<form:input path="iddestino"/>
							<form:hidden path="newDestino"/>
						</c:if>
					</td>
					<td><form:errors path="iddestino" class="error-message" /></td>
				</tr>
				<tr>
					<td>Nombre: </td>
					<td><form:input path="nombre"/></td>
					<td><form:errors path="nombre" class="error-message" /></td>
				</tr>
				<tr>
					<td>Fecha: </td>
					<td><form:input path="fecha" /></td>
					<td><form:errors path="fecha" class="error-message" /></td>
				</tr>
				<tr>
					<td>Descripcion corta: </td>
					<td><form:input path="descripcioncorta" /></td>
					<td><form:errors path="descripcioncorta" class="error-message" /></td>
				</tr>
				<tr>
					<td>Nº de plazas: </td>
					<td><form:input path="numplazas" /></td>
					<td><form:errors path="numplazas" class="error-message" /></td>
				</tr>
				<tr>
					<td>Precio: </td>
					<td><form:input path="precio" /></td>
					<td><form:errors path="precio" class="error-message" /></td>
				</tr>
				<tr>
					<td>Imagen: </td>
					<td><img src="${pageContext.request.contextPath}/destinoImagen?iddestino=${destinoForm.iddestino}" width="100"/></td>
					<td></td>
				</tr>
				<tr>
					<td>Upload Image</td>
					<td><form:input type="file" path="fileData"/></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Añade" /> <input type="reset" value="Reinicia" /></td>
				</tr>
			</table>
		</form:form>
		<jsp:include page="_piePagina.jsp" />
	</body>
</html>