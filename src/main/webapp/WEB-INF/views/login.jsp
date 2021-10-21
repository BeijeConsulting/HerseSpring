<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

<style>
.form {
	text-align: center;
	margin-top: 10%;
}

input[type=text], input[type=password]{
	width: 200px;
	height: 20px;
	
}

input[type=submit] {
	display: block;
	text-decoration: none;
	width: 210px;
	height: 40px;
	color: white;
	border: none;
	background-color: red;
	margin-left: auto;
	margin-right: auto;
}

.space{
	height: 10px;
}
</style>
</head>
<body>
	<div class="form">
		<h2>
			<b>Accedi</b>
		</h2>
		<form action="login" method="post">
			<input type="text" name="email" placeholder="E-mail"><br>
			<div class="space"></div>
			<input type="password" name="password" placeholder="Password"><br>
			<div class="space"></div>
			<input type="submit" value="Submit">
		</form>
	</div>
</body>
</html>