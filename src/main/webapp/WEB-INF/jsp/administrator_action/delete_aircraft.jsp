<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete aircraft</title>
</head>
<body>
	<c:choose>
		<c:when test="${error!=null}">
			<java-classes:printErrorInformation errorType="${error}" />
Error is not null
</c:when>
		<c:otherwise>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="DELETE_AIRCRAFT">
				<input type="hidden" name="delete_aircraft" value="DELETE_AIRCRAFT">
				<label>Choose aircraft </label> <select name="aircraft_numbers">
					<c:forEach var="type_item" items="${aircrafts}">
						<option>
							<c:out value="${type_item.registerNumber}" />
						</option>
					</c:forEach>
				</select> <input type="submit" value="delete aircraft" />
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>