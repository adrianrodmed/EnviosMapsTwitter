<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Shopping Cart Finalize</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body style="text-align: center;">
		<jsp:include page="_encabezado.jsp"/>
		<jsp:include page="_menu.jsp"/>
		<div class="page-title">Finalizado</div>
		<div class="container">
			<h3>Gracias por su compra</h3>
			Tu numero de compra es: ${ultimaCompraCarrito.numcompra}
		</div>
		<div class="container">
			<a href="/SpringMVC_Viajes/.pdf">Descarga el PDF de tu factura</a>
		</div>
		<jsp:include page="_piePagina.jsp" />
	</body>
</html>