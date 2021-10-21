<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prodotti</title>
</head>
<body>
${carrelloError}<br><br>
<form action="addCarrello" method="post">
<c:forEach items="${products}" var ="Products">
	<c:choose>
		<c:when test="${Products.getQuantity()<10}">
			<input type="number" min="0" max="${Products.getQuantity()}" value="0" style="width: 40px" name="<c:out value = "${Products.getId()}"/>" >
		</c:when>
		<c:otherwise>
			<input type="number" min="0" max="10" value="0" style="width: 40px" name="<c:out value = "${Products.getId()}"/>" >
		</c:otherwise>
	</c:choose>
	<label for= "<c:out value = "${Products.getId()}"/>"><c:out value = "${Products}"/></label><br>
</c:forEach>
<br><input type="submit" value="Aggiungi al Carrello">
</form><br>

<c:if test="${authUser == null}">
	<form action="redirectLogIn" method="post">
		<input type="submit" value="Log in">
	</form>
	<form action="registration" method="post">
		<input type="submit" value="Registrati">
	</form>
</c:if>

<form action="backhome" method="post">
	<input type="submit" value="Home">
</form>
</body>
</html>