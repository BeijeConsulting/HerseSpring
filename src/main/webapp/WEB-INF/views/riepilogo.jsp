<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo</title>

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
	text-decoration: none;
	width: 100px;
	height: 46.4px;
	border-color: white;
	background-color: red;
}

.shop .right {
	float: right;
}
</style>

</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<form action="confirmOrder" method="post">
		<c:forEach items="${items}" var="item">
			<div class="shop">
				<ul>
					<li>${item.productName}</li>
					<li>${item.productDescription}</li>
					<li>${item.quantity}</li>
					<li class="right">${item.amount}</li>
				</ul>
			</div>
		</c:forEach>
		<br> <input class="sendButton" type="submit" value="CONFERMA">
	</form>
	<form action="deleteItems" method="post">
		<input class="sendButton" type="submit" value="CANCELLA">
	</form>
</body>
</html>