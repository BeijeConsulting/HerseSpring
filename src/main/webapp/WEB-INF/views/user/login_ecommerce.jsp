<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.springframework.ui.Model"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">

	
</head>
<body style="margin:1%">
	<h1>Benvenuto, inserisci le tue credenziali</h1>
	<%
	String error = (String) session.getAttribute("error");
	if (error != null) {
		%>
		<span style="color:red"><%=error%></span><br><br>
		<%
		session.removeAttribute("error");
	}
	%>
	<form action="../user/login" method="post">
		<div>
			
			<label for="email">Email: </label>
			<input type="text" name="email">
			<br/><br/>
			
			
			<label for="password">Password: </label>
			<input type="password"  name ="password" required>
			<br/><br/>
		
			<button type="submit" type="button" class="btn btn-primary">Login</button>${message }
		</div>
	</form>
	<br>
	<a href="../user/register" style="text-decoration: none; color:blue;"><button type="button" class="btn btn-secondary">Registrati</button></a>
</body>
</html>