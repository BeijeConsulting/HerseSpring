<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover:not(.active) {
	background-color: #111;
}

.active {
	background-color: #04AA6D;
}

</style>
</head>
<body>
	<ul>
		<li><a href="home">Home</a></li>
		<li><a href="showProduct">Prodotti</a></li>
		<li><a href="profile">Profilo</a></li>
		<li style="float: right"><a class="active" href="logout">Esci</a></li>
	</ul>
</body>
</html>