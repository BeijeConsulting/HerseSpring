<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
${error}<br/>
	<form action='login' method='post' >
		<label for="username">Username:</label><br>
		<input type='text' name='username'><br>
		<label for="password">Password:</label><br>
		<input type='text' name='password'><br>
		<button type='submit'>Login</button>
	</form>
	<form action='signup' method='get'>
	<button type='submit'>Registrati</button>
	</form>
</body>
</html>