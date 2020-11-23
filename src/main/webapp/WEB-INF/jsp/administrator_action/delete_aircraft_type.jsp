<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="aircraft_type.delete.ok"
	var="aircraft_type_deleted" />
<fmt:message bundle="${loc}" key="aircraft_type.delete.fail"
	var="aircraft_type_not_deleted" />
<fmt:message bundle="${loc}" key="aircraft_type.delete" var="delete_type" />
<fmt:message bundle="${loc}" key="aircraft_type.choose_type"
	var="choose_type" />
<meta charset="UTF-8">
<title>Add flight</title>
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
		</c:when>
		<c:otherwise>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="DELETE_AIRCRAFT_TYPE">
				<input type="hidden" name="delete" value="delete"> <label><c:out
						value="${choose_type}" /></label><br> <select name="aircraft_types">
					<c:forEach var="type_item" items="${aircraftTypes}">
						<option>
							<c:out value="${type_item.aircraftType}" />
							<c:set var="id_iarcraft_type" scope="session"
								value="${type_item.idAircraftType }" />
						</option>
					</c:forEach>
				</select> <input type="submit" value="${delete_type}" />
			</form>
			<br>
			<c:choose>
				<c:when test="${result_attr=='success'}">
					<c:out value="${aircraft_type_deleted}" />
				</c:when>
				<c:when test="${result_attr=='fail'}">
					<c:out value="${aircraft_type_not_deleted}" />
				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>
</body>
</html>