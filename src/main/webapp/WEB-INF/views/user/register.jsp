<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Register</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">
	<h1>Non sei ancora registrato? Registrati</h1>
	<form action="../user/register" method="post">
	  <label for="email">Email:</label><br>
	  <input type="text" name="email"><br>
	  
	  <label for="password">Password:</label><br>
	  <input type="password" name="password"><br>
	  
	  <label for="name">Name:</label><br>
	  <input type="text" name="name"><br>
	  
	  <label for="surname">Surname:</label><br>
	  <input type="text" name="surname"><br><br>
	  
	  <button type="submit" type="button" class="btn btn-primary">Register</button>
	</form> 

</body>
</html>