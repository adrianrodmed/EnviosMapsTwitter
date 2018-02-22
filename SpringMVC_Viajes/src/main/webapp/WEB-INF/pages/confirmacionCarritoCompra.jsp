<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Shopping Cart Confirmation</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body style="text-align: center;">
		<jsp:include page="_encabezado.jsp" />
		<jsp:include page="_menu.jsp" />
		<fmt:setLocale value="en_US" scope="session"/>
		<div class="page-title">Confirmacion</div>
		<div class="customer-info-container">
			<h3>Informacion del Comprador:</h3>
			<ul>
				<li>Name: ${miCarrito.compradorInfo.nombrecomprador}</li>
				<li>Email: ${miCarrito.compradorInfo.mailcomprador}</li>
				<li>Phone: ${miCarrito.compradorInfo.telefonocomprador}</li>
				<li>Address: ${miCarrito.compradorInfo.direccioncomprador}</li>
			</ul>
			<h3>Resumen del Carrito: </h3>
			<ul>
				<li>Quantity: ${miCarrito.cantidadTotal}</li>
				<li>Total: <span class="total"><fmt:formatNumber value="${miCarrito.precioTotal}" type="currency"/></span></li>
			</ul>
		</div>
		<form method="POST" action="${pageContext.request.contextPath}/confirmacionCarritoCompra">
		<!-- Edit Cart -->
			<a class="navi-item" href="${pageContext.request.contextPath}/carritoCompra">Edita el Carrito</a>
		<!-- Edit Customer Info -->
			<a class="navi-item" href="${pageContext.request.contextPath}/carritoCompraComprador">Edita la Informacion del Comprador</a>
		<!-- Send/Save -->
			<input type="submit" value="Comprar" class="button-send-sc" />
		</form>
		<div class="container">
			<a href="/SpringMVC_Viajes/.pdf">Descarga el PDF de tu factura</a>
		</div>
		<div class="container">
			<c:forEach items="${miCarrito.lineaCarrito}" var="lineaCarritoInfo">
				<div class="product-preview-container">
					<ul>
						<li><img class="product-image" src="${pageContext.request.contextPath}/imagenDestino?iddestino=${lineaCarritoInfo.destinoInfo.iddestino}" /></li>
						<li>Id: ${lineaCarritoInfo.destinoInfo.iddestino} <input type="hidden" name="iddestino" value="${lineaCarritoInfo.destinoInfo.iddestino}" /></li>
						<li>Nombre: ${lineaCarritoInfo.destinoInfo.nombre}</li>
						<li>Descripcion: ${lineaCarritoInfo.destinoInfo.descripcioncorta}</li>
						<li>Fecha: <fmt:formatDate value="${lineaCarritoInfo.destinoInfo.fecha}" pattern="dd-MM-yyyy HH:mm"/></li>
						<li>Nº de Plazas: <fmt:formatNumber value="${lineaCarritoInfo.destinoInfo.numplazas}" type="currency"/></li>
						<li>Precio: <span class="price"><fmt:formatNumber value="${lineaCarritoInfo.destinoInfo.precio}" type="currency"/></span></li>
						<li>Cantidad: ${lineaCarritoInfo.cantidad}</li>
						<li>Subtotal: <span class="subtotal"><fmt:formatNumber value="${lineaCarritoInfo.preciototal}" type="currency"/></span></li>
					</ul>
				</div>
			</c:forEach>
		</div>
		<jsp:include page="_piePagina.jsp" />
	</body>
</html>