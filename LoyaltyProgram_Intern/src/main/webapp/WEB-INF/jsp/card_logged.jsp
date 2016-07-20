<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <h1><spring:message code="card.main_logged" /></h1>
    <form:form modelAttribute="cardLoggedDTO" method="POST" action="/main/card">
        <form:errors path="" element="div" />
        <div>
            <form:label path="number"><spring:message code="card.number" /></form:label>
            <form:input path="number" />
        </div>
        <div>
		    <spring:message code="card.active" />
			<form:checkbox path="active" />
		</div>
        <div>
            <input type="submit" />
        </div>
    </form:form>
    <a href="/main"> <spring:message code="card.go_main" /></a><br>
</body>
</html>