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
		<jsp:include page="_encabezado.jsp" />
		<jsp:include page="_menu.jsp" />
		<fmt:setLocale value="en_US" scope="session"/>
		<div class="page-title">Lista de la Compra</div>
		<div>Total Order Count: ${paginationResult.totalRecords}</div>
		<table border="1" style="width:100%">
			<tr>
				<th>Nº de Compra</th>
				<th>Fecha de Compra</th>
				<th>Nombre del Comprador</th>
				<th>Direccion del Comprador</th>
				<th>Mail del Comprador</th>
				<th>Telefono del Comprador</th>
				<th>Precio Total</th>
				<th>Vista</th>
			</tr>
			<c:forEach items="${paginationResult.list}" var="compraInfo">
				<tr>
					<td>${compraInfo.numcompra}"</td>
					<td><fmt:formatDate value="${compraInfo.fechacompra}" pattern="dd-MM-yyyy HH:mm"/></td>
					<td>${compraInfo.nombrecomprador}</td>
					<td>${compraInfo.direccioncomprador}</td>
					<td>${compraInfo.mailcomprador}</td>
					<td>${compraInfo.telefonocomprador}</td>
					<td style="color:red;"><fmt:formatNumber value="${compraInfo.preciototal}" type="currency"/></td>
					<td><a href="${pageContext.request.contextPath}/compra?idcompra=${compra.idcompra}">Vista</a></td>
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