<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact form</title>
</head>
<body>
<h2>Contact form</h2>
<form:form modelAttribute="contactMessageDTO" method="POST" action="/contact">
   <table>
    <tr>
        <td><form:label path="email">Email</form:label></td>
        <td><form:input path="email" /></td>
    </tr>
    <tr>
        <td><form:label path="subject">Subject</form:label></td>
        <td><form:input path="subject" /></td>
    </tr>
    <tr>
        <td><form:label path="message">Message</form:label></td>
        <td><form:textarea path="message" rows="5" cols="30" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>