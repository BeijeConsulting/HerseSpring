<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% if(session.getAttribute("user")!=null){ response.sendRedirect("./benvenuti"); } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME PAGE</title>
</head>

<body style="background-color:lightgrey;">
<style>input{background-color:lightblue; border-radius:3em;}</style>

<br><br><h2 align="center">BFC - ecommerce</h2><hr>

<h3 align="center"> Effettua l'accesso per visualizzare il catalogo</h3>

<form action="login" method="post" style="text-align: center;">
<span style="color:orange;">${feedback}</span><br><br>
<label for="user">Username: </label><br>
<input type="text" name="user" placeholder="mi chiamo.."><br>
<label for="pass">Password: </label><br>
<input type="password" name="pass" placeholder="sssh.."><br>
<input type="submit" value="login"><br><br> <p style="font-size:13px">oppure
<a href="user/insert">Registrati</a></p>
</form>
</body>
</html>