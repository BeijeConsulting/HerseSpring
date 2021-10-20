<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="it.beije.herse.entity.User"%>
<%@page import="it.beije.herse.entity.Product"%>
<%@page import="it.beije.herse.shop.Carrello"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
</head>
<body>

	<a href='logout' ><input type='button' value='logout'></a>
	
	<h2>
		Il carrello di
		<%=((User) session.getAttribute("user")).getName()%></h2>

	<ul>

		<li>
			<form action='delete' method='post'>
				<label for='<%= p.getName() %>'> <%= p.getPrice() %> € (quantità: <%= quantita %>, totale parziale: <%= p.getPrice()*quantita %>)</label>
				<input type='submit' value='rimuovi' /> <input type='hidden' name='idP' value='<%= p.getId() %>'> 
			</form>
		</li>

	</ul>
	<br><p><strong>Il totale è <%= total %> €</strong></p>
	<%
	String disabled = "";
//	int size = carrello.getMappa().size();
//	if(size==0)
//		disabled = "disabled";
	%>
	<form action='pay' method='post'>
	  <br>
	   <a href='catalogo.jsp'><input type='button' value='Torna al catalogo'/></a>
	   <input type='submit' value="Completa l'acquisto" <%= disabled %>/>
	</form>
	<%//session.setAttribute("amount", total); %>

</body>
</html>