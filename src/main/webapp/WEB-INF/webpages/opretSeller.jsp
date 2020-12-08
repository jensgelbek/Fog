<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8"%>
<p1>
    Her kan du oprette en bruger. Du skal have oprettet dig som bruger, før du kan bestille
    en carport.
    <br>
    Indtast dine oplysninger:

</p1>
<br>
<br>
<form method="post">
    <input type="hidden" name="oprettelse" value="oprettelse">
    <div class="container">
        <div class="form-group">

            <label for="name">Fulde navn:</label>
            <input type="text" class="form-control" id="name"
                   aria-describedby="sellerNavn" name="name">
        </div>


        <div class="form-group">
            <label for="username">Bruger navn</label>
            <input type="userName" class="form-control" id="userName"
                   aria-describedby="userName" name="userName">
        </div>

        <div class="col-md-1">
            <form method="post" >
                <input type="hidden" name="Opret" value="opret">
                <button type="submit" class="btn btn-primary" >Opret</button>
            </form>
        </div>


    </div>
</form>
