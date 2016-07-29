<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./header.jsp" %>
<body>
	<div class="container">
	<form:form modelAttribute="userDTO" method="POST" action="/register">
	<h1>
		<spring:message code="user.register" />
	</h1>
		<form:errors path="" element="div" />
		<div class="form-group">
			<form:label path="email">
				<spring:message code="user.email" />
			</form:label>
			<form:input cssClass="form-control" path="email" />
			<form:errors path="email" />
		</div>
		<div class="form-group">
			<form:label path="password">
				<spring:message code="user.password" />
			</form:label>
			<form:password cssClass="form-control" path="password" />
			<form:errors path="password" />
		</div>
		<div class="form-group">
			<form:label path="passwordRepeated">
				<spring:message code="user.password_repeated" />
			</form:label>
			<form:password cssClass="form-control" path="passwordRepeated" />
			<form:errors path="passwordRepeated" />
		</div>
		<div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	<a href="/"> <spring:message code="go.index" /></a>
	</form:form>
	</div>
</body>
</html>