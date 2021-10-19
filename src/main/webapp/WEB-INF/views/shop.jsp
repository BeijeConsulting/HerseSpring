<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shop</title>

<style>
form ul {
	border-bottom: 1px solid black;
}

.myli {
	background-color: white;
	height: 30px;
}

.myli li {
	width: 200px;
}

.myli-float {
	float: right;
}

.myli-float input {
	width: 50px;
	height: 25px;
}

.buy {
	background-color: #04AA6D;
	border: none;
	color: white;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	height: 40px;
	width: 70px;
}
</style>

</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>

	<form action="review" method="post">
		<%=session.getAttribute("htmlEl")%><br>
		<input class="buy" type="submit" value="Buy">
	</form>

</body>
</html>