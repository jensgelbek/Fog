<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Rediger materialer</title>
</head>
<body>

<div class="container">
    <link rel="stylesheet" href="<c:url value="/static/style.css"/>"/>
    <div class="row">
        <div class="col-md-2">
            Navn
        </div>
        <div class="col-md-3">
            Beskrivelse
        </div>
        <div class="col-md-1">
            pris
        </div>
        <div class="col-md-1">
           ny pris
        </div>
    </div>
    <c:forEach items="${requestScope.materialer}" var="materiale" varStatus="loop">
    <form method="post">
        <input type="hidden" name="rediger" value="rediger">
        <div class="row">
        <div class="col-md-2">
                ${materiale.name}
        </div>
        <div class="col-md-3">
                ${materiale.details}
        </div>
        <div class="col-md-1">
                ${materiale.unitPrice}
        </div>
        <div class="col-md-1">
                   <input type="text" class="form-control" id="pris"
                   aria-describedby="pris" name="pris">
        </div>
        <div class="col-md-1">
            <form method="post">
                <input type="hidden" name="navn" value="${materiale.name}">
                <button type="submit" class="btn btn-primary">Opdater</button>
            </form>
        </div>
    </div>
    </form>
    </c:forEach>
    </div>
</body>
</html>
