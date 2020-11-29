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
<fmt:message bundle="${loc}" key="flight.choose_status" var="choose_status" />
<fmt:message bundle="${loc}" key="flight.status.apply_status" var="apply_new_status" />
<fmt:message bundle="${loc}" key="flight.chose_status" var="shose_status" />
<fmt:message bundle="${loc}" key="airline.find" var="find_flights" />
</head>
<body>
	<br>
	<c:out value="${error}" />
	<br>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="DELETE_FLIGHT"> <label>
					<c:out value="${chose_status}"/></label> <select name="flight_status">
					<option>RECRUITMENT</option>
					<option>CREATED</option>
					<option>COMPLITED</option>
				</select> <input type="submit" value="${find_flights}" />

			</form>

			<c:choose>
				<c:when test="${flights!=null}">
					<br>
	Selected flight status: <c:out value="${selected_flight_status_attr}"></c:out>
					<br>

					<table border="1">
						<tr>
							<th> <c:out value="${current_city}"/></th>
							<th><c:out value="${destination_city}"/></th>
							<th><c:out value="${range}"/></th>
							<th><c:out value="${time}"/></th>
							<th><c:out value="${date}"/></th>
							<th><c:out value="${aircraft}"/></th>
							<th><c:out value="${reg_number}"/></th>
							<th><c:out value="${current_status}"/></th>
							<th><c:out value="${choose_status}"/></th>
							<th><c:out value="${apply_new_status}"/></th>
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
								<form action="Controller" method="POST">
									<input type="hidden" name="command"
										value="CHANGE_FLIGHT_STATUS">
									<td><select name="flight_new_status">
											<option>RECRUITMENT</option>
											<option>CREATED</option>
											<option>COMPLITED</option>
									</select></td>
									<td><input type="submit" value="${apply_new_status}" /> <input
										type="hidden" name="id_flight" value="${flight_item.idFlight}">
									</td>
								</form>
							</tr>
						</c:forEach>
					</table>
					<br>
				</c:when>
			</c:choose>
		
</body>
</html>