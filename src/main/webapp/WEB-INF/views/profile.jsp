<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profilo</title>

<style>
.items ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: white;
	border-bottom: 1px solid black;
}

.items li {
	float: left;
	display: block;
	color: black;
	text-align: left;
	padding: 14px 16px;
	text-decoration: none;
	width: 200px;
}

.items .right {
	float: right;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="items">
		<ul>
			<li>${user.name}</li>
			<li>${user.surname}</li>
			<li>${user.email}</li>
		</ul>
		<h2>I miei ordini</h2>
		<ul>
			<li><b>Prodotto</b></li>
			<li><b>Descrizione</b></li>
			<li><b>Quantità</b></li>
			<li class="right"><b>Costo</b></li>
		</ul>
		<c:forEach items="${items}" var="item">
			<ul>
				<li>${item.productName}</li>
				<li>${item.productDescription}</li>
				<li>${item.quantity}</li>
				<li class="right">${item.amount}</li>
			</ul>
		</c:forEach>
	</div>
</body>
</html>