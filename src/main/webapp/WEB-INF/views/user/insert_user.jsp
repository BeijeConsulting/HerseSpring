<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrati</title>
</head>
<body style="background-color:lightgrey">

<h2 align="center">Registrati</h2>
	<form style="text-align: center;" action="../user/insert" method="post">
	 <span style="color:orange"><c:if test="${not empty already}">${already}<br></c:if></span>
	<p style="color:green">${message} <a href="../login">Torna al login</a></p>
	  <label for="name">Nome:</label><br>
	  <input type="text" name="name"><br>
	  <label for="surname">Cognome:</label><br>
	  <input type="text" name="surname"><br>
	  <label for="email">Email:</label><br>
	  <input type="text" name="email"><br>
	  <label for="password">Password:</label><br>
	  <input type="password" name="password"><br>
	  <input type="submit" value="Submit">
	</form> 
</body>
<style> input{background-color:lightblue; border-radius:3em;}</style>
</html>
