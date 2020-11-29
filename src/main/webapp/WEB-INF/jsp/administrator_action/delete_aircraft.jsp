<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="aircraft.delete.ok"
	var="aircraft_delete_ok" />
<fmt:message bundle="${loc}" key="aircraft.delete.fail"
	var="aircraft_delete_fail" />
<fmt:message bundle="${loc}" key="aircraft.delete_aircraft" var="delete" />
<fmt:message bundle="${loc}" key="aircraft.choose_aircraft"
	var="choose_aircraft" />
<meta charset="UTF-8">
<title>Delete aircraft</title>
</head>
<body>
	<br>
	<c:out value="${error}" />
	<br>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="DELETE_AIRCRAFT"> <input
			type="hidden" name="delete_aircraft" value="DELETE_AIRCRAFT">
		<label><c:out value="${choose_aircraft}"></c:out></label> <select
			name="aircraft_numbers">
			<c:forEach var="type_item" items="${aircrafts}">
				<option>
					<c:out value="${type_item.registerNumber}" />
				</option>
			</c:forEach>
		</select> <input type="submit" value="${delete}" />
	</form>
	<c:choose>
		<c:when test="${result_attr=='success'}">
			<c:out value="${aircraft_delete_ok}" />
		</c:when>
		<c:when test="${result_attr=='fail'}">
			<c:out value="${aircraft_delete_fail}" />
		</c:when>
	</c:choose>

</body>
</html>