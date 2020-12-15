<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" %>
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
                   aria-describedby="kundeNavn" name="name">
        </div>

        <div class="form-group">
            <label for="address">Adresse:</label>
            <input type="text" class="form-control" id="address"
                   aria-describedby="kundeAdresse" name="address">
        </div>

        <div class="form-group">
            <label for="postBy">Postnummer & By:</label>
            <input type="text" class="form-control" id="postBy"
                   aria-describedby="kundePostBy" name="postBy">
        </div>

        <div class="form-group">
            <label for="tlfNummer">Telefon:</label>
            <input type="number" class="form-control" id="tlfNummer"
                   aria-describedby="kundeTlfNummer" name="tlfNummer">
        </div>
        <div class="form-group">
            <label for="email">Email-adresse:</label>
            <input type="email" class="form-control" id="email"
                   aria-describedby="kundeEmail" name="email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password"
                   aria-describedby="kundepassword" name="password">
        </div>


        <div class="col-md-1">
            <form method="post">
                <input type="hidden" name="Opret" value="opret">
                <button type="submit" class="btn btn-primary">Opret</button>
            </form>
        </div>


    </div>
</form>
