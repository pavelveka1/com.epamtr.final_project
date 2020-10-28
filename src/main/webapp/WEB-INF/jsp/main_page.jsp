<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
 <fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="user" var="button_ru" />
<fmt:message bundle="${loc}" key="button.name.en" var="button_en" />
<fmt:message bundle="${loc}" key="button.name.sign_out" var="sign_out" />
<title>CSS Template</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	background-color: black
}

.wrapper {
	display: grid;
	grid-template-columns: repeat(5, 1fr);
	grid-gap: 5px;
	grid-template-rows: repeat(8, 1fr);
	border: 2px solid #f76707;
	border-radius: 5px;
	background-color: #fff4e6;
}

.wrapper>div {
	border: 2px solid #ffa94d;
	border-radius: 5px;
	background-color: #ffd8a8;
	padding: 1em;
	color: #d9480f;
}

.header {
	grid-column: 1/6;
	grid-row: 1;
	background-image:
		url(http://liveangarsk.ru/files/images/2234043bdc76379567a0073555f2190f-oblaka20130728_161644.jpg);
	background-repeat: no-repeat;
	background-size: cover;
}

.menu {
	grid-column: 1;
	grid-row: 2/8;
	background-image: url(https://informburo.kz/img/inner/824/90/172.jpg);
	background-repeat: no-repeat;
	background-size: cover;
}

.content {
	grid-column: 2/6;
	grid-row: 2/8;
	background-image:
		url(https://farm6.staticflickr.com/5752/22216960510_a97d67072c_o.jpg);
	background-repeat: no-repeat;
	background-size: cover;
}

.footer {
	grid-column: 1/6;
	grid-row: 8;
	background-image:
		url(https://ic.pics.livejournal.com/gotskaya/15977901/237456/237456_900.jpg);
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
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
		<jsp:include page="fragment/manager_menu.jsp"></jsp:include>
		</c:when>
		<c:when test="${menu=='crew_menu'}">
		<jsp:include page="fragment/crew_menu.jsp"></jsp:include>
		</c:when>
		</c:choose>
		</div>
		<div class="content">
			<main>
		<jsp:include page="${current_page}" />
	</main>
		</div>
		<div class="footer">
			<footer>
				<jsp:include page="fragment/footer.jsp" />
			</footer>
		</div>
	</div>
</body>
</html>


</html>