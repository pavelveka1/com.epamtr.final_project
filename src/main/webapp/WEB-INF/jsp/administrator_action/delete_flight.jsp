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
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="DELETE_FLIGHT"> <label>
					Choose flight status</label> <select name="flight_status">
					<option>RECRUITMENT</option>
					<option>CREATED</option>
					<option>COMPLITED</option>
				</select> <input type="submit" value="Find" />

			</form>

			<c:choose>
				<c:when test="${flights!=null}">
					<br>
	Selected flight status: <c:out value="${selected_flight_status_attr}"></c:out>
					<br>

					<table border="1">
						<tr>
							<th>Current city</th>
							<th>Destination city</th>
							<th>Flight range</th>
							<th>Flight time</th>
							<th>Date</th>
							<th>Aircraft</th>
							<th>Registr. number</th>
							<th>Current status</th>
							<th>Choose status</th>
							<th>Apply new status</th>
							<th>Delete flight</th>
							<th>Update flight</th>
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
									<td><input type="submit" value="Apply new status" /> <input
										type="hidden" name="id_flight" value="${flight_item.idFlight}">
									</td>
								</form>
								<td><form action="Controller" method="POST">
										<input type="hidden" name="command" value="DELETE_FLIGHT">
										<input type="hidden" name="id_flight"
											value="${flight_item.idFlight}"> <input
											type="submit" value="Delete flight" />
									</form></td>
								<td><form action="Controller" method="POST">
										<input type="hidden" name="command" value="UPDATE_FLIGHT">
										<input type="hidden" name="id_flight"
											value="${flight_item.idFlight}"> <input
											type="submit" value="Update flight" />
									</form></td>
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