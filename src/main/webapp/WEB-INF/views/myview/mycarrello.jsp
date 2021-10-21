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
<c:if test = "${carrello.getOrderItems().size()>0}">
	<form action="aggiornaCarrello" method="post">
		<c:forEach items="${products}" var ="Products">
		<c:forEach items="${carrello.getOrderItems()}" var = "OrderItem">
			<c:if test = "${Products.getId() == OrderItem.getProductId()}">
				<c:choose>
					<c:when test="${Products.getQuantity() < 10}">
						<input type="number" min="0" max = "${Products.getQuantity()}" value="${OrderItem.getQuantity()}" style="width: 40px" name="${Products.getId()}">
					</c:when>
					<c:otherwise>
						<input type="number" min="0" max="10" value="${OrderItem.getQuantity()}" style="width: 40px" name="<c:out value = "${Products.getId()}"/>">
					</c:otherwise>
				</c:choose>
				<label for= "<c:out value = "${Products.getId()}"/>"><c:out value = "${Products}"/></label><br>
			</c:if>
		</c:forEach>
		</c:forEach><br>
		<input type = "submit" value="Aggiorna">
	</form><br>

	<form action="confirmation" method="post">
		<input type="submit" value="Acquista">
	</form><br>
	
	<form action="goProdotti" method="post">
		<input type="submit" value="Prodotti">
	</form>
</c:if>
<c:if test="${carrello.getOrderItems().size()==0}">
Seleziona prima qualche prodotto dalla nostra lista: <br><br>

<form action="goProdotti" method="post">
	<input type="submit" value="Prodotti">
</form>
</c:if>

<c:if test="${carrello == null}">
	Effettua prima il<br>
	<form action="redirectLogIn" method="post">
		<input type="submit" value="Log in">
	</form><br><br>
	oppure<br>
	<form action="registration" method="post">
		<input type="submit" value="Registrati">
	</form>
</c:if>

<br><br>${errorCarEmpty}
<br><br>${errorCar}
<br><br>${errorAuth}

</body>
</html>