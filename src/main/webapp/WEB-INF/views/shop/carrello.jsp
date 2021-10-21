<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="it.beije.herse.entity.User"%>
<%@page import="it.beije.herse.shop.Carrello"%>
<%@page import="it.beije.herse.entity.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
</head>
<body>

	<a href='../shop/logout' ><input type='button' value='logout'></a>
	
	<h2>Il carrello di ${user.name}</h2>
		

	<ul>
<c:forEach items="${ prodottiCarrello}" var="prodotto">
		<li>
		
			<form action='../shop/rimuovi' method='post'>
				<label for="${prodotto.name}"> ${prodotto.price } </label>
				<input type='submit' value='rimuovi' /> <input type='hidden' name='idP' value='${prodotto.id }'> 
			</form>
		</li>
</c:forEach>
	</ul>
	<br><p><strong>Il totale è ${total } €</strong></p>

	<form action='../shop/pay' method='post'>
	  <br>
	   <a href='../shop/catalogo'><input type='button' value='Torna al catalogo'/></a>
	   <input type='submit' value="Completa l'acquisto" ${disabled }/>
	</form>
	<%//session.setAttribute("amount", total); %>

</body>
</html>