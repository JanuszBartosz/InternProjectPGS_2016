<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
	<title>Title</title>
</head>
<body>

<h2>Awards</h2>
<form:form modelAttribute="map">
<table>
   <c:forEach items="${map}" var="mapElement">
      <tr>
           <td><a href="http://localhost:8080/available_awards?category=${mapElement.key}">${mapElement.key}</a></td>
           <c:forEach items="${mapElement.value}" var="awardDTO" >
              <td>
              	${awardDTO.name} 
              	<br/>
              	${awardDTO.pointsPrice}
              	<br/>
              	<sec:authorize access="hasAuthority('USER')">
					<button form="awardDTO">Order</button> 
				</sec:authorize>                   
              </td>                      
           </c:forEach>
      </tr>
   </c:forEach>
</table>
<br/>
</form:form>
</body>
</html>