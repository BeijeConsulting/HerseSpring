<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.beije.herse.Ecommerce.Product, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalogo</title>
</head>
<body>

	<h1 align="center">Catalogo Prodotti</h1>

	<p>
		Benvenuto Utente: <strong>${userId}</strong>
	</p>

	<hr>

	<div align="center">
		<h3>I nostri Prodotti</h3>

		<%
		List<Product> products = (List<Product>) session.getAttribute("products");
		%>
		<table Style="border: 1px solid">
			<tr>
				<th>Id</th>
				<th>Prodotto</th>
				<th>Prezzo</th>
				<th>Quantità</th>
			</tr>
			<%
			for (Product p : products) {
				out.print("<tr>");
				out.print("<td>" + p.getId() + "</td>");
				out.print("<td>" + p.getName() + "</td>");
				out.print("<td>" + p.getPrice() + "</td>");
				out.print("<td>" + p.getQuantity() + "</td>");
				out.print("</tr>");
			}
			%>
		</table>

		<form Style="border: 1px solid" action="#" method="post">
			<p>Di quale prodotto vuoi vedere i dettagli? (scegli in base
				all'id)</p>
			<%
			for (Product p : products) {
			%>
			<input type="checkbox" id=<%=p.getId()%> name=<%=p.getId()%>><label
				for="yes"><%=p.getId()%> </label>

			<%
			}
			%>
			<p>
				<input type="submit" value="vedi">
			</p>
		</form>

	</div>

</body>
</html>