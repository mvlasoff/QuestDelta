<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="parts/header.jsp" %>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">
        <div class="col-10 col-sm-8 col-lg-6">
            <c:if test="${requestScope.question.picture != null}">
                <img src="${requestScope.question.picture}" class="d-block mx-lg-auto img-fluid"
                     alt="Bootstrap Themes" width="700" height="500" loading="lazy">
            </c:if>
            <c:if test="${requestScope.question.picture == null}">
                <img src="/images/img.png" class="d-block mx-lg-auto img-fluid"
                     alt="Bootstrap Themes" width="700" height="500" loading="lazy">
            </c:if>
        </div>
        <div class="col-lg-6">

            <h1 class="display-5 fw-bold lh-1 mb-3">Space Quest.</h1>
            <p class="lead">${requestScope.question.text}</p>

            <ul class="icon-list ps-0">
                <c:if test="${!requestScope.end}">
                    <form>
                        <c:forEach var="answer" items="${requestScope.answers}">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="id" id="flexRadioDefault1"
                                       value="${answer.id}">
                                <label class="form-check-label" for="flexRadioDefault1">
                                        ${answer.text}
                                </label>
                            </div>
                        </c:forEach>
                        <div  class="d-grid gap-2 d-md-flex justify-content-md-start">
                            <button class="btn btn-primary" type="submit">Submit</button>
                        </div>
                    </form>
                </c:if>
            </ul>

            <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                <c:if test="${requestScope.end}">
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/space-quest" role="button">Restart</a>
                </c:if>
            </div>
        </div>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>