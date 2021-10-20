<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>
<body bgcolor="black" text="white">
	<h1>HERSE SHOP</h1>
	
	<jsp:useBean id="loggedUser" class="it.beije.herse.entity.User" scope="session"></jsp:useBean>
	
	MY INFO: <br>
	<table>
		<tr><td>ID: </td><td>${loggedUser.id}</td></tr>
		<tr><td>NAME: </td><td>${loggedUser.name}</td></tr>
		<tr><td>SURNAME: </td><td>${loggedUser.surname}</td></tr>
		<tr><td>EMAIL: </td><td>${loggedUser.email}</td></tr>
		<tr><td>PASSWORD: </td><td>${loggedUser.password}</td></tr>
	</table>
	<br>
	<form action="../user/backToMenu" method="post">
	<br><br>
	<input type="submit" name="back" value="BACK">
	</form>
</body>
</html>