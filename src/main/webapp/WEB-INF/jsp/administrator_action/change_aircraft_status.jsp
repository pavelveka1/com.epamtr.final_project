<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change aircraft status</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="aircraft.reg_number" var="reg_number" />
<fmt:message bundle="${loc}" key="aircraft.current_status" var="current_status" />
<fmt:message bundle="${loc}" key="aircraft.status" var="choose_status" />
<fmt:message bundle="${loc}" key="airline.apply" var="apply_status" />
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
</c:when>
		<c:otherwise>
			<div>
				<form action="Controller" method="POST">
					<input type="hidden" name="command" value="CHANGE_STATUS_AIRCRAFT">

					<table border="1">
						<tr>
							<th><c:out value="${reg_number}"/></th>
							<th><c:out value="${current_status}"/></th>
							<th><c:out value="${choose_status}"/></th>
							<th><c:out value="${apply_status}"/></th>
						</tr>

						<c:forEach var="type_item" items="${aircrafts}">
							<form action="Controller" method="POST">
								<input type="hidden" name="command"
									value="CHANGE_STATUS_AIRCRAFT"> <input type="hidden"
									name="id_aircraft" value="${type_item.idAircraft}">
								<tr>
									<td><c:out value="${type_item.registerNumber}" /></td>
									<td><c:out value="${type_item.status}" /></td>
									<td><select name="aircraft_status">
											<option>MAINTENANCE</option>
											<option>SERVICEABLE</option>
											<option>UNSERVICEABLE</option>
									</select></td>
									<td><input type="submit" value="${apply_status}"></td>
								</tr>

							</form>
						</c:forEach>
					</table>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>