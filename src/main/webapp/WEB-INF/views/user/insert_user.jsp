<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>INSERT USER</title>
</head>
<body>
${message}<br/>
	<form action="../user/insert" method="post">
	  <label for="email">email:</label><br>
	  <input type="text" name="email"><br>
	  <label for="password">password:</label><br>
	  <input type="text" name="password"><br>
	  <label for="name">name:</label><br>
	  <input type="text" name="name"><br>
	  <label for="surname">surname:</label><br>
	  <input type="text" name="surname"><br><br>
	  <input type="submit" value="Submit">
	</form> 
</body>
</html>