<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="it.beije.herse.Ecommerce.Carrello, it.beije.herse.entity.Product, it.beije.herse.entity.Order, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo Carrello</title>
</head>
<body>

<h1 align="center">Riepilogo Carrello</h1>

<hr>

<h3> Prodotti acquistati fino ad ora </h3>

<table Style="border:1px solid"  >
<tr><th>Id Prodotto</th><th>Quantità</th></tr>

<%

HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");
List<Product> products = (List<Product>) session.getAttribute("prodRiepilogo");

for (Integer key : map.keySet()) {
	Object obj = map.get(key);
//	Carrello carrello = (Carrello) obj;
	out.print(" Prodotto: ");
	for (Product p : products) {
		if(p.getId() == key){
			out.print("<tr>");
			out.print("<td>" + key + "</td>");
			out.print("<td>" + map.get(key) + "</td>");
			out.print("</tr>");
		}
	}
}
%>

</table>

<form action='catalogo' method='post'>
	
		<p>
			Torna Agli acquisti: <input type="submit" value="Vai">
		</p>
	
</form>

</body>
</html>