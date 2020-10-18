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
			<option>MANAGER</option>
			<option>ADMINISTRATOR</option>
			<option>DISPATCHER</option>
			<option>PILOT</option>
			<option>FLIGHT ATTENDANT</option>
			<option>ENGINEER</option>
		</select> <input type="submit" value="Find" /> <br>
		<br>
		<br>
		<form action="Controller" method="POST">
		<input type="hidden" name="command" value="GET_FLIGHTS_BY_USER">
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
					<td><input type=radio name="radio_id_user" value="${user_item.idUser}"  > </td>
				</tr>
			</c:forEach>
		</table>
		</select> <input type="submit" value="Show flights" /> <br>
		</form>
</body>
</html>