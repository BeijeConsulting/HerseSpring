<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="it.beije.herse.entity.Product"%>
<%@page import="it.beije.herse.shop.Carrello"%>
<%@page import="it.beije.herse.entity.Product"%>



<%@page import="java.util.*"%>

<%@page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
Integer id = Integer.valueOf((String) request.getParameter("id"));
EntityManager entityManager = ShopEntityManager.newEntityManager();
Product prodotto = entityManager.find(Product.class, id);
%>
<title>Dettaglio <%= prodotto.getName() %></title>
</head>
<body>
<a href='logout' ><input type='button' value='logout'></a>
<h3>Dettaglio prodotto:</h3>
<h4><%= prodotto.getName() %> prezzo <%= prodotto.getPrice() %></h4>
<p><%= prodotto.getDescription() %></p>

<%
Carrello carrello = null;

if(session.getAttribute("carrello")!=null){
	 carrello = (Carrello) session.getAttribute("carrello");
}
	
	int quantita=0;
	

if(carrello!=null && carrello.getMappa().containsKey(prodotto.getId())) {
  quantita = carrello.getMappa().get(prodotto.getId());
}

int tot = prodotto.getQuantity()- quantita;
%>

<form action='carrellos' method='post'>
				<input type='submit' value='aggiungi' /> <input type='hidden' name='idP' value='<%= prodotto.getId() %>'> 
				<input type='number' name='quantita' step='1' min='1' value='1'max='<%= tot %>'>
			</form>
			<a href='catalogo.jsp'><input type ='button'  value ='Torna al catalogo'></a>

</body>
</html>