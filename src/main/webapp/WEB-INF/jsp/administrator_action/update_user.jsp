<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
		<input type="hidden" name="command" value="UPDATE_USER"> <label>  Name</label>	
		<input type="hidden" name="changes" value="made">
			 <input
			type="text" name="name" value="${user.name}"><br> 
			<label>  Surname</label><input
			type="text" name="surname" value="${user.surname}"> <br>
			<label>  Patronimic</label><input
			type="text" name="patronimic" value="${user.patronimic}"><br>
			<label>  Email</label><input
			type="text" name="email" value="${user.email}"> <br><br> <label>Role<label>
				<select name="role">
					<option>MANAGER</option>
					<option>ADMINISTRATOR</option>
					<option>DISPATCHER</option>
					<option>PILOT</option>
					<option>FLIGHT ATTENDANT</option>
					<option>ENGINEER</option>
			</select><br>
			<label>  Login</label> <input type="text" name="login" value="${user_info.login}"><br>
			<label>  Password</label> <input
				type="text" name="password" value="${user_info.password}"> <input
				type="submit" value="Apply" />
	</form>
</body>
</html>