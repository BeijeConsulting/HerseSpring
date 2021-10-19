<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>List of registered users</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body>
	<h1>List of registered users</h1>
	
	<c:forEach items="${listUsers}" var = "user">
		<ul>
			<li>
				<ul>
					<li>Email : ${user.email}</li>
					<li>Name : ${user.name}</li>
					<li>Surname : ${user.surname}</li>
				</ul>
			</li>
		</ul>
	</c:forEach>
	
</body>
</html>