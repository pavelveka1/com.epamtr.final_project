<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users by flight</title>
</head>
<body>
	<strong> Flight: </strong>
	<table>
		<tr>
			<th><c:out value="${selected_flight.currentCity}" /></th>
			<th><c:out value="${selected_flight.destinationCity}" /></th>
			<th><c:out value="${selected_flight.timeDeparture}" /></th>
			<th><c:out value="${selected_flight.aircraftType}" /></th>
			<th><c:out value="${selected_flight.aircraftNumber}" /></th>
			<th><c:out value="${selected_flight.status}" /></th>
		</tr>
	</table>
	<br>
	<br>
	
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="GET_USERS_BY_ROLE">
		<input type="hidden" name="form" value="filled"> <label>
			Choose role</label> <select name="role">
			<option>MANAGER</option>
			<option>ADMINISTRATOR</option>
			<option>DISPATCHER</option>
			<option>PILOT</option>
			<option>FLIGHT ATTENDANT</option>
			<option>ENGINEER</option>
		</select> <input type="submit" value="Find" /> <br>
		<br>
		<br>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Surname</th>
			<th>Patronimic</th>
			<th>Email</th>
			<th>Role</th>
			<th>Choose</th>
		</tr>
			<c:forEach var="user_item" items="${users_by_role}">
				<td><c:out value="${users_item.name}" /></td>
				<td><c:out value="${users_item.surname}" /></td>
				<td><c:out value="${users_item.patronimic}" /></td>
				<td><c:out value="${users_item.email}" /></td>
				<td><c:out value="${users_item.role}" /></td>
				<td><select>
				<c:forEach var="position_item" items="${positions_by_role}">
				<option> <c:out value="${position_item.crewPosition}" /></option>
				</c:forEach>
				</select></td>
			</c:forEach>
		</table>

<form action="Controller" method="POST">
	<input type="hidden" name="command" value="DELETE_CREW_FROM_FLIGHT"> 
	<input type= "hidden" name="flight_id" value="${selected_flight.idFlight}">
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Surname</th>
			<th>Patronimic</th>
			<th>Email</th>
			<th>Crew position</th>
			<th>Choose</th>
		</tr>
		<c:forEach var="crew_item" items="${flight_team}">
			<tr>
				<td><c:out value="${crew_item.user.name}" /></td>
				<td><c:out value="${crew_item.user.surname}" /></td>
				<td><c:out value="${crew_item.user.patronimic}" /></td>
				<td><c:out value="${crew_item.user.email}" /></td>
				<td><c:out value="${crew_item.crewPosition}" /></td>
				<td><input type=radio name="radio_id_crew"
					value="${crew_item.user.idUser}"></td>
			</tr>
		</c:forEach>
	</table>
	<br>
		<input type="submit" value="Delete crew item" />
	</form>
	<br>
	<form action="Controller" method="POST">
	<input type="hidden" name="command" value="ADD_CREW_TO_FLIGHT"> 
	<input type="hidden" name="flight_id" value="${selected_flight.idFlight}">
		<input type="submit" value="Add crew item" />
	</form>
</body>
</html>