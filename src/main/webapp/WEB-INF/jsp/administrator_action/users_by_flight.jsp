<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users by flight</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="GET_USERS_BY_FLIGHT_ID">
		<label> Choose flight status</label>
		 <select name="flight_status">
			<option>STAFF RECRUITMENT</option>
			<option>CREATED</option>
			<option>COMPLETED</option>
		</select> 
		<input type="submit" value="Find" />

	</form>
	<br>
	<br>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="GET_USERS_BY_FLIGHT_ID">
		<select name="flights">
			<c:forEach var="flight_item" items="${flights}">
				<option>
					<c:out value="${flight_item}" />
					<c:set var="id_flight" scope="session"
						value="${flight_item.idFlight }" />
				</option>
			</c:forEach>
		</select> <input type="submit" value="Find" />
	</form>
	<br>
	<br>
	<table>
		<c:forEach var="user_item" items="${users_by_flight}">
			<tr>
				<td><c:out value="${user_item}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>