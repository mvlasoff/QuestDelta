<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <jsp:useBean id="user" scope="request" type="ua.com.javarush.quest.khmelov.dto.ui.UserDto"/>
    <form class="form-horizontal" action="edit-user?id=${user.id}" method="post" enctype="multipart/form-data">
        <fieldset>
            <!-- Form Name -->
            <legend>User Form</legend>

            <!-- Avatar input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userImage">Login</label>
                <div class="col-md-4">
                    <input id="userImage" name="image" type="file" class="form-control input-md">
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userLogin">Login</label>
                <div class="col-md-4">
                    <input id="userLogin" name="login" type="text" placeholder="set login" class="form-control input-md"
                           required=""
                           value="${user.login}">

                </div>
            </div>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userRole">Role</label>
                <div class="col-md-4">
                    <select id="userRole" name="role" class="form-control">
                        <c:forEach items="${applicationScope.roles}" var="role">
                            <option value="${role}" ${role==user.role?"selected":""}>
                                    ${role}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </div>

            <!-- Button -->
            <div class=" form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="${user.id>0?"update":"create"}"
                            class="btn btn-success">${user.id>0?"Update":"Create"}</button>
                </div>
            </div>

            <c:if test="${requestScope.user.id>0}">
                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="delete"></label>
                    <div class="col-md-4">
                        <button id="delete" name="delete" class="btn btn-danger">Delete</button>
                    </div>
                </div>
            </c:if>

        </fieldset>
    </form>
</div>
<%@include file="parts/footer.jsp" %>

