<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <jsp:useBean id="user" scope="session" type="ua.com.javarush.quest.khmelov.dto.UserDto"/>
    <form class="form-horizontal" action="profile" method="post" enctype="multipart/form-data">
        <fieldset>
            <!-- Form Name -->
            <legend>User Form</legend>

            <!-- Avatar input-->
            <div class="form-group">
                <img src="images/${user.image}" width="100" alt="${user.image}">
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userLogin">Login</label>
                <div id="userLogin" class="col-md-4">
                    ${user.login}
                </div>
            </div>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userRole">Role</label>
                <div id="userRole" class="col-md-4">
                    ${user.role}
                </div>
            </div>

            <!-- Button -->
            <div class=" form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="Edit"
                            class="btn btn-success">Edit
                    </button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<%@include file="parts/footer.jsp" %>

