<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h2>${message}</h2>
	<h3>
		<a href="/login"> <spring:message code="index.login" /></a><br>
		<a href="/register"> <spring:message code="index.register" /></a><br>
		<a href="/card"> <spring:message code="index.card" /></a><br>
	</h3>
</body>
</html>