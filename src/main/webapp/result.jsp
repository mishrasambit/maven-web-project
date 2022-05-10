<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<%
		final String message = (String) request.getAttribute ("message");
		%>
	<h1>
		<%= message %>
	</h1>
	<a href="login.html">Please Login!</a>
</body>
</html>