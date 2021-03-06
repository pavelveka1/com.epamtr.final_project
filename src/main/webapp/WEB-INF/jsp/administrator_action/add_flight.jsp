<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add flight</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="flight.add.ok" var="flight_add_ok" />
<fmt:message bundle="${loc}" key="flight.add.fail" var="flight_add_fail" />
<fmt:message bundle="${loc}" key="flight.current_city.not_valid"
	var="current_city_not_valid" />
<fmt:message bundle="${loc}" key="flight.destination_city.not_valid"
	var="destination_city_not_valid" />
<fmt:message bundle="${loc}" key="flight.flight_range.not_valid"
	var="flight_range_not_valid" />
<fmt:message bundle="${loc}" key="flight.flight_time.not_valid"
	var="flight_time_not_valid" />
<fmt:message bundle="${loc}" key="flight.time_departure.not_valid"
	var="time_daparture_not_valid" />
<fmt:message bundle="${loc}" key="flight.add_flight" var="add_flight" />
<fmt:message bundle="${loc}" key="flight.current_city"
	var="current_city" />
<fmt:message bundle="${loc}" key="flight.destination_city"
	var="destination_city" />
<fmt:message bundle="${loc}" key="flight.range" var="flight_range" />
<fmt:message bundle="${loc}" key="flight.time" var="flight_time" />
<fmt:message bundle="${loc}" key="flight.choose_aircraft"
	var="choose_aircraft" />
<fmt:message bundle="${loc}" key="flight.choose_date" var="choose_date" />
<fmt:message bundle="${loc}" key="flight.status" var="flight_status" />

</head>
<body>

	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="ADD_FLIGHT">




		<table>
			<tr>
				<td><label><c:out value="${current_city}" /> </label></td>
				<td><input type="text" name="current_city"
					placeholder="Current city"> <c:choose>
						<c:when test="${current_city_valid==false}">
							<c:out value="${current_city_not_valid}" />
						</c:when>
					</c:choose></td>
			</tr>
			<tr>
				<td><label><c:out value="${destination_city}" /> </label></td>
				<td><input type="text" name="destination_city"
					placeholder="Destination city"> <c:choose>
						<c:when test="${destination_city_valid==false}">
							<c:out value="${destination_city_not_valid}" />
						</c:when>
					</c:choose></td>
			</tr>
			<tr>
				<td><label> <c:out value="${flight_range}" />
				</label></td>
				<td><input type="number" name="flight_range"> <c:choose>
						<c:when test="${flight_range_valid==false}">
							<c:out value="${flight_range_not_valid}" />
						</c:when>
					</c:choose></td>
			</tr>

			<tr>
				<td><label><c:out value="${flight_time}" /> </label></td>
				<td><input type="number" name="flight_time"> <c:choose>
						<c:when test="${flight_time_valid==false}">
							<c:out value="${flight_time_not_valid}" />
						</c:when>
					</c:choose></td>
			</tr>
			<tr>
				<td><label><c:out value="${choose_aircraft}" /> </label></td>
				<td><select name="aircraft_numbers">
						<c:forEach var="type_item" items="${aircrafts}">
							<option>
								<c:out value="${type_item.registerNumber}" />
								<c:set var="id_iarcraft" scope="session"
									value="${type_item.idAircraft}" />
							</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><label><c:out value="${choose_date}" /> </label></td>
				<td><input type="datetime-local" name="time_departure" /> <c:choose>
						<c:when test="${time_daparture_valid==false}">
							<c:out value="${time_daparture_not_valid}" />
						</c:when>
					</c:choose></td>
			</tr>
			<tr>
				<td><label><c:out value="${flight_status}" /> </label></td>
				<td><select name="flight_status">
						<option>RECRUITMENT</option>
						<option>CREATED</option>
						<option>COMPLETED</option>
				</select></td>
			</tr>
		</table>

		<br> <input type="submit" value="${add_flight}" />
	</form>
	<br>
	<c:out value="${error}" />
	<br>
	<br>
	<c:choose>
		<c:when test="${result_attr=='success'}">
			<c:out value="${flight_add_ok}" />
		</c:when>
		<c:when test="${result_attr=='fail'}">
			<c:out value="${flight_add_fail}" />
		</c:when>
	</c:choose>
</body>
</html>