<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logged</title>
</head>
<body>
<h1> Hello world!  ${message} </h1>

<table border="1">

<tr><td>ID</td><td> ${loggedUser.id}</td></tr>

<tr><td>Email</td><td> ${loggedUser.email}</td></tr>

</table>
</body>
</html>