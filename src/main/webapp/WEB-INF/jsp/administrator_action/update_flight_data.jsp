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
		<input type="hidden" name="command" value="UPDATE_FLIGHT"> 
		<input type="hidden" name="flight_data" value="ready">
		<input type="hidden" name="flight_id" value="${selected_flight.idFlight}">
		<label>From </label>
			 <input
			type="text" name="current_city" placeholder="Current city" value="${selected_flight.currentCity}">
			<br><label>To </label> <input
			type="text" name="destination_city" placeholder="Destination city" value="${selected_flight.destinationCity}">
			<br>
			 <label>  Flight range in km  </label> <input
			type="number" name="flight_range" value="${selected_flight.flightRange}"> <br>
			<label>  Flight time in min  </label>
			 <input
			type="number" name="flight_time" value="${selected_flight.flightTime}" >
			
			 <br>
			 <label> Current aircraft <c:out value="${selected_flight.aircraftNumber}"/> 
			 </label>
			 <br>
			 <label>Choose aircraft  </label>
			  <select name="aircraft_numbers">
			<c:forEach var="type_item" items="${aircrafts}">
				<option>
					<c:out value="${type_item.registerNumber}" />
					<c:set var="id_iarcraft" scope="session"
						value="${type_item.idAircraft}" />
				</option>
			</c:forEach>
		</select> 
		<br>
		<label>Time departure: <c:out value="${selected_flight.timeDeparture}" /> </label><br>
		<input type="datetime-local" name="time_departure" placeholder="${selected_flight.timeDeparture}">
		
		
			 <br><br>
			 <label>Current flight status <c:out value="${selected_flight.status}" /> </label>
			 <br>
			  <label>Flight status<label>
				<select name="flight_status">
					<option>RECRUITMENT</option>
					<option>CREATED</option>
					<option>COMPLETED</option>
			</select> 
			<br>
			 <input
				type="submit" value="Apply" />
				
				<br> <br>
				
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