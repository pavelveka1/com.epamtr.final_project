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
  a:link {
  color: #FF00FF; 
  border-bottom: 1px dashed; 
}
a:visited {
  color: #00FF00;
}
a:hover {
  color: #FF0000; 
  border-bottom: .07em solid;
}
a:active {
  color: #FF00FF; 
  border-bottom: 1px dashed;
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
    width: 100%; /* Ширина блока */
    background: #4B0082; /* Цвет фона */
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
  
  <div>
   <h4>
  
			<c:out value="${user.role}" />
			:
			<c:out value="${user.surname}" />
			<c:out value="${user.name}" />
			<c:out value="${user.patronimic}" />
		</h4>
  </div>
  
  <div>
   <form action="Controller" method="POST">
    <input type="hidden" name="command" value="SIGN_OUT">
    <input type="submit" value="${sign_out}" />
   </form>
  </div>
 <div>
   <form action="Locale" method="POST">
    <input type="hidden" name="local" value="ru">
     <input type="hidden" name="jsp" value="/WEB-INF/jsp/administrator_page.jsp">
    <input type="submit" value="${button_ru}" />
   </form>
  </div>
  <div>
   <form action="Locale" method="POST">
    <input type="hidden" name="local" value="ru">
    <input type="hidden" name="jsp" value="/WEB-INF/jsp/administrator_page.jsp">
    <input type="submit" value="${button_en}" />
   </form>
  </div>
 <div>
 <a href="http://localhost:8080/airline/Controller?action=Controller&command=SIGN_OUT"><c:out value="${sign_out}"/></a>
 </div>
 <div>
 <a href="http://localhost:8080/airline/Locale?action=Locale&local=ru&jsp=/WEB-INF/jsp/administrator_page.jsp"><c:out value="${button_ru}"/></a>
 </div>
 <div>
 <a href="http://localhost:8080/airline/Locale?action=Locale&local=ru&jsp=/WEB-INF/jsp/administrator_page.jsp"><c:out value="${button_en}"/></a>
 </div>
 
  </div> 
 </body>
</html>