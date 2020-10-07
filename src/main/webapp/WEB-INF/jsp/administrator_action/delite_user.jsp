<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delite user</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="DELITE_USER"> <input
			type="text" name="login" placeholder="Login name"> <input
			type="submit" value="delite user" />
	</form>
</body>
</html>