<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="parts/header.jsp" %>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">
        <table class="table">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Games Played</th>
                <th scope="col">Games Won</th>
                <th scope="col">Games Lost</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">Space Quest</th>
                <td>${requestScope.spacegame.gamesCount}</td>
                <td>${requestScope.spacegame.gamesWon}</td>
                <td>${requestScope.spacegame.gamesCount - requestScope.spacegame.gamesWon}</td>
            </tr>
            <tr>
                <th scope="row">Java Quest</th>
                <td>${requestScope.javagame.gamesCount}</td>
                <td>${requestScope.javagame.gamesWon}</td>
                <td>${requestScope.javagame.gamesCount - requestScope.javagame.gamesWon}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>
