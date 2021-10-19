<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME PAGE</title>
</head>
<body>
<h2>BFC - ecommerce</h2>
<h3> Effettua l'accesso per visualizzare il catalogo</h3>

<form action="login" method="post">
<span style="text-color:orange;">${error}</span><br>
<label for="user">Username: </label><br>
<input type="text" name="user" placeholder="mi chiamo.."><br>
<label for="pass">Password: </label><br>
<input type="password" name="pass" placeholder="sssh.."><br>
<input type="submit" value="login"><br><br> <p style="font-size:13px">oppure
<a href="user/insert">Registrati</a></p>
</form>
</body>
</html>