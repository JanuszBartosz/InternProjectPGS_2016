<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./header.jsp" %>
<body>
<div class="container">
	<form:form modelAttribute="cardLoggedDTO" method="POST"
	<h1>
		<spring:message code="card.main_logged" />
	</h1>
		action="/main/card">
		<form:errors path="" element="div" />
		<div class="form-group">
			<form:label path="number">
				<spring:message code="card.number" />
			</form:label>
			<form:input cssClass="form-control" path="number" />
		</div>
		<div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	<a href="/main"> <spring:message code="go.main" /></a>
	</form:form>
	</div>
</body>
</html>