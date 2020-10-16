<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Update aircraft</title>
</head>
<body>

	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="UPDATE_AIRCRAFT"> <label>Choose
			aircraft </label> <select name="aircraft_numbers">
			<c:forEach var="type_item" items="${aircrafts}">
				<option>
					<c:out value="${type_item.registerNumber}" />
				</option>
			</c:forEach>
			</select>
			<input
			type="text" name="register_number" placeholder="New registration number">
		 <input type="submit" value="Update aircraft" />
	</form>
</body>
</html>