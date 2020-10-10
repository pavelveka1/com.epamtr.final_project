<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users by role</title>
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
		</select> <input type="submit" value="Find" /> <br>
		<br>
		<br>
		<table>
			<c:forEach var="user_item" items="${users_by_role}">
				<tr>
					<td><c:out value="${user_item}" /></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>