<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users by flight</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="user.name" var="name" />
<fmt:message bundle="${loc}" key="user.surname" var="surname" />
<fmt:message bundle="${loc}" key="user.patronimic" var="patronimic" />
<fmt:message bundle="${loc}" key="user.email" var="email" />
<fmt:message bundle="${loc}" key="flight.crew_position"
	var="crew_position_for_user" />
<fmt:message bundle="${loc}" key="airline.add" var="add_crew" />
<fmt:message bundle="${loc}" key="airline.delete" var="delete_crew" />
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

	<table border="1">
		<tr>
			<th><c:out value="${name}" /></th>
			<th><c:out value="${surname}" /></th>
			<th><c:out value="${patronimic}" /></th>
			<th><c:out value="${email}" /></th>
			<th><c:out value="${crew_position_for_user}" /></th>
			<th><c:out value="${delete_crew}" /></th>
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
							value="${selected_flight.idFlight}"> <input type="submit"
							value="${delete_crew}">
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
			value="${add_crew}" />
	</form>

	<br>
	<c:out value="${error}" />
	<br>
</body>
</html>