<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login form</title>
</head>
<body>
	<form:form modelAttribute="loginForm" method="POST" >
	<table>
        <tr>
            <td>Email:</td>
            <td><form:input type="text" path="email" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:input type="password" path="password" /></td>
        </tr>
    </table>
    <input type="submit" name="submit" value="Submit">
	</form:form>
</body>
</html>