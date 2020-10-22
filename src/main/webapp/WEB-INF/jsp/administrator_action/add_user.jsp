<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="ADD_USER"> <input
			type="hidden" name="page" value="FULL_FORM"> 
			<table>
			<tr>
			<td><label>Name </label></td>
			<td><input
			type="text" name="name" placeholder="name"></td>
			</tr>
			<tr>
			<td> <label>Surname </label>    </td>
			<td>  <input
			type="text" name="surname" placeholder="surname">    </td>
			</tr>
			<tr>
			<td> <label>Patronimic </label>    </td>
			<td>   <input
			type="text" name="patronimic" placeholder="patronimic">   </td>
			</tr>
			<tr>
			<td> <label>Email </label>    </td>
			<td>  <input
			type="text" name="email" placeholder="email">   </td>
			</tr>
			<tr>
			<td>  <label>Role<label>   </td>
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
			<td> <input type="text" name="login" placeholder="login">    </td>
			</tr>
			<tr> 
			<td> <label>Password </label>  </td>
			<td><input
				type="text" name="password" placeholder="password">  </td>
			</tr>
			</table>
			
			<br>  <input
				type="submit" value="add user" />
	</form>
</body>
</html>