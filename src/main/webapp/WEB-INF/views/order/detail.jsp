<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ORDER DETAIL</title>
</head>
<body>

<p>
id : ${order.id}<br/>
user id : ${order.userId}<br/>
amount : ${order.amount}<br/>
date : ${order.dateTime}<br/>
<c:forEach items="${order.items}" var="item">
item id : ${item.id}<br/>
</c:forEach>
</p>

</body>
</html>