<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prodotti</title>

<style>
.shop ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: white;
	border-bottom: 1px solid black;
}

.shop li {
	float: left;
	display: block;
	color: black;
	text-align: left;
	padding: 14px 16px;
	text-decoration: none;
	width: 200px;
}

.sendButton {
	float: right;
	display: block;
	color: white;
	height: 46.4px;
	text-decoration: none;
	width: 100px;
	background-color: red;
	border: none;
}

.shop .input {
	float: right;
	width: 60px;
	border-radius: 5px;
}

.shop input {
	border: 2px solid black;
}

.shop .active {
	background-color: red;
	float: right;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<form action="carrello" method="post">
		<c:forEach items="${products}" var="product">
			<div class="shop">
				<ul>
					<li>${product.name}</li>
					<li>${product.description}</li>
					<li>${product.price}</li>
					<li>Quantit� disponibile: ${product.quantity}</li>
					<li class="input"><input type="number" name="${product.id}" placeholder="0" max="${product.quantity}" min="0"></li>
				</ul>
			</div>
		</c:forEach>
		<br><input class="sendButton" type="submit" value="COMPRA">
	</form>
</body>
</html>