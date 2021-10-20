<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>
<body bgcolor="black" text="white">

	<h1>HERSE SHOP</h1>
	
	MY ORDER HISTORY: <br>
	<c:forEach items="${orderHistory.keySet()}" var="o">
	<table>
		<tr>
			<th>ORDER ID: ${o.getId()}</th>
			<th>AMOUNT: ${o.getAmount()} $</th>
			<th>DATE: ${DateTimeFormatter.ISO_DATE.format(o.getDateTime())}</th>
			<th>TIME: ${DateTimeFormatter.ISO_TIME.format(o.getDateTime())}</th>
		</tr>
		
		<c:forEach items="${orderHistory.get(o)}" var="i">
		<tr>
			<td></td>
			<td>PRODUCT ID: ${i.getProductId()}</td>
			<td>PRICE: ${i.getSellPrice()} $</td>
			<td>QUANTITY: ${i.getQuantity()}</td>
		</tr>
		</c:forEach>
		
	</table><br>
	</c:forEach>
	
	<form action="../user/backToMenu" method="post">
	<br><br>
	<input type="submit" name="back" value="BACK">
	</form>
</body>
</html>