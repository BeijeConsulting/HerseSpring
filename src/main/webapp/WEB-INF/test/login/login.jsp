<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TEST LOGIN</title>
</head>
<body bgcolor="black" text="white">
	
	<h1>${loginMessage}</h1>

	<form action="login/logIn" method="post">
	EMAIL: <input type="email" name="username"><br>
	PASSWORD: <input type="password" name="password"><br>
	
	<br><input type="submit" name="login" value="LOGIN">
	</form> 
</body>
</html>