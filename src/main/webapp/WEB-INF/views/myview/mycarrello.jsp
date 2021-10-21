<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
</head>
<body>
<form action="shopProdotti" method="get">
	<c:forEach items="${carrello.getOrderItems()}" var ="OrderItems">
		<c:choose>
			<c:when test="${Products.getQuantity()<10}">
				<input type="number" min="0" max="${Products.getQuantity()}" value="${OrderItems.getQuantity()}" style="width: 40px" name="<c:out value = "${Products.getId()}"/>" >
			</c:when>
			<c:otherwise>
				<input type="number" min="0" max="10" value="${OrderItems.getQuantity()}" style="width: 40px" name="<c:out value = "${Products.getId()}"/>" >
			</c:otherwise>
		</c:choose>
		<label for= "<c:out value = "${Products.getId()}"/>"><c:out value = "${Products}"/></label><br>
	</c:forEach>
	<input type = "submit" value="Acquista">
</form>
</body>
</html>