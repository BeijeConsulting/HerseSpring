<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LIST USERS</title>
</head>
<body>

<c:forEach items="${users}" var="user"><%-- for (User user : users)  --%>
<p>
email : ${user.email}<br/>
name : ${user.name}<br/>
surname : ${user.surname}
</p>
</c:forEach>

</body>
</html>