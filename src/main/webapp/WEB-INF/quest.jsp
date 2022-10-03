<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>
<div class="container">
    <h5>${requestScope.quest.text}</h5>
    <c:forEach var="question" items="${requestScope.quest.questions}">
        <img src="images/${question.image}" width="10%" >
        <form class="row row-cols-lg-auto g-3 align-items-center">
                <input class="w-25" id="question-image" name="image" type="file">
                <input class="w-50" type="text" id="question-text" value="${question.text}">
                <button type="submit" class="btn btn-primary w-25">Submit</button>
        </form>
    </c:forEach>
</div>
<c:import url="parts/footer.jsp"/>

