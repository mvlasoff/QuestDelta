<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <h1>Quests</h1>


    <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
        <c:forEach var="quest" items="${requestScope.quests}">
            <div class="feature col">
                <h3 class="fs-2">${quest.name}</h3>
                <p>Description</p>
                <a href="#" class="icon-link d-inline-flex align-items-center">
                    Играть
                    <svg class="bi" width="1em" height="1em">
                        <use xlink:href="#chevron-right"></use>
                    </svg>
                </a>
            </div>
        </c:forEach>
    </div>

</div>
<%@include file="parts/footer.jsp" %>

