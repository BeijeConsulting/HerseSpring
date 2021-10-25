<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <% if(session.getAttribute("user")==null){ response.sendRedirect("./login"); } %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page import="it.beije.herse.entity.Carrello"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BENVENUTI PAGE</title>
</head>
<style>input{background-color:lightblue;}</style>
<body style="background-color:lightgrey;">

<a href="logout"><p align="right"> Logout </p></a>
<h3>BENVENUT* ${user.name}</h3>

<ul>

<c:forEach items="${products}" var="p">

<li> ${p.name}, ${p.price} &#128;</li>

<c:set var="contains" value="false" />
<c:forEach var="item" items="${lista}">

 <c:if test="${lista eq p}">
 
    <p>funziona</p>
    <c:set var="contains" value="true" />
  </c:if>
  
</c:forEach> 
 <c:choose> 
 <c:when test="${contains}">
    
        <form action="carrello/edit" method="post">
         <input type="hidden" name="to_edit" value="${p.id}">
        <input type="number" name="how_many" min="1" max="${p.quantity}" placeholder="nuova quantità">
        <input type="submit" value="Modifica">
        </form>
        
        <form action="carrello/delete_product" method="post">
        <input type="hidden" name="to_del" value="${p.id}">
        <input type="submit" value="Elimina">
        </form>
        
        <br />
  </c:when>    
    <c:otherwise>
       <form action="carrello/add_product" method="post">
       <input type="hidden" name="to_add" value="${p.id}">
       <input type="submit" value="Aggiungi al carrello">
       <input type="number" name="how_many" min="1" max="${p.quantity}" value="1"> quanti ne vuoi?
        <br />
        
     
    </c:otherwise>
</c:choose>
</form>
</c:forEach>


</ul>
</body>
</html>