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
			type="text" name="current_city" placeholder="name"> <input
			type="text" name="destination_city" placeholder="surname"> <input
			type="text" name="flight_range" placeholder="patronimic"> <input
			type="text" name="flight_time" placeholder="email">
			<input
			type="text" name="time_departure" placeholder="email">
			<input
			type="text" name="aircraft_number" placeholder="email">
			<input
			type="text" name="aircraft_type" placeholder="email">
			 <br><br> <label>Flight status<label>
				<select name="status">
					<option>STAFF RECRUITMENT</option>
					<option>CREATED</option>
					<option>COMPLETED</option>
			</select> 
			<input type="text" name="login" placeholder="login"> <input
				type="text" name="password" placeholder="password"> <input
				type="submit" value="add user" />
	</form>
</body>
</html>