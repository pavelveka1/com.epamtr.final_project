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
	backgroung-image: url(bg-01.jpg);
}

/* Style the header */

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

form {
	margin: 2px; /* Отступы вокруг элемента */
	padding: 10px; /* Поля вокруг текста */
}

/* Style the footer */
footer {
position: fixed; /* Фиксированное положение */
    left: 0; bottom: 0; /* Левый нижний угол */
    padding: 10px; /* Поля вокруг текста */
    width: 100%; /* Ширина слоя */
	background-color: #777;
	text-align: center;
	color: white;
}

 #footer {
    position: fixed; /* Фиксированное положение */
    left: 0; bottom: 0; /* Левый нижний угол */
    padding: 10px; /* Поля вокруг текста */
    background: #39b54a; /* Цвет фона */
    color: #fff; /* Цвет текста */
    width: 100%; /* Ширина слоя */
   }

/* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */
@media ( max-width : 600px) {
	nav, article {
		width: 100%;
		height: auto;
	}
}

aside {
	background: #B0C4DE;
	padding: 2px;
	width: 180px;
	float: left;
}
</style>
</head>
<body>

	<header>
		<jsp:include page="fragment/header.jsp" />
	</header>

	<aside>
		<jsp:include page="fragment/aside.jsp" />
	</aside>

	<main>
		<jsp:include page="${current_page}" />
	</main>

	<footer>
		<p>Footer</p>
	</footer>

</body>


</html>