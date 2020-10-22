<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="GET_USER_BY_LOGIN"> <label> Input login</label>
		<input
			type="text" name="login" placeholder="login">
			<input type="submit" value="find"/>
			
			</form>
			<br><br>
		
		<form action="Controller" method="POST">
		<input type="hidden" name="command" value="UPDATE_USER">	
		<input type="hidden" name="changes" value="made">
		<table>
			<tr>
			<td><label>Name </label></td>
			<td><input
			type="text" name="name" value="${user.name}"></td>
			</tr>
			<tr>
			<td> <label>Surname </label>    </td>
			<td>  <input
			type="text" name="surname" value="${user.surname}">    </td>
			</tr>
			<tr>
			<td> <label>Patronimic </label>    </td>
			<td>   <input
			type="text" name="patronimic" value="${user.patronimic}">   </td>
			</tr>
			<tr>
			<td> <label>Email </label>    </td>
			<td>  <input
			type="text" name="email" value="${user.email}">   </td>
			</tr>
			<tr>
			<td>  <label>Current user role </label>   </td>
			<td> <c:out value="${user.role}" />    </td>
			</tr>
			<tr>
			<td>  <label>Choose role<label>   </td>
			<td>   <select name="role">
					<option>ADMINISTRATOR</option>
					<option>DISPATCHER</option>
					<option>MANAGER</option>
					<option>PILOT</option>
					<option>FLIGHT ATTENDANT</option>
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
				type="text" name="password" placeholder="password">  </td>
			</tr>
			</table>
			<input
				type="submit" value="Apply" />
	</form>
	
	
</body>
</html>