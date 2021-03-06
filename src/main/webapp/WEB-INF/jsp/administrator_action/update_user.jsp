<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="user.update.ok" var="user_update_ok" />
<fmt:message bundle="${loc}" key="user.update.fail"
	var="user_update_fail" />
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
<fmt:message bundle="${loc}" key="user.password" var="new_password" />
<fmt:message bundle="${loc}" key="user.choose_role" var="choose_role"/>
<fmt:message bundle="${loc}" key="airline.apply" var="apply" />
</head>
<body>
	<br>
	<c:out value="${error}" />
	<br>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="UPDATE_USER"> <input
					type="hidden" name="changes" value="made">
				<table>
					<tr>
						<td><label><c:out value="${name}" /></label></td>
						<td><input type="text" name="name"
							value="${selected_user.name}">
						<c:choose>
								<c:when test="${name_valid==false}">
									<c:out value="${name_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${surname}" /></label></td>
						<td><input type="text" name="surname"
							value="${selected_user.surname}"> <c:choose>
								<c:when test="${surname_valid==false}">
									<c:out value="${surname_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${patronimic}" /> </label></td>
						<td><input type="text" name="patronimic"
							value="${selected_user.patronimic}"> <c:choose>
								<c:when test="${patronimic_valid==false}">
									<c:out value="${patronimic_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${email}" /> </label></td>
						<td><input type="text" name="e_mail"
							value="${selected_user.email}"> <c:choose>
								<c:when test="${email_valid==false}">
									<c:out value="${email_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${role}" /> </label></td>
						<td><c:out value="${selected_user.role}" /></td>
					</tr>
					<tr>
						<td><label><c:out value="${choose_role}" /><label></td>
						<td><select name="role">
								<option>ADMINISTRATOR</option>
								<option>DISPATCHER</option>
								<option>MANAGER</option>
								<option>PILOT</option>
								<option>ATTENDANT</option>
								<option>ENGINEER</option>
						</select></td>
					</tr>
					<tr>
						<td><label><c:out value="${login}" /> </label></td>
						<td><input type="text" name="login"
							value="${user_info.login}"> <c:choose>
								<c:when test="${login_valid==false}">
									<c:out value="${login_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
					<tr>
						<td><label><c:out value="${new_password}" /> </label></td>
						<td><input type="password" name="password"> <c:choose>
								<c:when test="${password_valid==false}">
									<c:out value="${password_not_valid}" />
								</c:when>
							</c:choose></td>
					</tr>
				</table>
				<input type="submit" value="${apply}" />
			</form>
			<br>
			<c:choose>
				<c:when test="${result_attr=='success'}">
					<c:out value="${user_update_ok}" />
				</c:when>
				<c:when test="${result_attr=='fail'}">
					<c:out value="${user_update_fail}" />
				</c:when>
			</c:choose>
		
</body>
</html>