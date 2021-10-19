<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form action="auth" method="post">
	  <label for="email">E-mail:</label><br>
	  <input type="text" name="email"><br>
	  <label for="password">Password:</label><br>
	  <input type="password" name="password"><br><br>
	  <input type="submit" value="Submit">
	</form> 
</body>
</html>