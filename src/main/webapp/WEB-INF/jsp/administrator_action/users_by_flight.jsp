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
<fmt:message bundle="${loc}" key="flight.choose_status" var="choose_flight_status" />
<fmt:message bundle="${loc}" key="flight.current_city" var="current_city" />
<fmt:message bundle="${loc}" key="flight.destination_city" var="destination_city" />
<fmt:message bundle="${loc}" key="flight.range" var="range" />
<fmt:message bundle="${loc}" key="flight.time" var="time" />
<fmt:message bundle="${loc}" key="flight.date" var="date" />
<fmt:message bundle="${loc}" key="flight.current_status" var="current_status" />
<fmt:message bundle="${loc}" key="aircraft.aircraft" var="aircraft" />
<fmt:message bundle="${loc}" key="aircraft.reg_number" var="reg_number" />
<fmt:message bundle="${loc}" key="airline.find" var="find" />
<fmt:message bundle="${loc}" key="flight.show_crew" var="show_crew" />
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
</c:when>
		<c:otherwise>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_USERS_BY_FLIGHT_ID">
				<label> <c:out value="${choose_flight_status}"/></label> <select name="flight_status"
					value="${flight_status}">
					<option>RECRUITMENT</option>
					<option>CREATED</option>
					<option>COMPLITED</option>
				</select> <input type="submit" value="${find}" />

			</form>
			<br>
			<br>

			<c:choose>
				<c:when test="${flights!=null}">
					<table border="1">
						<tr>
							<th><c:out value="${current_city}"/></th>
							<th><c:out value="${destination_city}"/></th>
							<th><c:out value="${range}"/></th>
							<th><c:out value="${time}"/></th>
							<th><c:out value="${date}"/></th>
							<th><c:out value="${aircraft}"/></th>
							<th><c:out value="${reg_number}"/></th>
							<th><c:out value="${current_status}"/></th>
							<th><c:out value="${show_crew}"/></th>
						</tr>
						<c:forEach var="flight_item" items="${flights}">
							<tr>
								<td><c:out value="${flight_item.currentCity}" /></td>
								<td><c:out value="${flight_item.destinationCity}" /></td>
								<td><c:out value="${flight_item.flightRange}" /></td>
								<td><c:out value="${flight_item.flightTime}" /></td>
								<td><c:out value="${flight_item.timeDeparture}" /></td>
								<td><c:out value="${flight_item.aircraftType}" /></td>
								<td><c:out value="${flight_item.aircraftNumber}" /></td>
								<td><c:out value="${flight_item.status}" /></td>
								<td>
									<form action="Controller" method="POST">
										<input type="hidden" name="command"
											value="GET_USERS_BY_FLIGHT_ID"> <input type="hidden"
											name="id_flight" value="${flight_item.idFlight}"> <input
											type="submit" value="${show_crew}">
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