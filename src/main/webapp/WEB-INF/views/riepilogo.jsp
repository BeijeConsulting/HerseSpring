<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo</title>
<style>
.showOrder ul {
	border-bottom: 1px solid black;
}

.myli {
	background-color: white;
	height: 30px;
}

.myli li {
	width: 200px;
}

.myli-float {
	float: right;
}

.myli-float input {
	width: 50px;
	height: 25px;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	
	<div class="showOrder">
		${htmlEl}
	</div>
</body>
</html>