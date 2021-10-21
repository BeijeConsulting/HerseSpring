<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordine</title>
</head>
<body>

<h1 align ="center"> Grazie per aver effettuato l'ordine </h1>

<hr>

<h3> Ecco il riepilogo del tuo ordine</h3>

<p>Il tuo ordine:</p> ${FinalOrder} <br>

<p>I tuoi orderItem collegati all'ordine </p>
	<c:forEach items="${ordersToPrint}" var="o">
		<p>${o}</p>
	</c:forEach>

${ciao}
</body>
</html>