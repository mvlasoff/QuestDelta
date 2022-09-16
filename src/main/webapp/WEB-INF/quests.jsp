<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <h1>Quests</h1>
    <ul>
        <c:forEach var="quest" items="${requestScope.quests}">
            <li>
                Quest: <a href="quest?id=${quest.id}">${quest.name}</a>
            </li>
            <br>
        </c:forEach>
        <li><a href="create">Create new quest</a></li>
    </ul>
</div>
<%@include file="parts/footer.jsp" %>

