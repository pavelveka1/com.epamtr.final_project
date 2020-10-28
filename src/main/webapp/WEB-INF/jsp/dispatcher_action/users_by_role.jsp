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
		<input type="hidden" name="command" value="GET_USERS_BY_ROLE">
		<input type="hidden" name="form" value="filled"> <label>
			Choose role</label> <select name="role">
			<option>MANAGER</option>
			<option>ADMINISTRATOR</option>
			<option>DISPATCHER</option>
			<option>PILOT</option>
			<option>FLIGHT ATTENDANT</option>
			<option>ENGINEER</option>
		</select> <input type="submit" value="Find" />
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
		
			<label>Current role: <c:out value="${current_role.role}" /></label>
			<br>
			<table border="1">
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>Patronimic</th>
					<th>Email</th>
					<th>Role</th>
				</tr>
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



</body>
</html>