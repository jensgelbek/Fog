<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .header-text{
        margin-left: 10px;

    }

</style>
<html>
<head>
    <title>Ordreside</title>
</head>
<body>


    <h1>Ordre nummer  ${requestScope.order.orderID} </h1>
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
    ${requestScope.carport.tag}
    <br>
        <b> Tegning af din carport:<br></b>
        <p>Bredde & længde udgør 'Stern' (for/bag og siderne) her indkeret med <b><i>Rødt</i></b> <br>
            Tværgående 'Spær' med <b><i>Lilla</i></b>. <br>
            2 x bærende 'Rem' i siderne på langs med <b><i>sort</i></b>  <br>
            Stolper er firkanter placeret langs remmene <b><i>sort</i></b> <br>
            Den <b><i>blå</i></b> linje til venste er bredden indenfor stolperne </p>
        ${svg}

</body>
</html>
