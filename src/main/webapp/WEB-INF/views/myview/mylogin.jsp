<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

${error}<br/>
	<form action="acess" method="post">
	  <label for="email">email:</label><br>
	  <input type="text" name="email"><br>
	  <label for="password">password:</label><br>
	  <input type="password" name="password"><br><br>
	  <input type="submit" value="Log in">
	</form> <br>
	
	<form action="backhome" method="post">
	<input type="submit" value="home">
	</form>
</body>
</html>