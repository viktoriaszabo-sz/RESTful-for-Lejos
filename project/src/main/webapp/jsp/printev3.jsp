<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Print EV3 attributes</title>
</head>
<body>
<h1>EV3</h1>
<ul>
<c:forEach var="dataexchange" items="${requestScope.dataexchange }"> <!-- request.setAttribute("dogs", getDogList()); in public void readAll() -->
	<li>${dataexchange.speed}: ${dataexchange.turnangle} / ${dataexchange.maxobs} / ${dataexchange.securitydis}
	<!-- these are calling the getters  -->
</c:forEach>
</ul>
</body>
</html>