<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .header-text {
        margin-left: 10px;

    }

</style>
<html>
<head>
    <title>Ordreside</title>
</head>
<body>


<h1>Ordrenummer ${requestScope.order.orderID} </h1>
<b>Kunde ${requestScope.customer.name}</b><br>
<c:if test="${requestScope.order.status==\"tilbud\"}"><b>Status:</b> tilbud modtaget<br></c:if>
<c:if test="${requestScope.order.status==\"kontakt\"}"><b>Status:</b> venter på at blive kontakter af sælger<br></c:if>
<c:if test="${requestScope.order.status==\"ordre\"}"><b>Status:</b> carport bestilt og afventer levering<br></c:if>
<c:if test="${requestScope.order.status==\"afsluttet\"}"><b>Status:</b> ordren er afsluttet<br></c:if>
<c:if test="${requestScope.order.status==\"afslået\"}"><b>Status:</b> tilbud afslået<br></c:if>


<c:if test="${requestScope.order.tilbudsdato!=null}">
    <b>Tilbuds dato</b> ${requestScope.order.tilbudsdato} <br>
</c:if>
<c:if test="${requestScope.order.ordredato!=null}">
    <b> Ordre dato</b> ${requestScope.order.ordredato}<br>
</c:if>
<c:if test="${requestScope.order.leveringsDato!=null}">
    <b>Leveres</b>${requestScope.order.leveringsDato}<br>
</c:if>

<b>carport på</b> ${requestScope.carport.width/1000}*${requestScope.carport.lenght/1000} m
<c:if test="${requestScope.carport.shedWidth!=0}">
    med et skur på ${requestScope.carport.shedWidth/1000}*${requestScope.carport.shedLength/1000}m,
</c:if>
<c:if test="${requestScope.carport.rejsning==true}">
    med et fladt tag med
</c:if>
<c:if test="${requestScope.carport.rejsning==false}">
    med tag med rejsning med
</c:if>
${requestScope.carport.tag}<br>

<br>
<hr>
<form method="post">
<div class="row">
    <div class="col-md-1">
        carport
    </div>
    <div class="col-md-2">
        carport
    </div>
    <div class="col-md-1">
        carport
    </div>
    <div class="col-md-2">
        carport
    </div>
    <div class="col-md-1">
        skur
    </div>
    <div class="col-md-2">
        skur
    </div>
    <div class="col-md-1">
        skur
    </div>
    <div class="col-md-2">
        skur
    </div>
</div>

<div class="row">
    <div class="col-md-1">
        længde
    </div>
    <div class="col-md-2">
        ny længde
    </div>
    <div class="col-md-1">
        bredde
    </div>
    <div class="col-md-2">
        ny bredde
    </div>
    <div class="col-md-1">
        længde
    </div>
    <div class="col-md-2">
        ny længde
    </div>
    <div class="col-md-1">
        bredde
    </div>
    <div class="col-md-2">
        ny bredde
    </div>
</div>
<div class="row">
    <div class="col-md-1">
        ${requestScope.carport.lenght}
    </div>
    <div class="col-md-2">
        <div class="form-group ">
            <select class="form-control" name="length" id="længde">
                <option value=""></option>
                <c:forEach items="${requestScope.carportMeasure}" var="len">
                    <option value="${len}" <c:if test="${len==requestScope.carport.lenght}">selected</c:if>>
                            ${len}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-md-1">
        ${requestScope.carport.width}
    </div>
    <div class="col-md-2">
        <div class="form-group ">
            <select class="form-control" name="width" id="bredde">
                <option value=""></option>
                <c:forEach items="${requestScope.carportMeasure}" var="len">
                    <option value="${len}" <c:if test="${len==requestScope.carport.width}">selected</c:if>>
                    ${len}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-md-1">
        ${requestScope.carport.shedLength}
    </div>
    <div class="col-md-2">
        <div class="form-group ">
            <select class="form-control" name="shedlength" id="skurlængde">
                <option value=""></option>
                <c:forEach items="${requestScope.shedL}" var="len">
                    <option value="${len}" <c:if test="${len==requestScope.carport.shedLength}">selected</c:if>>
                            ${len}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-md-1">
        ${requestScope.carport.shedWidth}
    </div>
    <div class="col-md-2">
        <div class="form-group ">
            <select class="form-control" name="shedwidth" id="skurbredde">
                <option value=""></option>
                <c:forEach items="${requestScope.shedW}" var="len">
                    <option value="${len}" <c:if test="${len==requestScope.carport.shedWidth}">selected</c:if>>
                            ${len}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-md-1">
        <form method="post">
            <input type="hidden" name="edit" value=${requestScope.order.orderID}>
            <button type="submit" class="btn btn-primary">Gem</button>
        </form>
    </div>
    <div class="col-md-3">
        Gemmer nye mål og beregner ny stykliste
    </div>
</div>
</form>
<br>
<br>

<c:if test="${requestScope.order.status!=\"afsluttet\"}">
    <c:if test="${requestScope.order.status!=\"afslået\"}">
        <c:if test="${requestScope.order.status!=\"ordre\"}">
            <div class="row">

                <div class="col-md-1">
                    <form method="post">
                        <input type="hidden" name="bestil" value=${requestScope.order.orderID}>
                        <button type="submit" class="btn btn-primary">Bestil</button>
                    </form>
                </div>
                <div class="col-md-1">
                    <form method="post">
                        <input type="hidden" name="afslaa" value=${requestScope.order.orderID}>
                        <button type="submit" class="btn btn-primary">Afslå</button>
                    </form>
                </div>
                <div class="col-md-2">
                    <form method="post">
                        <input type="hidden" name="kontakt" value=${requestScope.order.orderID}>
                        <button type="submit" class="btn btn-primary">Kontakt mig</button>
                    </form>

                </div>

            </div>

        </c:if>
    </c:if>
</c:if>
<c:if test="${requestScope.order.status==\"ordre\"}">
    <h1>Her skla vises stykliste...</h1>
</c:if>

</body>
</html>
