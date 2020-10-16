<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change aircraft status</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="CHANGE_STATUS_AIRCRAFT"> 
			<label>Choose aircraft  </label>
			 
			  <select name="register_number">
			<c:forEach var="type_item" items="${aircrafts}">
				<option>
					<c:out value="${type_item.registerNumber}" />
				</option>
			</c:forEach>
		</select> 
			 
			<label>   Choose aircraft status  </label>
			
			 <select name="aircraft_status">
				<option>MAINTENANCE</option>
				<option>SERVICEABLE</option>
				<option>UNSERVICEABLE</option>
		</select> 
			 <input
				type="submit" value="Apply" />
	</form>
</body>
</html>