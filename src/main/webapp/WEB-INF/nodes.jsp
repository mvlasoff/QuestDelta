<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="node" items="${requestScope.nodes}">
        <li>${node}</li>
    </c:forEach>
</ul>
</body>
</html>