<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MY Home Page</title>
</head>
<body>
Benvenuto: ${name} ${surname}
<br><br>
<form action="prodotti" method="post">
	<input type="submit" value="Prodotti">
</form><br>
<c:if test = "${authUser == null}">

	<form action="redirectLogIn" method="post">
		<input type="submit" value="Log in">
	</form><br>
	
	<form action="registration" method="post">
		<input type="submit" value="Registrati">
	</form>

</c:if>

<c:if test = "${authUser != null}">

	<form action="logOut" method="post">
			<input type="submit" value="Log out">
	</form>

</c:if>

</body>
</html>