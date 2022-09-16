<%--
  Created by IntelliJ IDEA.
  User: drege
  Date: 8/24/2022
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <form class="form-horizontal" action="node?id=${requestScope.node.id}" method="post">
            <fieldset>

                <!-- Form Name -->
                <legend>Node Form</legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="id">Id</label>
                    <div class="col-md-4">
                        <input id="id" name="id" type="text" placeholder="" class="form-control input-md"
                        value="${requestScope.node.id}">
                    </div>
                </div>

                <!-- Textarea -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="question">Question</label>
                    <div class="col-md-4">
                        <textarea class="form-control" id="question" name="question">${requestScope.node.question}</textarea>
                    </div>
                </div>

                <!-- Multiple Radios -->
                <div class="form-group">
                    <%--@declare id="radios"--%><label class="col-md-4 control-label" for="radios"></label>
                    <div class="col-md-4">
                        <div class="radio">
                            <label for="radios-0">
                                <input type="radio" name="radios" id="radios-0" value="1" checked="checked">
                                Option one
                            </label>
                        </div>
                        <div class="radio">
                            <label for="radios-1">
                                <input type="radio" name="radios" id="radios-1" value="2">
                                Option two
                            </label>
                        </div>
                        <div class="radio">
                            <label for="radios-2">
                                <input type="radio" name="radios" id="radios-2" value="3">
                                Option three
                            </label>
                        </div>
                    </div>
                </div>

                <!-- Button (Double) -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="next"></label>
                    <div class="col-md-8">
                        <button id="next" name="next" class="btn btn-success">Next</button>
                        <button id="reset" name="reset" class="btn btn-danger">Reset</button>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>
