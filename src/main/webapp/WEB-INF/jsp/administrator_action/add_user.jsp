<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adding new user</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="user.add.ok" var="user_add_ok" />
<fmt:message bundle="${loc}" key="user.add.fail" var="user_add_fail" />
<fmt:message bundle="${loc}" key="user.name.not_valid"
	var="name_not_valid" />
<fmt:message bundle="${loc}" key="user.surname.not_valid"
	var="surname_not_valid" />
<fmt:message bundle="${loc}" key="user.patronimic.not_valid"
	var="patronimic_not_valid" />
<fmt:message bundle="${loc}" key="user.email.not_valid"
	var="email_not_valid" />
<fmt:message bundle="${loc}" key="user.login.not_valid"
	var="login_not_valid" />
<fmt:message bundle="${loc}" key="user.password.not_valid"
	var="password_not_valid" />
<fmt:message bundle="${loc}" key="user.name" var="name" />
<fmt:message bundle="${loc}" key="user.surname" var="surname" />
<fmt:message bundle="${loc}" key="user.patronimic" var="patronimic" />
<fmt:message bundle="${loc}" key="user.email" var="email" />
<fmt:message bundle="${loc}" key="user.role" var="role" />
<fmt:message bundle="${loc}" key="user.login" var="login" />
<fmt:message bundle="${loc}" key="user.password" var="password" />
<fmt:message bundle="${loc}" key="user.add_user" var="add_user" />
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
		</c:when>
		<c:otherwise>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_USER"> <input
					type="hidden" name="page" value="FULL_FORM">
				<table>
					<tr>
						<td><label><c:out value="${name}"/> </label></td>
						<td><input type="text" name="name" placeholder="name">
							<c:choose>
								<c:when test="${name_valid==false}">
									<c:out value="${name_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${surname}"/> </label></td>
						<td><input type="text" name="surname" placeholder="surname">
							<c:choose>
								<c:when test="${surname_valid==false}">
									<c:out value="${surname_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${patronimic}"/> </label></td>
						<td><input type="text" name="patronimic"
							placeholder="patronimic"> <c:choose>
								<c:when test="${patronimic_valid==false}">
									<c:out value="${patronimic_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${email}"/></label></td>
						<td><input type="text" name="e_mail" placeholder="email">
							<c:choose>
								<c:when test="${email_valid==false}">
									<c:out value="${email_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${role}"/><label></td>
						<td><select name="role">
								<option>ADMINISTRATOR</option>
								<option>DISPATCHER</option>
								<option>MANAGER</option>
								<option>PILOT</option>
								<option>FLIGHT ATTENDANT</option>
								<option>ENGINEER</option>
						</select></td>
					</tr>
					<tr>
						<td><label><c:out value="${login}"/> </label></td>
						<td><input type="text" name="login" placeholder="login">
							<c:choose>
								<c:when test="${login_valid==false}">
									<c:out value="${login_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${password}"/> </label></td>
						<td><input type="text" name="password" placeholder="password">
							<c:choose>
								<c:when test="${password_valid==false}">
									<c:out value="${password_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
				</table>

				<br> <input type="submit" value="${add_user}" />
			</form>
			<br>
			<br>
			<c:choose>
				<c:when test="${result_attr=='success'}">
					<c:out value="${user_add_ok}" />
				</c:when>
				<c:when test="${result_attr=='fail'}">
					<c:out value="${user_add_fail}" />
				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>
</body>
</html>