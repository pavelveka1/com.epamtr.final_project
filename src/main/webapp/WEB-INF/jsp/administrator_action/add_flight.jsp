<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add flight</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="flight.add.ok" var="flight_add_ok" />
<fmt:message bundle="${loc}" key="flight.add.fail" var="flight_add_fail" />
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="ADD_FLIGHT">




		<table>
			<tr>
				<td><label>Current city </label></td>
				<td><input type="text" name="current_city"
					placeholder="Current city"></td>
			</tr>
			<tr>
				<td><label>Destination city</label></td>
				<td><input type="text" name="destination_city"
					placeholder="Destination city"></td>
			</tr>
			<tr>
				<td><label> Flight range in km </label></td>
				<td><input type="number" name="flight_range"></td>
			</tr>

			<tr>
				<td><label> Flight time in min </label></td>
				<td><input type="number" name="flight_time"></td>
			</tr>
			<tr>
				<td><label>Choose aircraft </label></td>
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
				<td><label>Choose date </label></td>
				<td><input type="datetime-local" name="time_departure">
				</td>
			</tr>
			<tr>
				<td><label>Flight status<label></td>
				<td><select name="flight_status">
						<option>RECRUITMENT</option>
						<option>CREATED</option>
						<option>COMPLETED</option>
				</select></td>
			</tr>
		</table>

		<br> <input type="submit" value="Add flight" />
	</form>
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