<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="it.beije.herse.entity.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Catalogo</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">
	<jsp:useBean id="user" class="it.beije.herse.entity.User" scope="session"></jsp:useBean>
	<h1>Catalogo</h1>
	<% if (user.getEmail() == null) {
			%>
			<h2 style="color:red">Utente non autenticato!</h2>
			<br>
			<a href="../user/login" style="text-decoration: none; color:blue;"><button type="button" class="btn btn-primary">Login</button></a>
			<%
		} else {
			%>
			<c:forEach items="${listProducts}" var="product">
				<p>
				Prodotto : ${product.name}<br/>
				Id : ${product.id}<br/>
				Descrizione : ${product.description}<br/>
				Prezzo : ${product.price}<br/>
				Quantità : ${product.quantity}<br/>
				</p>
			</c:forEach>
			<br>
			<a href="../shop/menu" ><button type="button" class="btn btn-secondary">Torna al menu</button></a>
			<br>
			<a href="../user/logout" style="text-decoration: none; color:blue;"><button type="button" class="btn btn-primary">Log out</button></a>
		<%} %>
</body>
</html>