<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add flight</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="ADD_FLIGHT"> 
			 <input
			type="text" name="current_city" placeholder="Current city"> <input
			type="text" name="destination_city" placeholder="Destination city"> <label>  Flight range in km  </label> <input
			type="number" name="flight_range" > 
			<label>  Flight time in min  </label>
			 <input
			type="number" name="flight_time" >
			
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
		
		<input type="datetime-local" name="time_departure">
		
		
			 <br><br> <label>Flight status<label>
				<select name="flight_status">
					<option>STAFF RECRUITMENT</option>
					<option>CREATED</option>
					<option>COMPLETED</option>
			</select> 
			 <input
				type="submit" value="Add flight" />
	</form>
</body>
</html>