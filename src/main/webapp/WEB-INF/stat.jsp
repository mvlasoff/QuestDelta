<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>
<div class="container">
    <c:forEach var="line" items="${requestScope.statistics}">
        ${line}
        <hr>
    </c:forEach>
    <form action="/stat" method="post">
        <!-- Button -->
        <div class=" form-group">
            <label class="col-md-4 control-label" for="submit"></label>
            <div class="col-md-4">
                <button id="submit" name="save"
                        class="btn btn-success">Сохранить состояние базы данных
                </button>
            </div>
        </div>
    </form>
</div>
<c:import url="parts/footer.jsp"/>

