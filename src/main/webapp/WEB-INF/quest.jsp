<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <h5>${requestScope.quest.text}</h5>
    <c:forEach var="question" items="${requestScope.quest.questions}">
        <form class="row row-cols-lg-auto g-3 align-items-center">
            <div class="col-12">
                <img src="${question.image}">
                <!-- Avatar input-->
                    <label class="col-md-4 control-label" for="question-image">Изображение</label>
                    <div class="col-md-4">
                        <input id="question-image" name="image" type="file" class="form-control input-md">
                    </div>
            </div>

            <div class="col-12">
                <div class="form-check">
                    <input class="form-check-input" type="text" id="question-text"
                    value="${question.text}">
                    <label class="form-check-label" for="question-text">
                        Текст вопроса
                    </label>
                </div>
            </div>

            <div class="col-12">
                <label class="visually-hidden" for="question-state">Тип вопроса</label>
                <select class="form-select" id="question-state">
                    <option selected>Choose...</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
            </div>


            <div class="col-12">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </c:forEach>
</div>
<%@include file="parts/footer.jsp" %>

