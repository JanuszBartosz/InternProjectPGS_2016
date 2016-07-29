<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./header.jsp" %>
<body>
<div class="container">
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		<font color="red"> Your login attempt was not successful due to
			<br />
		<br /> <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /> <c:remove
				var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
		</font>
	</c:if>
	<form:form modelAttribute="loginForm" method="POST" cssClass="form-signin" id="">
	<h2 class="form-signin-heading">Please sign in</h2>
	<form:input cssClass="form-control" type="email" path="email" id="inputEmail" />
	<form:input cssClass="form-control" type="password" path="password" id="password" />
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<label for="inputEmail" class="sr-only">Email address</label> -->
<%-- 				<td></td> --%>
<%-- 				<td><form:errors path="email" /> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>Password:</td> -->
<%-- 				<td><form:input type="password" path="password" /></td> --%>
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 		<input type="submit" name="submit" value="Submit"> -->

	</form:form>
	<a href="/"> <spring:message code="go.index" /></a>
</div>
</body>
</html>