<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="user.update.ok" var="user_update_ok" />
<fmt:message bundle="${loc}" key="user.update.fail" var="user_update_fail" />
</head>
<body>

		<form action="Controller" method="POST">
		<input type="hidden" name="command" value="UPDATE_USER">	
		<input type="hidden" name="changes" value="made">
		<table>
			<tr>
			<td><label>Name </label></td>
			<td><input
			type="text" name="name" value="${selected_user.name}"></td>
			</tr>
			<tr>
			<td> <label>Surname </label>    </td>
			<td>  <input
			type="text" name="surname" value="${selected_user.surname}">    </td>
			</tr>
			<tr>
			<td> <label>Patronimic </label>    </td>
			<td>   <input
			type="text" name="patronimic" value="${selected_user.patronimic}">   </td>
			</tr>
			<tr>
			<td> <label>Email </label>    </td>
			<td>  <input
			type="text" name="email" value="${selected_user.email}">   </td>
			</tr>
			<tr>
			<td>  <label>Current user role </label>   </td>
			<td> <c:out value="${selected_user.role}" />    </td>
			</tr>
			<tr>
			<td>  <label>Choose role<label>   </td>
			<td>   <select name="role">
					<option>ADMINISTRATOR</option>
					<option>DISPATCHER</option>
					<option>MANAGER</option>
					<option>PILOT</option>
					<option>ATTENDANT</option>
					<option>ENGINEER</option>
			</select>  </td>
			</tr>
			<tr>
			<td>  <label>Login </label>   </td>
			<td> <input type="text" name="login" value="${user_info.login}">    </td>
			</tr>
			<tr> 
			<td> <label>Enter new password </label>  </td>
			<td><input
				type="password" name="password" value="${user_info.password}">  </td>
			</tr>
			</table>
			<input
				type="submit" value="Apply" />
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