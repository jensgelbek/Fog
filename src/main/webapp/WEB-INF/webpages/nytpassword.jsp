<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 14-12-2020
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>vælg nyt password</title>
    <h1>
            Skift venligst dit password:
        <br>
        <br>
        <br>
    </h1>
</head>
<body>
<form method="post">
<div class="form-group">
    <label for="gammeltpassword">Indtast nuværende password:</label>
    <input type="password" class="form-control" id="gammeltpassword"
           aria-describedby="gammeltpassword" name="gammeltpassword">

    <label for="nytpassword">Indtast dit nye password:</label>
    <input type="password" class="form-control" id="nytpassword"
           aria-describedby="nytpassword" name="nytpassword" required="required">


        <input type="hidden" name="OK" value="">
        <button type="submit" class="btn btn-primary">OK</button>


</div>
</form>
</body>
</html>
