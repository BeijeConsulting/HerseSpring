<%@page import="org.apache.taglibs.standard.tag.common.xml.ForEachTag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="it.beije.herse.entity.User"%>
<%@page import="it.beije.herse.shop.Carrello"%>
<%@page import="it.beije.herse.entity.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BFC</title>
</head>
<body>
	<a href='../shop/logout'><input type='button' value='logout'></a>

	<h1>Catalogo BFC</h1>

	<marquee> Per eventuali problemi con le JPA o con le JSP
		rivolgersi al servizio clienti 80099990099 </marquee>
	<h3>
		Welcome <span style='color: green'>${user.name}</span>
	</h3>

	<ul>
	<c:forEach items="${prodotti}" var="prodotto">
		<li>
			<form action='../shop/carrellos' method='post'>
				<label for='${prodotto.name }'>${prodotto.name },prezzo : ${prodotto.price}</label> 
				<input type='submit' value='aggiungi' /> 
				<input type='hidden' name='idP'value='${prodotto.id }'> 
				 <input type='number' name='quantita' step='1' min='1' value='1' max='${prodotto.quantity}'>
			</form>
		</li>

</c:forEach>

	</ul>
	<a href='../shop/carrello'><input type='submit' value='Vai al carrello' ${disabled} /></a>

</body>
</html>