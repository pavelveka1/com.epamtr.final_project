<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="deleted.user.ok" var="deleted_user_ok" />
<fmt:message bundle="${loc}" key="deleted.user.fail"
	var="deleted_user_fail" />
<title>Delete user</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="DELITE_USER"> <input
			type="text" name="login" placeholder="Login name"> <input
			type="submit" value="delite user" />
	</form>
	<br>
	<br>
	<c:choose>
		<c:when test="${deleted_user=='deleted'}">
			<c:out value="${deleted_user_ok}" />
		</c:when>
		<c:when test="${deleted_user=='not_deleted'}">
			<c:out value="${deleted_user_fail}" />
		</c:when>
	</c:choose>
</body>
</html>