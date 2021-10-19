<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
</head>
<body>

<h1>Registrati</h1>

<hr>

<p style="color:red"><strong> ${emailSbagliata} </strong></p>

<p style="color:red"><strong> ${utenteRegistrato} </strong></p>

<form action="registrazione" method="post">
	Nome: <br>
	<input type="text" name="name" placeholder="Tuo nome" required><br>
	Cognome: <br>
	<input type="text" name="surname" placeholder="Tuo cognome" required><br>
	Email: <br>
	<input type="text" name="email" placeholder= "Your Email" required><br>
	Password:<br>
	<input type="password" name="password" placeholder="**************" required><br><br>
	<input type="submit" value="Conferma">
</form>

</body>
</html>