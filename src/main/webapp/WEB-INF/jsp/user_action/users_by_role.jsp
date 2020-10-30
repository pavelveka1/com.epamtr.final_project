<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users by role</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="GET_FLIGHTS_BY_USER">
		<input type="hidden" name="form" value="filled"> <label>
			Choose role</label> <select name="role">
			<option>PILOT</option>
			<option>FLIGHT ATTENDANT</option>
			<option>ENGINEER</option>
		</select> <input type="submit" value="Find" /> <br> <br> <br>
		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="GET_FLIGHTS_BY_USER">
			<c:choose>
				<c:when test="${selected_role!=null}">
					<table border="1">
						<tr>
							<th>Name</th>
							<th>Surname</th>
							<th>Patronimic</th>
							<th>Email</th>
							<th>Role</th>
							<th>Choose</th>
						</tr>
						<c:forEach var="user_item" items="${users_by_role}">
							<tr>
								<td><c:out value="${user_item.name}" /></td>
								<td><c:out value="${user_item.surname}" /></td>
								<td><c:out value="${user_item.patronimic}" /></td>
								<td><c:out value="${user_item.email}" /></td>
								<td><c:out value="${user_item.role}" /></td>
								<td><form action="Controller" method="POST">
										<input type="hidden" name="command"
											value="GET_FLIGHTS_BY_USER"> <input type="hidden"
											name="id_selected_user" value="${user_item.idUser}">
										<input type="submit" value="Show flights" />
									</form></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>
</body>
</html>