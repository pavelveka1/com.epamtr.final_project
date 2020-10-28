<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add crew to flight</title>
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
		<input type="hidden" name="command" value="ADD_CREW_TO_FLIGHT"> 
		<input type="hidden" name="form" value="filled"> 
		<label>
			Choose position</label> <select name="position">
			<c:forEach var="position_item" items="${free_positions}">
				<option><c:out value="${position_item.crewPosition}" />
				</option>
			</c:forEach>
		</select>
		
		 <input type="submit" value="Find" /> <br> <br> 
		 
		 <c:choose>
		<c:when test="${selected_position!=null}">
		<label>Selected position: <c:out value="${selected_position}" /></label>
		 <br>
			<br>
				value="${selected_flight.idFlight}">
			<table border="1">
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>Patronimic</th>
					<th>Email</th>
					<th>Role</th>
					<th>Choose</th>
				</tr>
				<c:forEach var="user_item" items="${free_users_by_position}">
				<tr>
					<td><c:out value="${user_item.name}" /></td>
					<td><c:out value="${user_item.surname}" /></td>
					<td><c:out value="${user_item.patronimic}" /></td>
					<td><c:out value="${user_item.email}" /></td>
					<td><c:out value="${user_item.role.role}" /></td>
					<td>   <form action="Controller" method="POST">
								<input type="hidden" name="command" value="ADD_CREW_TO_FLIGHT">
								<input type="hidden" name="selected_user" value="${user_item.idUser}">
								<input type="hidden" name="flight_id"/>
								<input type="submit" value="Add to flight ">
							</form>   </td>
					</tr>
				</c:forEach>
			</table>
		
		
		</c:when>
	</c:choose>
		 
		 
</body>
</html>