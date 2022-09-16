<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <ul>
        <c:forEach var="user" items="${requestScope.users}">
            <li>
                <img src="images/${user.image}" width="100" alt="${user.image}">
                    ${user.role}: <a href="user?id=${user.id}">${user.login}</a>
            </li>
            <br>
        </c:forEach>
        <li><a href="user?id=0">Create new user</a></li>
    </ul>
</div>
<%@include file="parts/footer.jsp" %>

