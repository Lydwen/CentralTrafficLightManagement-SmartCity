<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Traffic Light : Add scenario</title>
</head>
<body>
<h4> Carrefour : INRIA</h4>
<form action="addscenario" method="POST">
    <label>Temps transition rouge</label> <input type="text" name="transition_time">
    <hr>
    <div>
        <p>Group :</p>
        <p> Temps vert <input type="text" name="green_timeA"></p>
        <div>
            <p>
                <label>Traffic Light</label>
                <input type="text" name="traffic_light1">
                <button type="button">-</button>
            </p>
            <p>
                <label>Traffic Light</label>
                <input type="text" name="traffic_light2">
                <button type="button">-</button>
            </p>
            <button type="button">+</button>
        </div>
        <hr>
    </div>
    <div>
        <p>Group :</p>
        <p> Temps vert <input type="text" name="green_timeB"></p>
        <div>
            <p>
                <label>Traffic Light</label>
                <input type="text" name="traffic_light3">
                <button type="button">-</button>
            </p>
            <p>
                <label>Traffic Light</label>
                <input type="text" name="traffic_light4">
                <button type="button">-</button>
            </p>
            <button type="button">+</button>
        </div>
        <hr>
    </div>
    <button type="button">addGroup</button>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>