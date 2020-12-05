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


</body>
</html>
