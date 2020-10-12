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
		<input type="hidden" name="command" value="DELETE_AIRCRAFT_TYPE"> 
		<input type="hidden" name="delete" value="delete">
			 <label>Choose type of aircraft</label><br>
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
				type="submit" value="Delete" />
	</form>
</body>
</html>