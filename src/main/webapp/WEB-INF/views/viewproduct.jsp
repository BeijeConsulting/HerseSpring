<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prodotti</title>
</head>
<body>
	<c:forEach items="products" var="product">
	<p>
	Nome: ${product.name}
	Descrizione: ${product.description}
	Prezzo: ${product.price}
	Quantità disponibile: ${product.quantity}
	</p>
	</c:forEach>
</body>
</html>