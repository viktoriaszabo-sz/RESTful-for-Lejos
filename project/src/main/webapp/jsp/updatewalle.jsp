<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException, conn.Connections" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Print EV3 attributes</title>
</head>
<body>

<style>
body {
    background-color: #3E4172;
}

h1   {
    display: inline-block;
    color: #9B7495;
    border: 5px dashed #9B7495;
    padding: 10px;
}

p    {
    color: #CFBFB8;
    font-size: 20px;
}

p1    {
    color: #CFBFB8;
}

input[type="submit"] {
            border-radius: 10px; 
            padding: 5px; 
        }
</style>

<h1>Update your input for Wall-E</h1>

 <form action = '/rest/project/updatewalle' method ='post' >

<!-- 	ID: <input type = 'text' name = 'id' value = '${requestScope.walle.id}'><br>
	Speed: <input type = 'text' name = 'speed' value = '${requestScope.walle.speed}'><br>
	Turn angle: <input type = 'text' name = 'turnangle' value = '${requestScope.walle.turnangle}'><br>
	Max obstacle count: <input type = 'text' name = 'maxobs' value = '${requestScope.walle.maxobs}'><br>

	<input type = 'submit' name = 'ok' value = 'Update'><br>
 -->
 
		<%/*
		Connection conn=null;
		//Attri d = new Attri();
		try {
    conn = Connections.getConnection();  //connection to db / mysql
    PreparedStatement pstmt = conn.prepareStatement("select * from walle order by id desc");
    ResultSet RS = pstmt.executeQuery();
    if (RS.next()) {
        int id = RS.getInt("id");
*/%>
    <!-- ID: <input name="id" value="${id}" readonly="readonly"><br>  -->
    <!-- ID: <input type="text" name="id"><br> -->
<% 
/*
    }
} catch (SQLException e) {
    e.printStackTrace();
}
*/%>
		Speed: <input type="text" name="speed"><br>
		Turn angle: <input type="text" name="turnangle"><br>
		Max obstacle count: <input type="text" name="maxobs"><br>
		
		<input type = 'submit' name = 'ok' value = 'Send'><br><br>
		<a href = '/html/ev3.html' class = "button" target = '_blank'>Back</a><br><br>

</form>  

<img src="/html/images/Pixar-Wall-E.jpg" alt="robot" style="width: 750px; height: 450px;">

<ul>
<!-- 
<c:forEach var="attri" items="${requestScope.attri}">
	<li>${attri.id} /${attri.speed} / ${attri.turnangle} / ${attri.maxobs} 
</c:forEach>  -->		<!-- this will help us see the attributes posted into the database (json string pretty much) -->

</ul>
</body>
</html>