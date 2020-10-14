<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>CSS Template</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
header {
	height: 10%;
	padding: 10px;
	text-align: center;
	font-size: 35px;
	color: white;
	background-color: #666;
}

/* Create two columns/boxes that floats next to each other */
nav {
	float: left;
	width: 20%;
	height: 300px; /* only for demonstration, should be removed */
	background: #ccc;
	padding: 20px;
}

/* Style the list inside the menu */
nav ul {
	list-style-type: none;
	padding: 10;
}

/* Clear floats after the columns */
section:after {
	content: "";
	display: table;
	clear: both;
	height: 80%;
}

/* Style the footer */
footer {
	background-color: #777;
	padding: 10px;
	width: 100%;
	text-align: center;
	color: white;
}

/* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */
@media ( max-width : 600px) {
	nav, article {
		width: 100%;
		height: auto;
	}
}
</style>
</head>
<body>
	<header>
		<h5>
			<c:out value="${user.role}" />
			:
			<c:out value="${user.surname}" />
			<c:out value="${user.name}" />
			<c:out value="${user.patronimic}" />
		</h5>
		<form action="Controller" method="POST">
				<input type="hidden" name="command" value="SIGN_OUT"> <input
					type="submit" value="Sign out" />
			</form>
	</header>

	<section>
		<nav>
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
					type="submit" value="Get users" />
			</form>
			
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_USERS_BY_FLIGHT_ID"> <input
					type="submit" value="Get users by flight" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="ADD_FLIGHT"> <input
					type="submit" value="add new flight" />
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
					type="submit" value="Add flight" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="DELETE_FLIGHT"> <input
					type="submit" value="Delete flight" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="UPDATE_FLIGHT"> <input
					type="submit" value="Updare flight" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_FLIGHTS_BY_STATUS"> <input
					type="submit" value="Flights by status" />
			</form>
			
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="GET_FLIGHTS_BY_USER"> <input
					type="submit" value="Flights by user" />
			</form>
			
			
			
		</nav>
	</section>
	<main class="col-xs-12 col-sm-8 col-md-9 col-lg-10"> 
				<jsp:include page="${currentPage}" /> 
			</main>
	<footer>
		<p>Footer</p>
	</footer>

</body>


</html>