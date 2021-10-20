<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LIST ORDERS</title>
</head>
<body>

<c:forEach items="${orders}" var="order">
<p>
id : ${order.id}<br/>
user id : ${order.userId}<br/>
amount : ${order.amount}<br/>
date : ${order.dateTime}
</p>
</c:forEach>

</body>
</html>