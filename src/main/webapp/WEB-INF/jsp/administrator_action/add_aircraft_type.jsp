<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="aircraft_type.add.ok"
	var="aircraft_type_add_ok" />
<fmt:message bundle="${loc}" key="aircraft_type.add.fail"
	var="aircraft_type_add_fail" />
<fmt:message bundle="${loc}" key="aircraft.type.not_valid"
	var="type_not_valid" />
<fmt:message bundle="${loc}" key="aircraft.flight_range.not_valid"
	var="range_flight_not_valid" />
<fmt:message bundle="${loc}" key="aircraft.number_passenger.not_valid"
	var="number_passengers_not_valid" />
<meta charset="UTF-8">
<title>Add flight</title>
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
Error is not null
</c:when>
		<c:otherwise>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_AIRCRAFT_TYPE">


				<table>
					<tr>
						<td><label>Enter new type of aircraft</label></td>
						<td><input type="text" name="aircraft_type"
							placeholder="aircraft type"> <c:choose>
								<c:when test="${type_valid==false}">
									<c:out value="${type_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label>Enter range of flight</label></td>
						<td><input type="number" name="range_flight"
							placeholder="range of flight"> <c:choose>
								<c:when test="${flight_range_valid==false}">
									<c:out value="${range_flight_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label>Enter number of passenger</label></td>
						<td><input type="number" name="number_passengers"
							placeholder="passenger's number"> <c:choose>
								<c:when test="${number_passengers_valid==false}">
									<c:out value="${number_passengers_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
				</table>

				<br> <input type="submit" value="Apply" />
			</form>

			<br>
			<c:choose>
				<c:when test="${result_attr=='success'}">
					<c:out value="${aircraft_type_add_ok}" />
				</c:when>
				<c:when test="${result_attr=='fail'}">
					<c:out value="${aircraft_type_add_fail}" />
				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>
</body>
</html>