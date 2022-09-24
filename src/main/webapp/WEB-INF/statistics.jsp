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
    <div class="list-group w-auto">
        <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
            <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32"
                 class="rounded-circle flex-shrink-0">
            <div class="d-flex gap-2 w-100 justify-content-between">
                <div>
                    <h6 class="mb-0">Games played: ${sessionScope.gamesplayed}</h6>
                    <p class="mb-0 opacity-75">...</p>
                </div>
                <small class="opacity-50 text-nowrap">now</small>
            </div>
        </a>
    </div>
</div>

<%@ include file="parts/footer.txt" %>
