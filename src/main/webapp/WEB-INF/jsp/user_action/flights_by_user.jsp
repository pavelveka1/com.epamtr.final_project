<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flights by user</title>
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
</head>
<body>
	<br>
	<c:out value="${error}" />
	<br>
			<strong> <c:out value="${selected_user.role}" /> :
			</strong>
			<table>
				<tr>
					<th><c:out value="${selected_user.name}" /></th>
					<th><c:out value="${selected_user.surname}" /></th>
					<th><c:out value="${selected_user.patronimic}" /></th>

				</tr>
			</table>
			<br>
			<c:choose>
				<c:when test="${selected_user!=null}">

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
						</tr>
						<c:forEach var="flight" items="${flights}">
							<tr>
								<td><c:out value="${flight.currentCity}" /></td>
								<td><c:out value="${flight.destinationCity}" /></td>
								<td><c:out value="${flight.flightRange}" /></td>
								<td><c:out value="${flight.flightTime}" /></td>
								<td><c:out value="${flight.timeDeparture}" /></td>
								<td><c:out value="${flight.aircraftType}" /></td>
								<td><c:out value="${flight.aircraftNumber}" /></td>
								<td><c:out value="${flight.status}" /></td>
							</tr>
						</c:forEach>
					</table>
					<br>
				</c:when>
			</c:choose>
		
</body>
</html>