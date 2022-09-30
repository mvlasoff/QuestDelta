<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="parts/header.jsp" %>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">


        <main class="text-center form-signin w-100 m-auto">
            <form class="row g-3 needs-validation" action="sign-up" method="post" novalidate>
                <div class="col-md-5">
                    <label for="validationCustom03" class="form-label">Login</label>
                    <input type="text" class="form-control" name="login" id="validationCustom03" required>
                </div>
                <div class="col-md-5">
                    <label for="validationCustom04" class="form-label">Role</label>
                    <select class="form-select" name="role" id="validationCustom04" required>
                        <option selected disabled value="">Choose...</option>
                        <c:forEach var="role" items="${sessionScope.roles}">
                            <option value="${role}">${role}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-5">
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="password1" id="floatingPassword1"
                           placeholder="Password" required>
                    <p style="color: crimson">${requestScope.message}</p>
                </div>
                <div class="col-md-5">
                    <label class="form-label">Repeat Password</label>
                    <input type="password" class="form-control" name="password2" id="floatingPassword2"
                           placeholder="Password" required>
                </div>
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">Submit form</button>
                </div>
            </form>
        </main>


    </div>
</div>

<%@ include file="parts/footer.jsp" %>