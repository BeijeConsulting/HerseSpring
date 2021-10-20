<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <% if(session.getAttribute("user")==null){ response.sendRedirect("./login"); } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BENVENUTI PAGE</title>
</head>
<style>input{background-color:lightblue;}</style>
<body style="background-color:lightgrey;">

<a href="logout"><p align="right"> Logout </p></a>
<h3>BENVENUT* ${user}</h3>

<ul>
<c:forEach items="${products}" var="p">
<li> ${p.name},${p.description}</li>
</c:forEach>
</ul>
</body>
</html>