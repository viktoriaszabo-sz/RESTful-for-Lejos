<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Print EV3 attributes</title>
</head>
<body>
<h1>EV3</h1>
<ul>

<c:forEach var="attri" items="${requestScope.attri}">
	<li>${attri.speed}: ${attri.turnangle} / ${attri.maxobs} 
</c:forEach> 		<!-- this will help us see the attributes posted into the database (json string pretty much) -->

</ul>
<h1> This is a threat ᕦ(ò_óˇ)ᕤ  </h1>
</body>
</html>