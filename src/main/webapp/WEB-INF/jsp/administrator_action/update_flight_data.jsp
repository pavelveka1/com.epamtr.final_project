<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update flight data</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="UPDATE_FLIGHT"> <input
			type="hidden" name="flight_data" value="ready"> <input
			type="hidden" name="flight_id" value="${selected_flight.idFlight}">


		<table>
			<tr>
				<td><label>Current city </label></td>
				<td><input type="text" name="current_city"
					placeholder="Current city" value="${selected_flight.currentCity}"></td>
			</tr>
			<tr>
				<td><label>Destination city</label></td>
				<td><input type="text" name="destination_city"
					placeholder="Destination city"
					value="${selected_flight.destinationCity}"></td>
			</tr>
			<tr>
				<td><label> Flight range in km </label></td>
				<td><input type="number" name="flight_range"
					value="${selected_flight.flightRange}"></td>
			</tr>

			<tr>
				<td><label> Flight time in min </label></td>
				<td><input type="number" name="flight_time"
					value="${selected_flight.flightTime}"></td>
			</tr>
			<tr>
				<td><label>Current aircraft </label></td>
				<td><c:out value="${selected_flight.aircraftNumber}" /></td>
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
				<td><label>Time departure: </label></td>
				<td><c:out value="${selected_flight.timeDeparture}" /></td>
			</tr>
			<tr>
				<td><label>Choose departure: </label></td>
				<td><input type="datetime-local" name="time_departure"
					placeholder="${selected_flight.timeDeparture}"></td>
			</tr>
			<tr>
				<td><label>Current flight status </label></td>
				<td><c:out value="${selected_flight.status}" /></td>
			</tr>
			<tr>
				<td><label>Choose flight status<label></td>
				<td><select name="flight_status">
						<option>RECRUITMENT</option>
						<option>CREATED</option>
						<option>COMPLITED</option>
				</select></td>
			</tr>
		</table>

		<br> <input type="submit" value="Apply" /> <br> <br>

		<table>
			<tr>
				<th><c:out value="${selected_flight.currentCity}" /></th>
				<th><c:out value="${selected_flight.destinationCity}" /></th>
				<th><c:out value="${selected_flight.timeDeparture}" /></th>
				<th><c:out value="${selected_flight.flightRange}" /></th>
				<th><c:out value="${selected_flight.flightTime}" /></th>
				<th><c:out value="${selected_flight.aircraftType}" /></th>
				<th><c:out value="${selected_flight.aircraftNumber}" /></th>
				<th><c:out value="${selected_flight.status}" /></th>
			</tr>
		</table>

	</form>
</body>
</html>