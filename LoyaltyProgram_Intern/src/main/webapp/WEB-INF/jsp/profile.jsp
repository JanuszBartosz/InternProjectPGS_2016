<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h1>
		<spring:message code="profile.main" />
	</h1>
	<form:form modelAttribute="userProfileDTO" method="POST"
		action="/profile">
		<form:errors path="" element="div" />
		<div>
			<form:label path="name">
				<spring:message code="profile.name" />
			</form:label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<div>
			<form:label path="surname">
				<spring:message code="profile.surname" />
			</form:label>
			<form:input path="surname" />
			<form:errors path="surname" />
		</div>
		<div>
			<form:label path="city">
				<spring:message code="profile.city" />
			</form:label>
			<form:input path="city" />
		</div>
		<div>
			<form:label path="street">
				<spring:message code="profile.street" />
			</form:label>
			<form:input path="street" />
		</div>
		<div>
			<form:label path="homeNumber">
				<spring:message code="profile.home_number" />
			</form:label>
			<form:input path="homeNumber" />
		</div>
		<div>
			<form:label path="postCode">
				<spring:message code="profile.post_code" />
			</form:label>
			<form:input path="postCode" />
		</div>
		<div>
			<spring:message code="profile.hobbies" />
			<form:checkboxes items="${hobbiesNames}" path="hobbies" />	
		</div>
		<div>
			<input type="submit" />
		</div>
	</form:form>

	<a href="/main"> <spring:message code="profile.go_main" /></a>
</body>
</html>