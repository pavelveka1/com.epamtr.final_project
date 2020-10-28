<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="user.add" var="add_user" />
<fmt:message bundle="${loc}" key="user.delete" var="delete_user" />
<fmt:message bundle="${loc}" key="user.update" var="update_user" />
<fmt:message bundle="${loc}" key="user.get_by_role" var="get_users_by_role" />
<fmt:message bundle="${loc}" key="user.get_by_flight_id" var="get_users_by_flight_id" />
<fmt:message bundle="${loc}" key="aircraft.type.add" var="add_aircraft_type" />
<fmt:message bundle="${loc}" key="aircraft.type.delete" var="delete_aircraft_type" />

<fmt:message bundle="${loc}" key="aircraft.add" var="add_aircraft" />
<fmt:message bundle="${loc}" key="aircraft.delete" var="delete_aircraft" />
<fmt:message bundle="${loc}" key="aircraft.update" var="update_aircraft" />
<fmt:message bundle="${loc}" key="aircraft.status.change"
	var="change_status_aircraft" />
<fmt:message bundle="${loc}" key="flight.add" var="add_flight" />
<fmt:message bundle="${loc}" key="flight.action" var="action_flight" />
<fmt:message bundle="${loc}" key="flight.get_by_user"
	var="get_flights_by_user" />

</head>
<body>
	<ul>
	<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=ADD_USER"><c:out
						value="${add_user}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=GET_USERS_BY_ROLE"><c:out
						value="${get_users_by_role}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=GET_USERS_BY_FLIGHT_ID"><c:out
						value="${get_users_by_flight_id}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=ADD_AIRCRAFT_TYPE"><c:out
						value="${add_aircraft_type}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=DELETE_AIRCRAFT_TYPE"><c:out
						value="${delete_aircraft_type}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=ADD_AIRCRAFT"><c:out
						value="${add_aircraft}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=DELETE_AIRCRAFT"><c:out
						value="${delete_aircraft}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=UPDATE_AIRCRAFT"><c:out
						value="${update_aircraft}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=CHANGE_STATUS_AIRCRAFT"><c:out
						value="${change_status_aircraft}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=ADD_FLIGHT"><c:out
						value="${add_flight}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=DELETE_FLIGHT"><c:out
						value="${action_flight}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=GET_FLIGHTS_BY_USER"><c:out
						value="${get_flights_by_user}" /></a></li>
	</ul>
</body>
</html>