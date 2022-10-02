<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="WEB-INF/parts/header.jsp"%>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">
        <div class="col-10 col-sm-8 col-lg-6">
            <img src="${pageContext.request.contextPath}/images/welcome.png" class="d-block mx-lg-auto img-fluid" alt="Bootstrap Themes" width="700" height="500" loading="lazy">
        </div>
        <div class="col-lg-6">
            <h1 class="display-5 fw-bold lh-1 mb-3">Hello, there!</h1>
            <p class="lead">This website is made for training purpose.
                Login/Sign-up first. Only ADMIN or USER can play.
                Choose your quest, think, learn, have fun.</p>
        </div>
    </div>
</div>

<%@ include file="WEB-INF/parts/footer.jsp"%>