<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
${error}
<form action="registrationConfirmation" method="post">
	<label for="name">name:</label><br>
	<input type="text" name="name" value="${tmpUser.getName()}"><br>
	<label for="surname">surname:</label><br>
	<input type="text" name="surname" value="${tmpUser.getSurname()}"><br>
	<label for="email">email:</label><br>
	<input type="text" name="email" value="${tmpUser.getEmail()}"><br>
	<label for="password">password:</label><br>
	<input type="password" name="password"><br>
	<label for="passwordCheck">ripeti password:</label><br>
	<input type="password" name="passwordCheck"><br><br>
	<input type="submit" value="Registrati">
</form><br>

<form action="redirectLogIn" method="post">
	<input type="submit" value="Log in">
</form><br>

<form action="backhome" method="post">
	<input type="submit" value="Home">
</form>
</body>
</html>