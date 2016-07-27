<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Spring MVC and List Example</h2>

		<ul>
			<c:forEach items="${map}" var="mapValue" >
				<li>${mapValue}</li>
			</c:forEach>
		</ul>


</body>
</html>