<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Traffic Light Emergency</title>
    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <h1 class="page-header">Traffic Light Emergency</h1>
    <c:if test="${success}">
    <div class="col-sm-offset-4 col-sm-4 text-success">
        Emergency declared successfully!
    </div>
    </c:if>
    <c:if test="${not empty error}">
    <div class="col-sm-offset-4 col-sm-4 text-success">
        Emergency declaration failed!<br/>
        ${error}
    </div>
    </c:if>
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <form action="emergency" method="POST">
                <div class="form-group">
                    <input type="text" name="crossroad" id="crossroad"
                           class="form-control"/>
                    <label for="crossroad">Crossroad</label>
                </div>
                <div class="form-group">
                    <label for="traffic_light">Traffic light</label>
                    <input type="text" name="traffic_light" id="traffic_light"
                           class="form-control"/>
                </div>
                <input type="submit" value="TURN IT ON!"
                       class="btn btn-default"/>
            </form>
        </div>
    </div>
</body>
</html>
