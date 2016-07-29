<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h1>
		<spring:message code="user.register" />
	</h1>
	<form:form modelAttribute="userDTO" method="POST" action="/register">
		<form:errors path="" element="div" />
		<div>
			<form:label path="email">
				<spring:message code="user.email" />
			</form:label>
			<form:input path="email" />
			<form:errors path="email" />
		</div>
		<div>
			<input type="submit" />
		</div>
	</form:form>
	<a href="/"> <spring:message code="go.index" /></a>
</body>
</html>