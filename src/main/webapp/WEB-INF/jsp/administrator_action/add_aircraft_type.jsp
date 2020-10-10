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
		<input type="hidden" name="command" value="ADD_AIRCRAFT_TYPE"> 
			 <label>Enter new type of aircraft</label><br><input
			type="text" name="aircraft_type" placeholder="aircraft type"><br>
			<label>Enter range of flight</label><br>
			 <input
			type="number" name="range_flight" placeholder="range of flight"><br>
			<label>Enter number of passenger</label><br>
			 <input
			type="number" name="number_passengers" placeholder="passenger's number"> 
			 <input
				type="submit" value="add user" />
	</form>
</body>
</html>