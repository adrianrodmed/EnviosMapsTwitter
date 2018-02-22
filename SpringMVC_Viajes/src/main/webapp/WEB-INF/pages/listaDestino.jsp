<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lista de Destinos</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body>
		<jsp:include page="_encabezado.jsp"/>
		<jsp:include page="_menu.jsp"/>
		<fmt:setLocale value="en_US" scope="session"/>
		<div class="page-title">Lista de Destinos</div>
		<c:forEach items="${paginationDestinos.list}" var="destInfo">
			<div class="product-preview-container">
				<ul>
					<li><img class="product-image" src="${pageContext.request.contextPath}/destinoImagen?iddestino=${destInfo.iddestino}" /></li>
					<li>Id: ${destInfo.iddestino}</li>
					<li>Nombre: ${destInfo.nombre}</li>
					<li>Descripcion: ${destInfo.descripcioncorta}</li>
<%-- 					<li>Fecha: ${destInfo.fecha}</li> --%>
<%-- 					<li>Nº de plazas: ${destInfo.numplazas}</li> --%>
					<li>Precio: <fmt:formatNumber value="${destInfo.precio}" type="currency"/></li>
					<li><a href="${pageContext.request.contextPath}/compraDestino?iddestino=${destInfo.iddestino}">Comprar ahora</a></li>
				<!-- For Manager edit Product -->
					<security:authorize access="hasRole('MANAGER')">
						<li><a style="color: red;" href="${pageContext.request.contextPath}/destino?iddestino=${destInfo.iddestino}">Edita un Destino</a></li>
					</security:authorize>
				</ul>
			</div>
		</c:forEach><br/>
		<c:if test="${paginationDestinos.totalPages > 1}">
			<div class="page-navigator">
				<c:forEach items="${paginationDestinos.navigationPages}" var = "page">
					<c:if test="${page != -1 }">
						<a href="listaDestino?page=${page}" class="nav-item">${page}</a>
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