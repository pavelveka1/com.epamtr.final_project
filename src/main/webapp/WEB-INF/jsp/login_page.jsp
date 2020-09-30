<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login V16</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="resources/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<!--===============================================================================================-->
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="login" var="login" />
<fmt:message bundle="${loc}" key="password" var="password" />
<fmt:message bundle="${loc}" key="button.name.ru" var="button_ru" />
<fmt:message bundle="${loc}" key="button.name.en" var="button_en" />
<fmt:message bundle="${loc}" key="button.login" var="button_login" />
<fmt:message bundle="${loc}" key="text.airline" var="text_airline" />

</head>

<body>


	<div class="container-login100" style="background-image: url("resources/images/bg-01.jpg");">

		<div class="wrap-login100 p-t-30 p-b-50">
			<div class="button-container">

				<form class="button-container" action="Locale" method="POST">
					<input  type="hidden" name="local" value="ru"> 
					<input  type="hidden" name="jsp" value="/WEB-INF/jsp/login_page.jsp">
					<input  type="submit" value="${button_ru}" />
				</form>

				<form action="Locale" method="POST">
					<input type="hidden" name="local" value="en">
					<input type="hidden" name="jsp" value="/WEB-INF/jsp/login_page.jsp">
					 <input type="submit" value="${button_en}" />
				</form>

			</div>
			<span class="login100-form-title p-b-41"><c:out value="${text_airline}"/> </span>
			<form class="login100-form validate-form p-b-33 p-t-5"
				action="Controller" method="POST">
				<input type="hidden" name="command" value="sign_in">
				<div class="wrap-input100 validate-input"
					data-validate="Enter username">
					<input class="input100" type="text" name="login"
						placeholder="${login}"> <span class="focus-input100"
						data-placeholder="&#xe82a;"></span>
				</div>

				<div class="wrap-input100 validate-input"
					data-validate="Enter password">
					<input class="input100" type="password" name="pass"
						placeholder=${password}> <span class="focus-input100"
						data-placeholder="&#xe80f;"></span>
				</div>

				<div class="container-login100-form-btn m-t-32">

					<button class="login100-form-btn"><c:out value="${button_login}"/></button>

				</div>

			</form>
		</div>
	</div>
	</div>


	<div id="dropDownSelect1"></div>

	<!--===============================================================================================-->
	<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/vendor/bootstrap/js/popper.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/vendor/daterangepicker/moment.min.js"></script>
	<script src="resources/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="resources/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="resources/js/main.js"></script>

</body>
</html>