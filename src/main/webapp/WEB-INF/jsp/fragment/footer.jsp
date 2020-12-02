<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

   .layer {
    height: 60px; /* Высота блока */
    width: 1fr; /* Ширина блока */
  
    padding: 10px; /* Поля вокруг текста */
    border: none; /* Параметры рамки */
    text-align: center;
   font-size: 20pt;
    color: white;
   }
  
  </style>
</head>
<body>
<div class="layer">
 <div>
 <a href="https://www.iata.org"><c:out value=" IATA"/></a>
 </div>
 <div>
 <a href="https://www.icao.int/sustainability/compendium/Pages/0-default.aspx"><c:out value=" IKAO"/></a>
 </div>
 <div>
 <a href="http://www.caa.gov.by"><c:out value="Belarussian aviation authorities"/></a>
 </div>
</div>
</body>
</html>