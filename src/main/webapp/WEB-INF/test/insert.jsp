<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TEST INSERT</title>
</head>
<body bgcolor="black" text="white">

	<h1>WELCOME ${email}</h1>
	
	INSERT YOUR NAME AND SURNAME:
	<form action="insert" method="post">
	<input type="text" name="name" placeholder="Your Name"><br>
	<input type="text" name="surname" placeholder="Your Surname"><br>
	<br><input type="submit" name="done" value="DONE">
	</form>	
</body>
</html>