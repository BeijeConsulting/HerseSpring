<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TEST INSERT</title>
</head>
<body bgcolor="black" text="white">

	<h1>WELCOME ${user.username}</h1>
	
	PASSWORD: <c:forEach begin="1" end="${user.password.length()}">*</c:forEach><br><br>
	
	INSERT YOUR FISTNAME AND LASTNAME:
	<form action="../login/insert" method="post">
	<input type="text" name="firstName" placeholder="Your First Name"><br>
	<input type="text" name="lastName" placeholder="Your Last Name"><br>
	<br><input type="submit" name="done" value="DONE">
	</form>	
</body>
</html>