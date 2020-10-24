<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show flights</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="GET_FLIGHTS_BY_STATUS">
		<label> Choose flight status</label> <select name="flight_status"
			value="${flight_status}">
			<option>RECRUITMENT</option>
			<option>CREATED</option>
			<option>COMPLITED</option>
		</select> <input type="submit" value="Find" />

	</form>
	<br>
	<c:choose>
		<c:when test="${selected_status!=null}">
			<table>
				<tr>
					<th><label>Selected status: </label></th>
					<th><c:out value="${selected_status}" /></th>
				</tr>
			</table>
			<br>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_FLIGHTS_BY_STATUS">



				<table border="1">
					<tr>
						<th>Current city</th>
						<th>Destination city</th>
						<th>Flight range</th>
						<th>Flight time</th>
						<th>Date</th>
						<th>Aircraft</th>
						<th>Registr. number</th>
						<th>Status</th>
						<th>Choose</th>
					</tr>
					<c:forEach var="flight_item" items="${flights}">
						<tr>
							<td><c:out value="${flight_item.currentCity}" /></td>
							<td><c:out value="${flight_item.destinationCity}" /></td>
							<td><c:out value="${flight_item.flightRange}" /></td>
							<td><c:out value="${flight_item.flightTime}" /></td>
							<td><c:out value="${flight_item.timeDeparture}" /></td>
							<td><c:out value="${flight_item.aircraftType}" /></td>
							<td><c:out value="${flight_item.aircraftNumber}" /></td>
							<td><c:out value="${flight_item.status}" /></td>
							<td><input type=radio name="radio_id_flight"
								value="${flight_item.idFlight}"></td>
						</tr>
					</c:forEach>
				</table>

				<input type="submit" value="Find crew" />
			</form>
		</c:when>
	</c:choose>


</body>
</html>