<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./header.jsp" %>
<body>
<div class="container">
	<form:form modelAttribute="cardNotLoggedDTO" method="POST"
		action="/card">
		<h1>
			<spring:message code="card.main_notlogged" />
		</h1>
		<form:errors path="" element="div" />
		<div class="form-group">
			<form:label path="number">
				<spring:message code="card.number" />
			</form:label>
			<form:input cssClass="form-control" path="number" />
		</div>
		<div class="form-group">
			<form:label path="email">
				<spring:message code="card.email" />
			</form:label>
			<form:input cssClass="form-control" path="email" />
		</div>
		<div class="form-group">
			<form:label path="name">
				<spring:message code="card.name" />
			</form:label>
			<form:input cssClass="form-control" path="name" />
		</div>
		<div class="form-group">
			<form:label path="surname">
				<spring:message code="card.surname" />
			</form:label>
			<form:input cssClass="form-control" path="surname" />
		</div>
		<div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	<a href="/"> <spring:message code="go.index" /></a>
	</form:form>
	</div>
	<br>
</body>
</html>