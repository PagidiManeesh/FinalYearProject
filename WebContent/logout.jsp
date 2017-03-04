<!-- Logout page -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script type="text/javascript" src="check.js"></script>
 
 
 <link rel="shortcut icon" href="icon.jpg">

<title>Signout</title>
</head>
<body >
<% session.invalidate();%>
<center>
<h1>You are currently Signed out<br>
<a href="http://localhost:8080/FinalYear">Login</a> to continue..
</h1>
<hr>
</center>
  </body>
</body>
</html>