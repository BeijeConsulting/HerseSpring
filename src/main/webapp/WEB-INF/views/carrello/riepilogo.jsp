<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="it.beije.herse.Ecommerce.Carrello, it.beije.herse.entity.Product, it.beije.herse.entity.Order, java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo Carrello</title>
</head>
<body>

<h1 align="center">Riepilogo Carrello</h1>

<hr>

<h3> Prodotti acquistati fino ad ora </h3>


<table Style="border:1px solid">
	<tr>
		<th> Id Prodotto </th>
		<th> Quantità </th>
	</tr>
	<c:forEach items="${map.keySet()}" var="key">
		Prodotto:
		<c:forEach items="${products}" var="p">
			<c:if test="${p.id == key}">
				<tr>
					<td>${key}</td>
					<td>${map.get(key)}</td>
				</tr>
			</c:if>
		</c:forEach>
	</c:forEach>
</table>

<form action='../catalogo' method='get'>
	
		<p>
			Torna Agli acquisti: <input type="submit" value="Vai">
		</p>
	
</form>

</body>
</html>