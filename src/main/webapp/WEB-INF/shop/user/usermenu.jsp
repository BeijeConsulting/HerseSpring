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
    
    <jsp:useBean id="loggedUser" class="it.beije.herse.entity.User" scope="session"></jsp:useBean>
    <jsp:setProperty property="email" name="loggedUser"/>
    <jsp:setProperty property="password" name="loggedUser"/>
    <jsp:setProperty property="name" name="loggedUser"/>
    <jsp:setProperty property="surname" name="loggedUser"/>
    <jsp:setProperty property="id" name="loggedUser"/>
    
    <h1>HERSE SHOP</h1>
    
    <h2>WELCOME 
    <c:choose>
    	<c:when test="${not empty loggedUser.name && not empty loggedUser.surname}">
    		 <jsp:getProperty property="name" name="loggedUser"/> <jsp:getProperty property="surname" name="loggedUser"/>
    	</c:when>
    	<c:otherwise>
    		<jsp:getProperty property="email" name="loggedUser"/>
    	</c:otherwise>
    </c:choose>
   	</h2>
    
    <form action="../user/menu" method="post">
        CHOOSE AN OPTON:<br>
        <input type="radio" name="userAction" value="showProfile">SHOW MY PROFILE<br>
        <input type="radio" name="userAction" value="showOrderHistory">SHOW MY ORDER HISTORY<br>
        <input type="radio" name="userAction" value="newOrder">NEW ORDER<br>
        <input type="radio" name="userAction" value="updateProfile">UPDATE PROFILE<br>
        <br>
        <input type=submit name="submitUserAction" value="SUBMIT">
        <br><br>
        <input type=submit name="exit" value="EXIT">
    </form>
     
</body>
    
</html>