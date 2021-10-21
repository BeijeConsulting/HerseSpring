<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="java.util.List"%>
 <%@page import="it.beije.herse.entity.Product"%>
 <%@page import="it.beije.herse.entity.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Nuovo ordine</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">
	<jsp:useBean id="user" class="it.beije.herse.entity.User" scope="session"></jsp:useBean>
	
	<%
	String errorInput = (String) session.getAttribute("errorInput");
	if (errorInput != null) {
		%>
		<span style="color:red"><%=errorInput%></span><br><br>
		<%
		session.removeAttribute("error");
	}
	%>
	
	<%
	String errorIdProduct = (String) session.getAttribute("errorIdProduct");
	if (errorIdProduct != null) {
		%>
		<span style="color:red"><%=errorIdProduct%></span><br><br>
		<%
		session.removeAttribute("error");
	}
	%>
	
		<%
	String errorQuantity = (String) session.getAttribute("errorQuantity");
	if (errorQuantity != null) {
		%>
		<span style="color:red"><%=errorQuantity%></span><br><br>
		<%
		session.removeAttribute("error");
	}
	%>
	
	<h1>Nuovo ordine</h1>
	<% 
	if (user.getEmail() == null) {
		%>
		<h2 style="color:red">Utente non autenticato!</h2>
		<br>
		<a href="../user/login" style="text-decoration: none; color:blue;"><button type="button" class="btn btn-primary">Login</button></a>
		<%
	} else {
		%>
	<br>
	<strong>Inserisci id e quantità dei prodotti che vuoi aggiungere al carrello</strong>
	<br>
	<form action="../shop/ordine" method="post">
		<label for="id">Id prodotto: </label>
		<input type="number" name ="id">	
		<br>
		<label for="quantita">Quantità prodotto: </label>
		<input type="number" name ="quantita">
		<br>
		<br>
		<button type ="submit" type="button" class="btn btn-success" name="btn_submit">Vai al carrello</button>
	</form>
	<br>
	<table class="table table-hover">
			<thead>
				<tr>
					<th>Prodotti disponibili</th>
					<th>Quantità disponibile</th>
					<th>Id prodotto</th>
					<th>Prezzo</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listProducts}" var="product">
					<tr>
						<td>
							<span>Prodotto : ${product.name}</span>
							<a href="../shop/catalogo">Dettaglio prodotti</a>
						</td>
						<td>${product.quantity}</td>
						<td>${product.id}</td>
						<td>${product.price}</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
	
	<br><br>
	<a href="../shop/menu" ><button type="button" class="btn btn-secondary">Torna al menu</button></a>
	<br><br>
	<a href="../user/logout" style="text-decoration: none; color:blue;"><button type="button" class="btn btn-primary">Log out</button></a>
	<%} %>
</body>

</html>