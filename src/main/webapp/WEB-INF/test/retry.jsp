<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TEST RETRY</title>
</head>
<body bgcolor="black" text="white">

	<form action="retry" method="post">
	<input type="radio" name="retryAction" value="back">BACK<br>
	<input type="radio" name="retryAction" value="signIn">SIGN IN AS ${email}<br>
	<br><input type="submit" name="retry" value="SUBMIT">
	</form>
</body>
</html>