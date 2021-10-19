<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>INSERT USER</title>
</head>
<body>
<<<<<<< HEAD
	<c:if test="${error!=null}">
		<p style="color:red">${error}</p>
	</c:if>

=======
${message}<br/>
>>>>>>> refs/remotes/origin/main
	<form action="../user/insert" method="post">
<<<<<<< HEAD
		<label for="username">username:</label><br> <input type="text"
			name="username"><br> <label for="password">password:</label><br>
		<input type="text" name="password"><br> <label
			for="firstName">first name:</label><br> <input type="text"
			name="firstName"><br> <label for="lastName">last
			name:</label><br> <input type="text" name="lastName"><br>
		<br> <input type="submit" value="Submit">
	</form>
=======
	  <label for="email">email:</label><br>
	  <input type="text" name="email"><br>
	  <label for="password">password:</label><br>
	  <input type="text" name="password"><br>
	  <label for="name">name:</label><br>
	  <input type="text" name="name"><br>
	  <label for="surname">surname:</label><br>
	  <input type="text" name="surname"><br><br>
	  <input type="submit" value="Submit">
	</form> 
>>>>>>> refs/remotes/origin/main
</body>
</html>