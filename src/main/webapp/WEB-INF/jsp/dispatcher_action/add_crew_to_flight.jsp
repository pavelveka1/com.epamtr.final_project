<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users by role</title>
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
		</c:when>
		<c:otherwise>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_USERS_BY_FLIGHT_ID">
				<input type="hidden" name="form" value="filled"> <label>
					Choose role</label> <select name="role">
					<option>PILOT</option>
					<option>ATTENDANT</option>
					<option>ENGINEER</option>
				</select> <input type="submit" value="Find" />
			</form>
			<br>
			<br>
			<br>
			<div style="overflow: scroll">
				<form action="Controller" method="POST">
					<input type="hidden" name="command" value="GET_USERS_BY_FLIGHT_ID">
					<input type="submit" value="Apply" /> <br>
					<table border="1">
						<tr>
							<th>Name</th>
							<th>Surname</th>
							<th>Patronimic</th>
							<th>Email</th>
							<th>Role</th>
							<th>Position</th>
							<th>Choose</th>
						</tr>
						<c:forEach var="user_item" items="${users_by_role}">
							<tr>
								<td><c:out value="${user_item.name}" /></td>
								<td><c:out value="${user_item.surname}" /></td>
								<td><c:out value="${user_item.patronimic}" /></td>
								<td><c:out value="${user_item.email}" /></td>
								<td><c:out value="${user_item.role}" /></td>
								<td><select name="role">
										<c:forEach var="position" items="${positions}">
											<option>
												<c:out value="${position.crewPosition}" />
											</option>
										</c:forEach>
								</select></td>
								<td><input type=radio name="radio_id_user"
									value="${user_item.idUser}"></td>
							</tr>
						</c:forEach>
					</table>
					</select> <br>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>