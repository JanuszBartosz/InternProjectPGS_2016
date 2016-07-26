<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<body>
	<h1>
		<spring:message code="change_password.main" />
	</h1>
	<form:form modelAttribute="passwordDTO" method="POST"
		action="/change_password">
		<form:errors path="" element="div" />
		<div>
			<form:label path="oldPassword">
				<spring:message code="change_password.old_password" />
			</form:label>
			<form:password path="oldPassword" />
			<form:errors path="oldPassword" />
		</div>
		<div>
			<form:label path="newPassword">
				<spring:message code="change_password.new_password" />
			</form:label>
			<form:password path="newPassword" />
			<form:errors path="newPassword" />
		</div>
		<div>
			<form:label path="newPasswordRepeat">
				<spring:message code="change_password.new_password_repeat" />
			</form:label>
			<form:password path="newPasswordRepeat" />
			<form:errors path="newPasswordRepeat" />
		</div>
		<div>
			<input type="submit" />
		</div>
	</form:form>
	 <sec:authorize access="hasAuthority('USER')">
		<a href="/main"> <spring:message code="go.main" /></a>
	 </sec:authorize>
</body>
</html>