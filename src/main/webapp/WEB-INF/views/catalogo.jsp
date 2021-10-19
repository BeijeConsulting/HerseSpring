<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.beije.herse.entity.Product, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalogo</title>
</head>
<body>

	<h1 align="center">Catalogo Prodotti</h1>

	<p>
		Benvenuto Utente: <strong>${userId}</strong>
	</p>

	<hr>

	<div align="center">
		<h3>I nostri Prodotti</h3>

		<table Style="border: 1px solid">
			<tr>
				<th>Id</th>
				<th>Prodotto</th>
				<th>Prezzo</th>
				<th>Quantità</th>
			</tr>
			<c:forEach items="${products}" var="products">
				<tr>
					<td>${products.id}</td>
					<td>${products.name}</td>
					<td>${products.price}</td>
					<td>${products.quantity}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<h3>Cosa vuoi comprare?</h3>
	
	<form action='carrello/crea' method='post'>

		<p>
			Id Prodotto : <br>
			<input type="text" name="productId" placeholder= "Product Id" required>
		</p>

		<p>
			Quantità : <br>
			<input type="text" name="quantity" placeholder= "Quantity" required>
		</p>
		<p>
			<input type="submit" value="Conferma">
		</p>
	</form>
	
	<form action='carello/riepilogo' method='post'>
	
		<p>
			<input type="submit" value="Riepilogo Carrello">
		</p>
	
	</form>

</body>
</html>