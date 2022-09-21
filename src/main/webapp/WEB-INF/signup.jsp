<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <form class="form-horizontal" action="signup" method="post" enctype="multipart/form-data">
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
                           value="">

                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userPassword">Password</label>
                <div class="col-md-4">
                    <input id="userPassword" name="password" type="password" placeholder="pass req"
                           class="form-control input-md" required=""
                           value="">

                </div>
            </div>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userRole">Role</label>
                <div class="col-md-4">
                    <select id="userRole" name="role" class="form-control">
                        <c:forEach items="${applicationScope.roles}" var="role">
                            <option value="${role}" ${role=="USER"?"selected":""}>
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
                    <button id="submit" name="create"
                            class="btn btn-success">Create</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
<%@include file="parts/footer.jsp" %>

