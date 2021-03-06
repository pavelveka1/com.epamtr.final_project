<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<title>Update aircraft</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="aircraft.update.ok"
	var="aircraft_update_ok" />
<fmt:message bundle="${loc}" key="aircraft.update.fail"
	var="aircraft_update_fail" />
<fmt:message bundle="${loc}" key="aircraft.reg.number.not_valid"
	var="registration_number_not_valid" />
<fmt:message bundle="${loc}" key="aircraft.choose_aircraft"
	var="choose_aircraft" />
	<fmt:message bundle="${loc}" key="airline.update"
	var="update_aircraft" />
</head>
<body>
	<br>
	<c:out value="${error}" />
	<br>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="UPDATE_AIRCRAFT">
				<label><c:out value="${choose_aircraft}"></c:out></label> <select name="aircraft_numbers">
					<c:forEach var="type_item" items="${aircrafts}">
						<option>
							<c:out value="${type_item.registerNumber}" />
						</option>
					</c:forEach>
				</select> <input type="text" name="register_number"
					placeholder="New registration number"> <input type="submit"
					value="${update_aircraft}" />
			</form>

			<br>
			<c:choose>
				<c:when test="${data_is_valid==false}">
					<c:out value="${registration_number_not_valid}" />
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${result_attr=='success'}">
					<c:out value="${aircraft_update_ok}" />
				</c:when>
				<c:when test="${result_attr=='fail'}">
					<c:out value="${aircraft_update_fail}" />
				</c:when>
			</c:choose>
		
</body>
</html>