<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>

<body bgcolor="black" text="white">
    
    <h1>HERSE SHOP</h1>
    
    <h3><font color="red">${loginMessage}</font></h3>
     
     <%String action = "login/login"; %>
     
    <c:if test="${loginMessage.length()>0}"><% action = "../login/login"; %></c:if>
    <form action="<%=action%>" method="post">
    	INSERT USER AND PASSWORD<br> 
    	EMAIL: <input type="email" name="email"><br>
    	PASSWORD: <input type="password" name="password"><br><br>
    	<input type=submit name="submitLogin" value="LOGIN">
    </form>
    
</body>
</html>