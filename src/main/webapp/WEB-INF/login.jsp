<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="parts/header.jsp" %>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">

        <main class="text-center form-signin w-100 m-auto">
            <form action="log-in" method="post">
                <c:if test="${sessionScope.user == null}">
                    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <h1 class="h3 mb-3 fw-normal">Welcome, ${sessionScope.user.login}</h1>
                    <h1 class="h3 mb-3 fw-normal">Role, ${sessionScope.user.role}</h1>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <div class="form-floating">
                        <input type="text" class="form-control" name="login" id="floatingInput" placeholder="Username">
                        <label for="floatingInput">Login</label>
                    </div>
                    <div class="form-floating">
                        <input type="password" class="form-control" name="password" id="floatingPassword"
                               placeholder="Password">
                        <label for="floatingPassword">Password</label>
                    </div>
                    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>

                </c:if>
            </form>
        </main>


    </div>
</div>

<%@ include file="parts/footer.jsp" %>