<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Emergency</title>
</head>
<body>
<h4> Carrefour : INRIA</h4>
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
</body>
</html>
