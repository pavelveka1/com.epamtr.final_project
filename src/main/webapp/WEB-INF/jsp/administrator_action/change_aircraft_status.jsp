<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change aircraft status</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="CHANGE_STATUS_AIRCRAFT">

		<table border="1">
			<tr>
				<th>Registration number</th>
				<th>Current status</th>
				<th>Choose status</th>
				<th>Apply</th>
			</tr>
			
			<c:forEach var="type_item" items="${aircrafts}">
		<form action="Controller" method="POST"> 
		<input type="hidden" name="command" value="CHANGE_STATUS_AIRCRAFT">
		<input type="hidden" name="id_aircraft" value="${type_item.idAircraft}">
		<tr>
					<td><c:out value="${type_item.registerNumber}" /></td>
					<td><c:out value="${type_item.status}" /></td>
					<td> 
					    <select name="aircraft_status">
							<option>MAINTENANCE</option>
							<option>SERVICEABLE</option>
							<option>UNSERVICEABLE</option>
					    </select>
					</td>
					<td><input type="submit" value="Apply"></td>
						</tr>
						
		</form>
		</c:forEach>
			</table>


		<label>Choose aircraft </label> <select name="registeration_number">
			<c:forEach var="type_item" items="${aircrafts}">
				<option>
					<c:out value="${type_item.registerNumber}" /> Status:
					<c:out value="${type_item.status}" />
				</option>
			</c:forEach>
		</select> <label> Choose aircraft status </label> <select
			name="aircraft_status">
			<option>MAINTENANCE</option>
			<option>SERVICEABLE</option>
			<option>UNSERVICEABLE</option>
		</select> <input type="submit" value="Apply" />
	</form>

</body>
</html>