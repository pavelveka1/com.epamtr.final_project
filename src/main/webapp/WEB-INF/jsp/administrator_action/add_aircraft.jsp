<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add aircraft</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="ADD_AIRCRAFT"> 
			 <label>Choose type of aircraft  </label>
			 
			  <select name="aircraft_types">
			<c:forEach var="type_item" items="${aircraftTypes}">
				<option>
					<c:out value="${type_item.aircraftType}" />
					<c:set var="id_iarcraft_type" scope="session"
						value="${type_item.idAircraftType }" />
				</option>
			</c:forEach>
		</select> 
			 
			 <input
			type="text" name="register_number" placeholder="registration number">
			
			<label>   Choose aircraft status  </label>
			
			 <select name="aircraft_status">
				<option>MAINTENANCE</option>
				<option>SERVICEABLE</option>
				<option>UNSERVICEABLE</option>
		</select> 
			 <input
				type="submit" value="add aircraft" />
	</form>
</body>
</html>