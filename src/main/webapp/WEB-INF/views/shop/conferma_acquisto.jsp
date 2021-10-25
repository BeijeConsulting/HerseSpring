<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ordine confermato</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">
	<jsp:useBean id="user" class="it.beije.herse.entity.User" scope="session"></jsp:useBean>
	
	<% 
	if (user.getEmail() == null) {
		%>
		<h2 style="color:red">Utente non autenticato!</h2>
		<br>
		<a href="../user/login" style="text-decoration: none; color:blue;"><button type="button" class="btn btn-primary">Login</button></a>
		<%
	} else {
		%>
		<h1>Ordine confermato</h1>

	<br>
	<a href="../shop/menu" style="text-decoration: none; color:blue;">-> Torna al menu</a>
	<br/><br/>
	<a href="../user/logout" style="text-decoration: none; "><button type="button" class="btn btn-primary">Log out</button></a>
	
	<%} %>
</body>
</html>