<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrati</title>
</head>
<body>
<p>${message}</p>
<h2 align="center">Registrati</h2>
<style> input{background-color:light-blue;}</style>
	<form style="text-align: center;" action="../user/insert" method="post">
	  <label for="name">Nome:</label><br>
	  <input type="text" name="name"><br>
	  <label for="surname">Cognome:</label><br>
	  <input type="text" name="surname"><br>
	  <span style="text-color:orange;"><c:if test="${not empty already}">${already}</c:if></span>
	  <label for="email">Email:</label><br>
	  <input type="text" name="email"><br>
	  <label for="password">Password:</label><br>
	  <input type="password" name="password"><br>
	  <input type="submit" value="Submit">
	</form> 
</body>
</html>