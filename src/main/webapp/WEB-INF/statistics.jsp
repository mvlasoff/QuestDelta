<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="parts/header.txt" %>

<div class="container">
    <header class="d-flex justify-content-center py-3">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="${pageContext.request.contextPath}/" class="nav-link">Home</a></li>
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false">
                    Quests
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="space-quest">Space Quest</a></li>
                    <li><a class="dropdown-item" href="java-quest">Java Quest</a></li>
                </ul>
            </div>
            <li class="nav-item"><a href="/stat" class="nav-link">Statistics</a></li>
        </ul>
    </header>
</div>

<div class="container col-xxl-8 px-4 py-5">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Game.</th>
            <th scope="col">Games Played.</th>
            <th scope="col">Games Won.</th>
            <th scope="col">Games Lost.</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">Space Quest.</th>
            <td>${sessionScope.gamesplayed}</td>
            <td>...</td>
            <td>...</td>
        </tr>
        <tr>
            <th scope="row">Java Quest.</th>
            <td>${sessionScope.gamesplayed}</td>
            <td>...</td>
            <td>...</td>
        </tr>
        </tbody>
    </table>
</div>

<%@ include file="parts/footer.txt" %>
