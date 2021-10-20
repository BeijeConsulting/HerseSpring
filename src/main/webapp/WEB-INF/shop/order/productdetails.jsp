<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>
<body bgcolor="black" text="white">

	<h1>HERSE SHOP</h1>
	
	PRODUCT DETAILS: <br>
	<table>
		<tr><td>ID: </td><td>${product.getId()}</td></tr>
		<tr><td>NAME: </td><td>"${product.getName()}"</td></tr>
		<tr><td>DESCRIPTION: </td><td>${product.getDescription()}</td></tr>
		<tr><td>PRICE: </td><td>${product.getPrice()} $</td></tr>
		<tr><td>IN STOCK: </td><td>${product.getQuantity()} </td></tr>
	</table>
	<br>
	<form action="../order/backToMenu" method="post">
	<br><br>
	<input type="submit" name="back" value="BACK">
	</form>
</body>
</html>