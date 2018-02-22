<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Shopping Cart</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body style="text-align: center;">
		<jsp:include page="_encabezado.jsp" />
		<jsp:include page="_menu.jsp" />
		<fmt:setLocale value="en_US" scope="session"/>
		<div class="page-title">Mi Carrito</div>
		<c:if test="${empty carritoForm or empty carritoForm.lineaCarrito}">
			<h2>El carrito esta vacio</h2>
			<a href="${pageContext.request.contextPath}/listaDestino">Muestra la Lista de Destinos</a>
		</c:if>
		<c:if test="${not empty carritoForm and not empty carritoForm.lineaCarrito}">
			<form:form method="POST" modelAttribute="carritoForm" action="${pageContext.request.contextPath}/carritoCompra">
				<c:forEach items="${carritoForm.lineaCarrito}" var="lineaCarritoInfo" varStatus="varStatus">
					<div class="product-preview-container">
						<ul>
							<li><img class="product-image" src="${pageContext.request.contextPath}/imagenDestino?iddestino=${lineaCarritoInfo.destinoInfo.iddestino}"/></li>
							<li>Id: ${lineaCarritoInfo.destinoInfo.iddestino}<form:hidden path="lineaCarrito[${varStatus.index}].destinoInfo.iddestino"/></li>
							<li>Nombre: ${lineaCarritoInfo.destinoInfo.nombre}</li>
							<li>Descripcion: ${lineaCarritoInfo.destinoInfo.descripcioncorta}</li>
							<li>Fecha: <fmt:formatDate value="${lineaCarritoInfo.destinoInfo.fecha}" pattern="dd-MM-yyyy HH:mm"/></li>
							<li>Nº de Plazas: <fmt:formatNumber value="${lineaCarritoInfo.destinoInfo.numplazas}" type="currency"/></li>
							<li>Precio: <span class="price"><fmt:formatNumber value="${lineaCarritoInfo.destinoInfo.precio}" type="currency"/></span></li>
							<li>Cantidad: <form:input path="lineaCarrito[${varStatus.index}].cantidad" /></li>
							<li>Subtotal: <span class="subtotal"><fmt:formatNumber value="${lineaCarritoInfo.preciototal}" type="currency"/></span></li>
							<li><a href="${pageContext.request.contextPath}/eliminarDestinoCarritoCompra?iddestino=${lineaCarritoInfo.destinoInfo.iddestino}"> Borrar </a></li>
						</ul>
					</div>
				</c:forEach>
				<div style="clear: both"></div>
				<input class="button-update-sc" type="submit" value="Actualiza cantidad"/>
				<a class="navi-item" href="${pageContext.request.contextPath}/carritoCompraComprador">Introduce la Informacion del Comprador</a>
				<a class="navi-item" href="${pageContext.request.contextPath}/listaDestino">Continua Comprando</a>
			</form:form>
		</c:if>
		<jsp:include page="_piePagina.jsp" />
	</body>
</html>