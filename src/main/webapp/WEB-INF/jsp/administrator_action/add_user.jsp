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
			type="hidden" name="page" value="FULL_FORM"> <input
			type="text" name="name" placeholder="name"> <input
			type="text" name="surname" placeholder="surname"> <input
			type="text" name="patronimic" placeholder="patronimic"> <input
			type="text" name="email" placeholder="email"> <br><br> <label>Role<label>
				<select name="role">
					<option>ADMINISTRATOR</option>
					<option>DISPATCHER</option>
					<option>MANAGER</option>
					<option>PILOT</option>
					<option>FLIGHT ATTENDANT</option>
					<option>ENGINEER</option>
			</select> <input type="text" name="login" placeholder="login"> <input
				type="text" name="password" placeholder="password"> <input
				type="submit" value="add user" />
	</form>
</body>
</html>