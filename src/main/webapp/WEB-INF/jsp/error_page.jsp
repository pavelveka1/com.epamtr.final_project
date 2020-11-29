<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Error</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="button.goToHomePage" var="go_home_btn" />
</head>
<body>
<body>
	<div class="wrapper">
		<div class="header">
			<header>
				<jsp:include page="fragment/header.jsp" />
			</header>
		</div>
		<div class="menu">
			<c:choose>
				<c:when test="${menu=='admin_menu'}">
					<jsp:include page="fragment/admin_menu.jsp"></jsp:include>
				</c:when>
				<c:when test="${menu=='dispatcher_menu'}">
					<jsp:include page="fragment/dispatcher_menu.jsp"></jsp:include>
				</c:when>
				<c:when test="${menu=='manager_menu'}">
					<jsp:include page="fragment/crew_menu.jsp"></jsp:include>
				</c:when>
				<c:when test="${menu=='crew_menu'}">
					<jsp:include page="fragment/crew_menu.jsp"></jsp:include>
				</c:when>
			</c:choose>
		</div>
		<div class="content">
			<main>
				<java-classes:errorInformation errorCode="${pageContext.errorData.statusCode}" />
			</main>
		</div>
		<div class="footer">
			<footer>
				<jsp:include page="fragment/footer.jsp" />
			</footer>
		</div>
	</div>
</body></body>
</html>