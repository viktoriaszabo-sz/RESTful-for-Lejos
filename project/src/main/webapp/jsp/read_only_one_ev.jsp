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
<h1>Print only one EV3 according to id</h1>

<!-- <form action = '/rest/project/read_only_one_dog/{id}" method = 'post'>

	Speed: <input type = 'text' name = 'speed' value = ''><br>
	Turn angle: <input type = 'text' name = 'turnangle' value = ''><br>
	Max obstacle count: <input type = 'text' name = 'maxobs' value = ''><br>
	Security distance: <input type = 'text' name = 'securitydis' value = ''><br>
	
	<input type = 'submit' name = 'ok' value = 'Send'><br>
	

</form>  -->


<ul>

<c:forEach var="attri" items="${requestScope.attri}">
	<li>${attri.speed}: ${attri.turnangle} / ${attri.maxobs} 
</c:forEach> 		<!-- this will help us see the attributes posted into the database (json string pretty much) -->

</ul>
<h1> this is a test</h1>
</body>
</html>