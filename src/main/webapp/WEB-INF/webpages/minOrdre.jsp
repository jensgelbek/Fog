<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Ordreside</title>
</head>
<body>


<h1>Nummer ${requestScope.order.orderID} </h1>
<c:if test="${requestScope.order.status==\"tilbud\"}"><b>Status:</b> Tilbud modtaget<br></c:if>
<c:if test="${requestScope.order.status==\"kontakt\"}"><b>Status:</b> Venter på at blive kontakter af sælger<br></c:if>
<c:if test="${requestScope.order.status==\"ordre\"}"><b>Status:</b> Carport bestilt og afventer levering<br></c:if>
<c:if test="${requestScope.order.status==\"afsluttet\"}"><b>Status:</b> Ordren er afsluttet<br></c:if>
<c:if test="${requestScope.order.status==\"afslået\"}"><b>Status:</b> Tilbud afslået<br></c:if>


<c:if test="${requestScope.order.tilbudsdato!=null}">
    <b>Tilbuds dato</b> ${requestScope.order.tilbudsdato} <br>
</c:if>
<c:if test="${requestScope.order.ordredato!=null}">
    <b> Ordre dato</b> ${requestScope.order.ordredato}<br>
</c:if>
<c:if test="${requestScope.order.leveringsDato!=null}">
    <b>Leveres</b>${requestScope.order.leveringsDato}<br>
</c:if>
<b>Carport på</b> ${requestScope.carport.width/1000}*${requestScope.carport.lenght/1000} m
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
<b>Pris: </b>${requestScope.order.price}
<br>


<b> Tegning af din carport:<br></b>
<p>Bredde & længde udgør 'Stern' (for/bag og siderne), her indikeret med <b><i>Rødt</i></b> <br>
    Tværgående 'Spær' med <b><i>Lilla</i></b>. <br>
    2 x bærende 'Rem' i siderne på langs med <b><i>Sort</i></b> <br>
    Stolper er firkanter placeret langs remmene <b><i>Sort</i></b> <br>
    Linje til venste er bredden indenfor stolperne, tegnet med<b><i> Blå</i></b><br>
    Skuret er indikeret med <b><i>Grøn</i></b>
</p>
${svg}
<br>

<hr>

<c:if test="${requestScope.order.status==\"afsluttet\"||requestScope.order.status==\"ordre\"}">
   <h1>Stykliste</h1>
   <b>Træ og træplader</b>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Beskrivelse</th>
            <th scope="col">længde</th>
            <th scope="col">Antal</th>
            <th scope="col">Beskrivelse</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.stykliste.volumenListe}" var="styklisteLinje">
            <tr>
                <td>${styklisteLinje.materiale.details}</td>
                <td>${styklisteLinje.materiale.length}</td>
                <td>${styklisteLinje.quantity}</td>
                <td>${styklisteLinje.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <br>
        <b>Beslag og skruer</b>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">Beskrivelse</th>
                <th scope="col">Antal</th>
                <th scope="col">Enhed</th>
                <th scope="col">Beskrivelse</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.stykliste.unitListe}" var="styklisteLinje">
                <tr>
                    <td>${styklisteLinje.materiale.details}</td>
                    <td>${styklisteLinje.quantity}</td>
                    <td>${styklisteLinje.materiale.unitType}</td>
                    <td>${styklisteLinje.description}</td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</c:if>

<c:if test="${requestScope.order.status==\"tilbud\"||requestScope.order.status==\"kontakt\"}">
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
    <h1>Her skal vises stykliste...</h1>
</c:if>

</body>
</html>
