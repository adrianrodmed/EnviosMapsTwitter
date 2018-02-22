<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product List</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body style="text-align: center;">
		<jsp:include page="_encabezado.jsp"/>
		<jsp:include page="_menu.jsp"/>
		<fmt:setLocale value="en_US" scope="session"/>
		<div class="page-title">Informacion de Compra</div>
		<div class="customer-info-container">
			<h3>Informacion del Comprador:</h3>
			<ul>
				<li>Name: ${compraInfo.nombrecomprador}</li>
				<li>Email: ${compraInfo.mailcomprador}</li>
				<li>Telefono: ${compraInfo.telefonocomprador}</li>
				<li>Direccion: ${compraInfo.direccioncomprador}</li>
			</ul>
			<h3>Compra: </h3>
			<ul>
				<li>Total: <span class="total"><fmt:formatNumber value="${compraInfo.preciototal}" type="currency"/></span></li>
			</ul>
		</div><br/>
		<table border="1" style="width: 100%">
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Cantidad</th>
				<th>Precio</th>
				<th>Precio Total</th>
			</tr>
			<c:forEach items="${compraInfo.reservas}" var="reservaInfo">
				<tr>
					<td>${reservaInfo.iddestino}</td>
					<td>${reservaInfo.nombre}</td>
					<td>${reservaInfo.cantidad}</td>
					<td>${reservaInfo.numplazas}</td>
					<td><fmt:formatNumber value="${reservaInfo.precio}" type="currency"/></td>
					<td><fmt:formatNumber value="${reservaInfo.preciototal}" type="currency"/></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${paginationResult.totalPages > 1}">
			<div class="page-navigator">
				<c:forEach items="${paginationResult.navigationPages}" var = "page">
					<c:if test="${page != -1 }">
						<a href="listaCompra?page=${page}" class="nav-item">${page}</a>
					</c:if>
					<c:if test="${page == -1 }">
						<span class="nav-item"> ... </span>
					</c:if>
				</c:forEach>
			</div>
		</c:if>
		<jsp:include page="_piePagina.jsp" />
	</body>
</html>