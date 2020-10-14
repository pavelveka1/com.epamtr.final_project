<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show flights</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="GET_FLIGHTS_BY_STATUS">
		<label>Choose flight status <label> <select
				name="flight_status">
					<option>STAFF RECRUITMENT</option>
					<option>CREATED</option>
					<option>COMPLETED</option>
			</select> <input type="submit" value="Show flights" />
	</form>
	<br><br>
			<div>
			<label>Flights</label> 
			<br><br>
			<select name="flights">
			<c:forEach var="type_item" items="${flights}">
			<hr>
				<option> From: 
					<c:out value="${type_item.currentCity}" />
					To: 
					<c:out value="${type_item.destinationCity}" />
					Aircraft: 
					<c:out value="${type_item.aircraftType}" />
					<c:out value="${type_item.aircraftNumber}" />
					Flight range: 
					<c:out value="${type_item.flightRange}" />
					Flight time: 
					<c:out value="${type_item.flightTime}" />
					Time departure: 
					<c:out value="${type_item.timeDeparture}" />
					Status: 
					<c:out value="${type_item.status}" />	
				</option>
			</c:forEach>
		</select>
			</div>
			
</body>
</html>