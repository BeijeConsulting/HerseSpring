<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<h1 align="center">Benvenuto</h1>

<p>Accedi per fare i tuoi acquisti</p>

<hr>

<p style="color:red"><strong> ${credenziali} </strong></p>

<div align="center">
<form action="catalogo" method="post">
	  Email: <br>
	  <input type="text" name="email" placeholder= "Your Email" required><br>
	  Password:<br>
	  <input type="password" name="password" placeholder="**************" required><br><br>
	  <input type="submit" value="Conferma">
	</form> 
</div>

</body>
</html>