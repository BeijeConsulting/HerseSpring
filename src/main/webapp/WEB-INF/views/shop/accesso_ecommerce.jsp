<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accesso</title>
</head>
<body>

<h1 style="color:green">Benvenuto in <strong>BFC</strong>, il primo e-commerce d'Italia</h1>


	<%
	String error = (String) session.getAttribute("error");
	if (error != null) {
	%>
	<span style="color: red"><%=error%></span>
	<br>
	<br>
	<%
	session.removeAttribute("error");
	}
	%>


	<form action="../shop/login" method="post">
		<label for="username">Username</label><br>
		 <input type="text"name="email" value="utente@prova.it"><br>
			 <label for="password">Password</label><br> 
			 <input type="text"name="password" value="12345"><br> 
			 <input type="submit" value="Submit">
	</form>
	
	<a href = "../shop/iscriviti"> <input type="button" value = "nuovo utente"></a>
</body>
</html>