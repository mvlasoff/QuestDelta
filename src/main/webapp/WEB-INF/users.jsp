<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <c:forEach var="user" items="${requestScope.users}">
        <img src="images/${user.image}" width="100" alt="${user.image}">
        ${user.role}: <a href="edit-user?id=${user.id}">${user.login}</a>
        <br>
    </c:forEach>
    <p><a href="signup">Create new user</a>
</div>
<%@include file="parts/footer.jsp" %>

