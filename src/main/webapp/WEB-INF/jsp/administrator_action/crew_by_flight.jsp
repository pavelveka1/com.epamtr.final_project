<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users by flight</title>
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
Error is not null
</c:when>
		<c:otherwise>
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

			<table border="1">
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>Patronimic</th>
					<th>Email</th>
					<th>Crew position</th>
					<th>Delete crew item</th>
				</tr>
				<c:forEach var="crew_item" items="${flight_team}">
					<tr>
						<td><c:out value="${crew_item.user.name}" /></td>
						<td><c:out value="${crew_item.user.surname}" /></td>
						<td><c:out value="${crew_item.user.patronimic}" /></td>
						<td><c:out value="${crew_item.user.email}" /></td>
						<td><c:out value="${crew_item.crewPosition}" /></td>
						<td>
							<form action="Controller" method="POST">
								<input type="hidden" name="command"
									value="DELETE_CREW_FROM_FLIGHT"> <input type="hidden"
									name="id_selected_user" value="${crew_item.user.idUser}">
								<input type="hidden" name="flight_id"
									value="${selected_flight.idFlight}"> <input
									type="submit" value="Delete ">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<br>

			<br>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_CREW_TO_FLIGHT">
				<input type="hidden" name="add_crew" value="add_crew_to_flight">
				<input type="hidden" name="flight_id"
					value="${selected_flight.idFlight}"> <input type="submit"
					value="Add crew item" />
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>