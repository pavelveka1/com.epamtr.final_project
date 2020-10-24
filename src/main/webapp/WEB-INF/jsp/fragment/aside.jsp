<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_USER"> 
				<input
					type="submit" value="add user" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="DELITE_USER"> <input
					type="submit" value="delite user" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="UPDATE_USER"> <input
					type="submit" value="Update user" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_USERS_BY_ROLE"> 
				<input
					type="submit" value="Get users by role" />
			</form>
			
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_USERS_BY_FLIGHT_ID"> <input
					type="submit" value="Get users by flight" />
			</form>
			
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_AIRCRAFT_TYPE"> <input
					type="submit" value="Add type of aircraft" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="DELETE_AIRCRAFT_TYPE"> <input
					type="submit" value="Delete aircraft type" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_AIRCRAFT"> <input
					type="submit" value="add aircraft" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="DELETE_AIRCRAFT"> <input
					type="submit" value="delete aircraft" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="UPDATE_AIRCRAFT"> <input
					type="submit" value="Update aircraft number" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="CHANGE_STATUS_AIRCRAFT"> <input
					type="submit" value="Update aircraft status" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_FLIGHT"> <input
					type="submit" value="Add new flight" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="DELETE_FLIGHT"> <input
					type="submit" value="Delete flight" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="UPDATE_FLIGHT"> <input
					type="submit" value="Update flight" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_FLIGHTS_BY_STATUS"> <input
					type="submit" value="Flights by status" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_FLIGHTS_BY_USER"> <input
					type="submit" value="Flights by user" />
			</form>
</body>
</html>