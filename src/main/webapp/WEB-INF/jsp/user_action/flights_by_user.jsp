<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flights by user</title>
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
Error is not null
</c:when>
		<c:otherwise>
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
							<th>Current city</th>
							<th>Destination city</th>
							<th>Flight range</th>
							<th>Flight time</th>
							<th>Date</th>
							<th>Aircraft type</th>
							<th>Aircraft number</th>
							<th>Status</th>
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
		</c:otherwise>
	</c:choose>
</body>
</html>