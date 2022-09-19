<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${not empty requestScope.error}">
    <div class="container d-flex flex-wrap justify-content-center">
            <span class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
                <span class="alert alert-warning" role="alert">
                        ${requestScope.error}
                </span>
            </span>
    </div>
</c:if>

<div class="container">
    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 link-secondary">Home</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">Квесты</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">Создать</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">Играть</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">Статистика</a></li>
        </ul>
        <p class="text-center text-muted">© 2022 Java Rush University, Inc. Group Delta.</p>
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>