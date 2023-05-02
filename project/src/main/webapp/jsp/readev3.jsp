<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Read EV3 attributes</title>
</head>
<body>
	<h1>EV3</h1>
	<ul>

		<c:forEach var="attri" items="${requestScope.attri}">
			<!-- request.setAttribute("dogs", getDogList()); in public void readAll() -->
			<li>${attri.speed}/ ${attri.turnangle} / ${attri.maxobs} /
				${attri.securitydis}
		</c:forEach>
		<!-- this will help us see the attributes retrieved from the database (json string pretty much -->
	</ul>
	<h1>this is a test</h1>
</body>
</html>