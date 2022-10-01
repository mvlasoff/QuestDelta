<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                <use xlink:href="#bootstrap"></use>
            </svg>
        </a>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 link-secondary">Home</a></li>
            <li>
                <div class="dropdown">
                    <a class="btn btn-outline-primary me-2" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Quests
                    </a>
                    <c:if test='${sessionScope.user != null && sessionScope.user.role != "GUEST"}'>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="space-quest">Space Quest</a></li>
                            <li><a class="dropdown-item" href="java-quest">Java Quest</a></li>
                        </ul>
                    </c:if>
                </div>
            </li>
            <c:if test='${sessionScope.user != null && sessionScope.user.role != "GUEST"}'>
                <li><a href="stat" class="nav-link px-2 link-secondary">Statistics</a></li>
            </c:if>
        </ul>
        <div class="col-md-3 text-end">
            <c:if test="${sessionScope.user == null}">
                <a class="btn btn-outline-primary me-2" href="log-in" role="button" aria-expanded="false">
                    Login
                </a>
                <a class="btn btn-primary me-2" href="sign-up" role="button" aria-expanded="false">
                    Sign-up
                </a>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <a class="btn btn-outline-primary me-2" href="log-in" role="button" aria-expanded="false">
                    Profile
                </a>
                <a class="btn btn-outline-primary me-2" href="log-out" role="button" aria-expanded="false">
                    Logout
                </a>
            </c:if>
        </div>
    </header>
</div>
