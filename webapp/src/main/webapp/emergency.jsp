<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Emergency</title>
</head>
<body>
<h4> Carrefour : INRIA</h4>
<form action="./emergency" method="POST">
    <input type="text" name="crossroad" id="crossroad"/>
    <label for="crossroad">Crossroad</label>

    <input type="text" name="traffic_light" id="traffic_light"/>
    <label for="traffic_light">Traffic light</label>

    <input type="submit" value="TURN IT ON!"/>
</form>
</body>
</html>
