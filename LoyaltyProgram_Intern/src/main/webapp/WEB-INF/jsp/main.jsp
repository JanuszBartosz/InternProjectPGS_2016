<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h1>Logged as:   ${loggedUser.email}</h1>
	<h3>
		<a href="/profile"> <spring:message code="main.profile" /></a><br>
		<a href="/main/card"> <spring:message code="main.card" /></a><br>
		<a href="/change_password"> <spring:message code="main.change_password" /></a><br>
		<a href="/contact"> <spring:message code="main.contact" /></a><br>
		<a href="/logout"> <spring:message code="main.logout" /></a><br>
	</h3>
</body>
</html>