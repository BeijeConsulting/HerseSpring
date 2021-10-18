<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TEST RESULT</title>
</head>
<body>
	
	<h1>WELCOME <% Model model;
	if(model.getAttribute("name")!=null) {%> ${name} <%} 
	else{ %> ${email} <%} %></h1>
</body>
</html>