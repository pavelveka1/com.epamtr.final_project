<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add crew to flight</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="user.name" var="name" />
<fmt:message bundle="${loc}" key="user.surname" var="surname" />
<fmt:message bundle="${loc}" key="user.patronimic" var="patronimic" />
<fmt:message bundle="${loc}" key="user.email" var="email" />
<fmt:message bundle="${loc}" key="user.role" var="role" />
<fmt:message bundle="${loc}" key="flight.crew.choose_position"
	var="flight_choose_crew_position" />
<fmt:message bundle="${loc}" key="flight.crew.selected_position"
	var="flight_selected_crew_position" />
<fmt:message bundle="${loc}" key="airline.add" var="add_crew" />
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
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

			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_CREW_TO_FLIGHT">
				<input type="hidden" name="form" value="filled"> <label>
					<c:out value="${flight_choose_crew_position}" />
				</label> <select name="position">
					<c:forEach var="position_item" items="${free_positions}">
						<option><c:out value="${position_item.crewPosition}" />
						</option>
					</c:forEach>
				</select> <input type="submit" value="Find" /> <br> <br>
			</form>
			<c:choose>
				<c:when test="${selected_position!=null}">
					<label><c:out value="${flight_selected_crew_position}"></c:out>:
						<c:out value="${selected_position}" /></label>
					<br>
					<table border="1">
						<tr>
							<th><c:out value="${name}" /></th>
							<th><c:out value="${surname}" /></th>
							<th><c:out value="${patronimic}" /></th>
							<th><c:out value="${email}" /></th>
							<th><c:out value="${role}" /></th>
							<th><c:out value="${add_crew}" /></th>
						</tr>
						<c:forEach var="user_item" items="${free_users_by_position}">
							<tr>
								<td><c:out value="${user_item.name}" /></td>
								<td><c:out value="${user_item.surname}" /></td>
								<td><c:out value="${user_item.patronimic}" /></td>
								<td><c:out value="${user_item.email}" /></td>
								<td><c:out value="${user_item.role.role}" /></td>
								<td>
									<form action="Controller" method="POST">
										<input type="hidden" name="command" value="ADD_CREW_TO_FLIGHT">
										<input type="hidden" name="selected_user"
											value="${user_item.idUser}"> <input type="hidden"
											name="flight_id" /> <input type="submit" value="${add_crew}">
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>


				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>

</body>
</html>