<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add flight</title>
</head>
<body>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="ADD_AIRCRAFT_TYPE"> 
		
		
			<table>
			<tr>
			<td><label>Enter new type of aircraft</label></td>
			<td><input
			type="text" name="aircraft_type" placeholder="aircraft type"></td>
			</tr>
			<tr>
			<td> <label>Enter range of flight</label>    </td>
			<td>  <input
			type="number" name="range_flight" placeholder="range of flight">   </td>
			</tr>
			<tr>
			<td> <label>Enter number of passenger</label> </td>
			<td>   <input
			type="number" name="number_passengers" placeholder="passenger's number">  </td>
			</tr>
			</table>
		
			<br>
			 
			 <input
				type="submit" value="Apply" />
	</form>
</body>
</html>