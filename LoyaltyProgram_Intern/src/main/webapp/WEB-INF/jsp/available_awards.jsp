<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@include file="./header.jsp" %>
<body>
<form:form modelAttribute="map">
<div style="text-align: center">
<h2>&nbspAwards</h2>
</div>
<table class = "table">
   <c:forEach items="${map}" var="mapElement">
      <tr>
           <td><h3><a class="label label-primary" href="http://localhost:8080/available_awards?category=${mapElement.key}">${mapElement.key}</a></h3></td>
           <c:forEach items="${mapElement.value}" var="awardDTO" >
              <td>
              <div class="alert alert-success" style="width: 230px; height: 230px; left: 50%; text-align: center;">
	              <img class="img-thumbnail" alt="thumb" src="http://lorempixel.com/150/80">
					<div>
	              	<label class="label label-info">${awardDTO.name}</label>
					</div>
	              	<br/>
	              	<label class="featurette-heading">Price:&nbsp</label><span class="text-muted">${awardDTO.pointsPrice}</span>
	              	<br/>
	              	<label class="featurette-heading">Amount:&nbsp</label><span class="text-muted">${awardDTO.stockAmount}</span>
	              	<br/>
	              	<sec:authorize access="hasAuthority('USER')">
						<button type="button" class="btn btn-sm btn-danger" form="awardDTO">Order</button> 
					</sec:authorize>                   
              </div>
              </td>                      
           </c:forEach>
      </tr>
   </c:forEach>
</table>
<br/>
</form:form>
</body>
</html>