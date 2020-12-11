<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8"%>
<div class="row">
    <h1>Carport</h1>

</div>
<div class="row">
    <h3>Stykliste: <br/>
        Husk at kontrollere materialelisten før du går igang</h3>
</div>
<div class="row">
    <hr>
    <br>
</div>
<div class="row">
    <div class="col-md-2">
        Beskrivelse
    </div>
    <div class="col-md-2">
        Længde
    </div>
    <div class="col-md-2">
        Antal
    </div>
    <div class="col-md-2">
        Enhed
    </div>
    <div class="col-md-2">
        Beskrivelse
    </div>
    <div class="col-md-2">
        Ændre/Slette
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-2">
        ${requestScope.sternWidthCalc.toString()}
    </div>
    <div class="col-md-2">
        N/A
    </div>
    <div class="col-md-2">
        ${requestScope.sternWidthCalc}
    </div>
    <div class="col-md-2">
        Stk
    </div>
    <div class="col-md-2">
        ${requestScope.sternWidthCalc.description}
    </div>
    <div class="col-md-2">
        N/A
    </div>
</div>
<hr>
</div>
<form method="post" >
    <div class="text-right">
        <input type="hidden" name="afslut" >
        <button type="submit" class="btn btn-primary" >Afslut</button
    </div>
</form>


