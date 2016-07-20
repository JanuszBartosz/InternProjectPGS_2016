<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <h1><spring:message code="card.main_notlogged" /></h1>
    <form:form modelAttribute="cardNotLoggedDTO" method="POST" action="/card">
        <form:errors path="" element="div" />
        <div>
            <form:label path="number"><spring:message code="card.number" /></form:label>
            <form:input path="number" />
        </div>
        <div>
            <form:label path="email"><spring:message code="card.email" /></form:label>
            <form:input path="email" />
        </div>
          <div>
            <form:label path="name"><spring:message code="card.name" /></form:label>
            <form:input path="name" />
        </div>
          <div>
            <form:label path="surname"><spring:message code="card.surname" /></form:label>
            <form:input path="surname" />
        </div>
        <div>
            <input type="submit" />
        </div>
    </form:form>
    <a href="/"> <spring:message code="card.go_index" /></a><br>
</body>
</html>