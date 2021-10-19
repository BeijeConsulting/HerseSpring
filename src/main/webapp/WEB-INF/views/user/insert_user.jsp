<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrati</title>
</head>
<body>

<h2 align="center">Registrati</h2>
<style> input{background-color:pink;}</style>
	<form style="text-align: center;" action="../user/insert" method="post">
	  <label for="name">Nome:</label><br>
	  <input type="text" name="name"><br>
	  <label for="surname">Cognome:</label><br>
	  <input type="text" name="surname"><br><br>
	  <label for="email">Email:</label><br>
	  <input type="text" name="email"><br>
	  <label for="password">Password:</label><br>
	  <input type="password" name="password"><br>
	  <input type="submit" value="Submit">
	</form> 
</body>
</html>