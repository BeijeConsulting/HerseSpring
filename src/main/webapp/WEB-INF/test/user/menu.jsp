<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TEST RESULT</title>
</head>
<body bgcolor="black" text="white">
	
	<h1>WELCOME 
	<c:choose>
		<c:when test="${firstName.length()>0 && lastName.length()>0}">${firstName} ${lastName}</c:when>
		<c:otherwise>Man With No Name</c:otherwise>
	</c:choose>
	</h1>
	
	<form action="../user/menu" method="post">
	<h2>PRODUCTS: </h2>
	<table>
		<tr>
			<th>ADD</th>
			<th>ID</th>
			<th>NAME</th>
			<th>DESC</th>
			<th>PRICE</th>
			<th>IN STOCK</th>
		</tr>
	<c:forEach items="${products}" var="p">
		<tr>
			<td><input type="checkbox" name="check${p.id}"></td>
			<td>${p.id}</td>
			<td>${p.name}</td>
			<td>${p.description}</td>
			<td>${p.price} $</td>
			<td>${p.quantity}</td>
		</tr>
	</c:forEach>
	</table>
	</form>
	
	
	<form action="index" method="post">
	<input type="submit" name="backToLogin" value="BACK TO LOGIN"></form>
</body>
</html>