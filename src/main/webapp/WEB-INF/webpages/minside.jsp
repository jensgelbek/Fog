<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" %>


<h1>
    Mine Ordrer
</h1>
<div class="container">

    <link rel="stylesheet" href="<c:url value="/static/style.css"/>"/>
    <br>
    <br>
    <div class="row">
        <div class="col-md-5">

        </div>
        <div class="col-md-2" align="center">
            Carport
        </div>
        <div class="col-md-2" align="center">
            Skur
        </div>
        <div class="col-md-2">

        </div>
    </div>
    <div class="row">
        <div class="col-md-1">
            Ordre nummer
        </div>
        <div class="col-md-1">
            Tilbuds dato
        </div>
        <div class="col-md-1">
            Ordre dato
        </div>
        <div class="col-md-1">
            Levering
        </div>
        <div class="col-md-1">
            Navn
        </div>
        <div class="col-md-1">
            Bredde mm
        </div>
        <div class="col-md-1">
            Længde mm
        </div>
        <div class="col-md-1">
            Bredde mm
        </div>
        <div class="col-md-1">
            Længde mm
        </div>
        <div class="col-md-1">
            Pris
        </div>
        <div class="col-md-1">
            Status
        </div>
    </div>
    <hr>
    <c:forEach items="${requestScope.Orders}" var="ordreLinje" varStatus="loop">
        <div class="row">
            <div class="col-md-1">
                    ${ordreLinje.orderID}
            </div>
            <div class="col-md-1">
                    ${ordreLinje.tilbudsdato}
            </div>
            <div class="col-md-1">
                    ${ordreLinje.ordredato}
            </div>
            <div class="col-md-1">
                    ${ordreLinje.leveringsDato}
            </div>
            <div class="col-md-1">
                    ${requestScope.navne[loop.index]}
            </div>
            <div class="col-md-1">
                    ${requestScope.carportbredder[loop.index]}
            </div>
            <div class="col-md-1">
                    ${requestScope.carportlaengder[loop.index]}

            </div>
            <div class="col-md-1">
                    ${requestScope.skurbredder[loop.index]}
            </div>
            <div class="col-md-1">
                    ${requestScope.skurlaengder[loop.index]}
            </div>
            <div class="col-md-1">
                    ${ordreLinje.price}
            </div>
            <div class="col-md-1">
                    ${ordreLinje.status}
            </div>
            <div class="col-md-1">
                <form method="post">
                    <input type="hidden" name="vis" value="${ordreLinje.orderID}">
                    <button type="submit" class="btn btn-primary">Vis</button>
                </form>
            </div>

        </div>
    </c:forEach>

    <hr>
</div>

