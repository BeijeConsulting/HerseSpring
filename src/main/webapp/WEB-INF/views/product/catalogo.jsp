<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LIST USERS</title>
</head>
<body>
	Benvenuto, ${user.name} ${user.surname} , di seguito il nostro
	catalogo:
	<table>
		<thead>
			<tr>
				<td>Id</td>
				<td>Nome</td>
				<td>Descrizione</td>
				<td>Prezzo</td>
				<td>Quantità Disponibili</td>
			</tr>
		</thead>
		<c:forEach items="${products}" var="product">
		<tr>
			<td>${product.id}</td>
			<td>${product.name}</td>
			<td>${product.desc}</td>
			<td>${product.price}</td>
			<td>${product.qty}</td>
		</tr>
		</c:forEach>
		</table>
		<form action='catalogo' method='post'>
		<label for='id'>Id:</label><br> 
		<input type='text'name='id' required> <br> 
		<label for='qty'>Quantità:</label><br>
		<input type='text' name='qty' required><br>
		<button type='submit'>Invio</button>
	</form>
	<form action='carrello' method='post'>
		<button type='submit'>Vai al carrello</button>
	</form>
	<form action='dettagli' method='post'>
		<button type='submit'>Dettagli prodotti</button>
	</form>
</body>
</html>