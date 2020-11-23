<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users by role</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="user.choose_role" var="choose_role" />
<fmt:message bundle="${loc}" key="airline.find" var="find" />
<fmt:message bundle="${loc}" key="user.name" var="name" />
<fmt:message bundle="${loc}" key="user.surname" var="surname" />
<fmt:message bundle="${loc}" key="user.patronimic" var="patronimic" />
<fmt:message bundle="${loc}" key="user.email" var="email" />
<fmt:message bundle="${loc}" key="user.role" var="user_role" />
<fmt:message bundle="${loc}" key="airline.delete" var="delete_user" />
<fmt:message bundle="${loc}" key="airline.update" var="update_user" />
<fmt:message bundle="${loc}" key="user.chose_role" var="chose_role" />
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
</c:when>
		<c:otherwise>

			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_USERS_BY_ROLE">
				<input type="hidden" name="form" value="filled"> <label>
					<c:out value="${choose_role}"/></label> <select name="role">
					<option>MANAGER</option>
					<option>ADMINISTRATOR</option>
					<option>DISPATCHER</option>
					<option>PILOT</option>
					<option>ATTENDANT</option>
					<option>ENGINEER</option>
				</select> <input type="submit" value="${find}" />
			</form>
			<br>
			<br>
			<c:choose>
				<c:when test="${deleted_user=='success'}">
					<c:out value="${deleted_user_ok}" />
				</c:when>
				<c:when test="${deleted_user=='fail'}">
					<c:out value="${deleted_user_fail}" />
				</c:when>
			</c:choose>

			<c:choose>
				<c:when test="${current_role!=null}">

					<label><c:out value="${chose_role}"></c:out>: <c:out value="${current_role.role}" /></label>
					<br>
					<table border="1">
						<tr>
							<th><c:out value="${name}"/></th>
							<th><c:out value="${surname}"/></th>
							<th><c:out value="${patronimic}"/></th>
							<th><c:out value="${email}"/></th>
							<th><c:out value="${user_role}"/></th>
							<th><c:out value="${delete_user}"/></th>
							<th><c:out value="${update_user}"/></th>
						</tr>
						<c:forEach var="user_item" items="${users_by_role}">

							<tr>
								<td><c:out value="${user_item.name}" /></td>
								<td><c:out value="${user_item.surname}" /></td>
								<td><c:out value="${user_item.patronimic}" /></td>
								<td><c:out value="${user_item.email}" /></td>
								<td><c:out value="${user_item.role}" /></td>
								<td><form action="Controller" method="POST">
										<input type="hidden" name="command" value="DELITE_USER">
										<input type="hidden" name="id_user"
											value="${user_item.idUser}"> <input type="submit"
											value="${delete_user}">
									</form></td>
								<td><form action="Controller" method="POST">
										<input type="hidden" name="command" value="UPDATE_USER">
										<input type="hidden" name="id_user"
											value="${user_item.idUser}"> <input type="submit"
											value="${update_user}">
									</form></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>

		</c:otherwise>
	</c:choose>

</body>
</html>