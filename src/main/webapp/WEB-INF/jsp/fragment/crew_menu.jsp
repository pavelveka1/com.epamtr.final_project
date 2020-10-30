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
<fmt:message bundle="${loc}" key="dispatcher.user.get_by_role" var="get_users_by_role_for_crew" />
<fmt:message bundle="${loc}" key="crew.show" var="show_crew" />
<fmt:message bundle="${loc}" key="flight.show" var="show_flight" />
<fmt:message bundle="${loc}" key="flight.get_by_user"
	var="get_flights_by_user" />

</head>
<body>
	<ul>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=GET_USERS_BY_ROLE"><c:out
						value="${get_users_by_role_for_crew}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=GET_USERS_BY_FLIGHT_ID"><c:out
						value="${show_crew}" /></a></li>
		<li><a
				href="http://localhost:8080/airline/Controller?action=Controller&command=GET_FLIGHTS_BY_USER"><c:out
						value="${get_flights_by_user}" /></a></li>
	</ul>
</body>
</html>