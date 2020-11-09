<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="aircraft.add.ok" var="aircraft_add_ok" />
<fmt:message bundle="${loc}" key="aircraft.add.fail"
	var="aircraft_add_fail" />
<fmt:message bundle="${loc}" key="aircraft.reg.number.not_valid"
	var="registration_number_not_valid" />
<meta charset="UTF-8">
<title>Add aircraft</title>
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
Error is not null
</c:when>
		<c:otherwise>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_AIRCRAFT">

				<table>
					<tr>
						<td><label>Choose type of aircraft </label></td>
						<td><select name="aircraft_types">
								<c:forEach var="type_item" items="${aircraftTypes}">
									<option>
										<c:out value="${type_item.aircraftType}" />
										<c:set var="id_iarcraft_type" scope="session"
											value="${type_item.idAircraftType }" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Enter registration number</label></td>
						<td><input type="text" name="register_number"
							placeholder="registration number"> <c:choose>
								<c:when test="${data_is_valid==false}">
									<c:out value="${registration_number_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label> Choose aircraft status </label></td>
						<td><select name="aircraft_status">
								<option>MAINTENANCE</option>
								<option>SERVICEABLE</option>
								<option>UNSERVICEABLE</option>
						</select></td>
					</tr>
				</table>
				<br> <input type="submit" value="add aircraft" />
			</form>
			<br>
			<c:choose>
				<c:when test="${result_attr=='success'}">
					<c:out value="${aircraft_add_ok}" />
				</c:when>
				<c:when test="${result_attr=='fail'}">
					<c:out value="${aircraft_add_fail}" />
				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>

</body>
</html>