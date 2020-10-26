<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="deleted.user.ok" var="deleted_user_ok" />
<fmt:message bundle="${loc}" key="deleted.user.fail"
	var="deleted_user_fail" />
<title>Delete user</title>
</head>
<body>


	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="GET_USERS_BY_ROLE">
		<input type="hidden" name="form" value="filled"> <label>
			Choose role</label> <select name="role">
			<option>MANAGER</option>
			<option>ADMINISTRATOR</option>
			<option>DISPATCHER</option>
			<option>PILOT</option>
			<option>FLIGHT ATTENDANT</option>
			<option>ENGINEER</option>
		</select> <input type="submit" value="Find" /> <br> <br>
		<c:choose>
			<c:when test="${current_role!=null}">

				<label>Current role: <c:out value="${current_role.role}" /></label>
				<br>
				<table border="1">
					<tr>
						<th>Name</th>
						<th>Surname</th>
						<th>Patronimic</th>
						<th>Email</th>
						<th>Role</th>
						<th>Delete</th>
						<th>Update</th>
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
									<input type="hidden" name="id_user" value="${user_item.idUser}">
									<input type="submit" value="Delete">
								</form></td>
							<td><form action="Controller" method="POST">
									<input type="hidden" name="command" value="UPDATE_USER">
									<input type="hidden" name="id_user" value="${user_item.idUser}">
									<input type="submit" value="Update">
								</form></td>
						</tr>


					</c:forEach>



					<c:forEach var="user_item" items="${users_by_role}">
						<tr>
							<td><c:out value="${user_item.name}" /></td>
							<td><c:out value="${user_item.surname}" /></td>
							<td><c:out value="${user_item.patronimic}" /></td>
							<td><c:out value="${user_item.email}" /></td>
							<td><c:out value="${user_item.role}" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>






		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="DELITE_USER"> <input
				type="text" name="login" placeholder="Login name"> <input
				type="submit" value="delite user" />
		</form>
		<br> <br>
		<c:choose>
			<c:when test="${deleted_user=='success'}">
				<c:out value="${deleted_user_ok}" />
			</c:when>
			<c:when test="${deleted_user=='fail'}">
				<c:out value="${deleted_user_fail}" />
			</c:when>
		</c:choose>
</body>
</html>