<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Dashboard Template for Bootstrap</title>
        <!-- Bootstrap core CSS -->
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="container-fluid">
            <h1 class="page-header">Traffic Light Management</h1>
            <div class="row">
                <div class="col-md-3">
                    <c:if test="${not empty crossroads}">
                        <ul class="nav nav-sidebar">
                            <c:forEach var="crossroad" items="${crossroads}">
                                <li><a href="${crossroad}">${crossroad}</a></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>
                <div class="col-md-9">
                    <c:if test="${not empty crossroad}">
                        <h2>${crossroad}</h2>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="redTransactionTime" class="col-sm-2 control-label">RedTransactionTime</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="redTransactionTime">
                                </div>
                            </div>
                            <hr>
                            <div class="group">
                                <p>Group</p>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">GreenTime</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group trafficLight">
                                    <label class="col-sm-2 control-label">Traffic Light</label>
                                    <div class="col-sm-10">
                                        <div class="input-group">
                                            <input type="text" class="form-control">
                                            <span class="input-group-btn">
                                                <a class="btn btn-default deleteTrafficLight" type="button">-</a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group trafficLight">
                                    <label class="col-sm-2 control-label">Traffic Light</label>
                                    <div class="col-sm-10">
                                        <div class="input-group">
                                            <input type="text" class="form-control">
                                            <span class="input-group-btn">
                                                <a class="btn btn-default deleteTrafficLight" type="button">-</a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <a class="btn btn-default addTrafficLight">Add Traffic Light</a>
                                        <a class="btn btn-default deleteGroup">Delete Group</a>
                                    </div>
                                </div>
                            </div>

                            <div class="group">
                                <p>Group</p>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">GreenTime</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group trafficLight">
                                    <label class="col-sm-2 control-label">Traffic Light</label>
                                    <div class="col-sm-10">
                                        <div class="input-group">
                                            <input type="text" class="form-control">
                                            <span class="input-group-btn">
                                                <a class="btn btn-default deleteTrafficLight" type="button">-</a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group trafficLight">
                                    <label class="col-sm-2 control-label">Traffic Light</label>
                                    <div class="col-sm-10">
                                        <div class="input-group">
                                            <input type="text" class="form-control">
                                            <span class="input-group-btn">
                                                <a class="btn btn-default deleteTrafficLight" type="button">-</a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <a class="btn btn-default addTrafficLight">Add Traffic Light</a>
                                        <a class="btn btn-default deleteGroup">Delete Group</a>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <a class="btn btn-default addGroup">Add Group</a>
                                    <button type="submit" class="btn btn-default">Submit</button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="/resources/js/bootstrap.min.js"></script>
        <script src="/resources/js/event.js"></script>
    </body>
</html>

