<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>INSERT USER</title>
</head>
<body>
	<c:if test="${error!=null}">
		<p style="color:red">${error}</p>
	</c:if>

	<form action="../user/insert" method="post">
		<label for="username">username:</label><br> <input type="text"
			name="username"><br> <label for="password">password:</label><br>
		<input type="text" name="password"><br> <label
			for="firstName">first name:</label><br> <input type="text"
			name="firstName"><br> <label for="lastName">last
			name:</label><br> <input type="text" name="lastName"><br>
		<br> <input type="submit" value="Submit">
	</form>
</body>
</html>