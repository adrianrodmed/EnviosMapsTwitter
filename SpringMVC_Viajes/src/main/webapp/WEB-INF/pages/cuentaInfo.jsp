<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Informacion de Cuenta</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
	</head>
	<body style="text-align: center;">
		<jsp:include page="_encabezado.jsp" />
		<jsp:include page="_menu.jsp" />
		<div class="page-title">Informacion de Cuenta</div>
		<div class="account-container">
			<ul>
				<li>Nombre: ${pageContext.request.userPrincipal.name}</li>
				<li>Role:
					<ul>
						<c:forEach items="${userDetails.authorities}" var="auth">
							<li>${auth.authority}</li>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
		<jsp:include page="_piePagina.jsp" />
	</body>
</html>
