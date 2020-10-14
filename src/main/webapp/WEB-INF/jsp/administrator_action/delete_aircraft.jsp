<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete aircraft</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="DELETE_AIRCRAFT"> 
		<input type="hidden" name="delete_aircraft" value="DELETE_AIRCRAFT"> 
			 <label>Choose aircraft  </label>
			 
			  <select name="aircraft_numbers">
			<c:forEach var="type_item" items="${aircrafts}}">
				<option>
					<c:out value="${type_item.registrationNumber}" />
				</option>
			</c:forEach>
		</select> 
			 
			 <input
				type="submit" value="delete aircraft" />
	</form>
</body>
</html>