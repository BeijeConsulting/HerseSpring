<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>navbar</title>

<style>
.nav ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: black;
}

.nav li {
	float: left;
}

.nav li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

.nav .active {
	background-color: red;
	float: right;
	width: 100px;
}
</style>

</head>
<body>
	<div class="nav">
		<ul>
			<li><a href="homePage">Home</a></li>
			<li><a href="showProduct">Shop</a></li>
			<li><a href="carrello">Carrello</a></li>
			<li><a href="profile">Profilo</a></li>
			<li class="active"><a href="logout">Esci</a></li>
		</ul>
	</div>
</body>
</html>