<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
 <head>
  <meta charset="utf-8">
  <fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.epamtr.airline.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="button.name.ru" var="button_ru" />
<fmt:message bundle="${loc}" key="button.name.en" var="button_en" />
<fmt:message bundle="${loc}" key="button.name.sign_out" var="sign_out" />

  <title>height</title>
  <style>
  .wrapper {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-template-rows: 1;
 }
 
 .wrapper-user-name-location{
 grid-column: 1 / 2;
 }
 
 .wrapper-button-location{
 grid-column: 6;
 }
  
  a:link {
  color: #FF00FF; 
  
}
a:visited {
  color: #00FF00;
}
a:hover {
  color: #FF0000; 

}
a:active {
  color: #FF00FF; 
 
}
  div{
  display: inline-block;
  }
  button {
   height: 40px; /* Высота блока */
    width: 30%; /* Ширина блока */
    background: #1E90FF; /* Цвет фона */
    padding: 7px; /* Поля вокруг текста */
    align: right;
    border: none; /* Параметры рамки */
    
    color: white;
  }
   .layer {
    height: 60px; /* Высота блока */
    width: 1fr; /* Ширина блока */
  
    padding: 7px; /* Поля вокруг текста */
    border: none; /* Параметры рамки */
    text-align: left;
    color: white;
   }
   button #rock {
   background: url(uk.png) no-repeat;
   float: left;
   color: white;
   position: right;
}
  </style>
  
 </head> 
 <body> 
  <div class="layer">
  
  <div class="wrapper-use-name-location">
   <h4>
  
			<c:out value="${user.role}" />
			:
			<c:out value="${user.surname}" />
			<c:out value="${user.name}" />
			<c:out value="${user.patronimic}" />
		</h4>
  </div>
  <div class="wrapper-button-location" align="right">
  <div>
 <a href="http://localhost:8080/airline/Controller?action=Controller&command=SIGN_OUT"><c:out value="${sign_out}"/></a>
 </div>
  <div>
	<form action="Locale" method="POST">
			<input type="hidden" name="local" value="ru"> <input
				type="hidden" name="jsp" value="/WEB-INF/jsp/main_page.jsp">
			<input type="image"
				src="https://v1.iconsearch.ru/uploads/icons/finalflags/48x48/russia-flag.png"
				alt="Russian" />
		</form>
	</div>
	<div>
	<form action="Locale" method="POST">
			<input type="hidden" name="local" value="en"> <input
				type="hidden" name="jsp" value="/WEB-INF/jsp/main_page.jsp">
			<input type="image"
				src="https://v1.iconsearch.ru/uploads/icons/finalflags/48x48/united-kingdom-flag.png"
				alt="English" />
		</form>
	</div>
	</div>
	</div>
 </body>
</html>