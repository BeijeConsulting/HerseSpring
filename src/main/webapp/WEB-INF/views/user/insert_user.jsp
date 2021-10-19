<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>INSERT USER</title>
</head>
<body>
	<form action="../user/insert" method="post">
	  <label for="username">username:</label><br>
	  <input type="text" name="username"><br>
	  <label for="password">password:</label><br>
	  <input type="text" name="password"><br>
	  <label for="firstName">first name:</label><br>
	  <input type="text" name="firstName"><br>
	  <label for="lastName">last name:</label><br>
	  <input type="text" name="lastName"><br><br>
	  <input type="submit" value="Submit">
	</form> 
</body>
</html>