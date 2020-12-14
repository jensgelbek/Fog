<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .form-group {
        margin-left: 15px;
        margin-right: 40%;
    }

    h1 {
        font-size: x-large;
        margin-left: 15px;
    }

    p1 {
        font-style: italic;
        margin-left: 15px;
    }
</style>
<html>
<head>
    <title>Title</title>
    <h1> Johannes Fog Brugeroprettelse
        <br>
        <br>
        <br>

    </h1>
</head>
<body>
<p1>
    Indtast dine oplysninger:

</p1>
<br>
<br>

<form>
    <form method="post">
        <div class="form-group">

            <label for="navn">Fulde navn:</label>
            <input type="text" class="form-control" id="navn"
                   aria-describedby="kundeNavn" name="navn">
        </div>

        <div class="form-group">
            <label for="adresse">Adresse:</label>
            <input type="text" class="form-control" id="adresse"
                   aria-describedby="kundeAdresse" name="adresse">
        </div>

        <div class="form-group">
            <label for="postBy">Postnummer & By:</label>
            <input type="text" class="form-control" id="postBy"
                   aria-describedby="kundePostBy" name="postBy">
        </div>

        <div class="form-group">
            <label for="tlfNummer">Telefon:</label>
            <input type="text" class="form-control" id="tlfNummer"
                   aria-describedby="kundeTlfNummer" name="tlfNummer">
        </div>

        <div class="form-group">
            <label for="email">Email-adresse:</label>
            <input type="text" class="form-control" id="email"
                   aria-describedby="kundeEmail" name="email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="text" class="form-control" id="password"
                   aria-describedby="kundepassword" name="password">
        </div>
        <div class="form-group">
            <label for="gentagpassword">Gentag Password:</label>
            <input type="text" class="form-control" id="gentagpassword"
                   aria-describedby="kundegentagpassword" name="gentagpassword">
        </div>

        <div class="col-md-1">

            <input type="hidden" name="Opret" value="opret">
            <button type="submit" class="btn btn-primary">Opret</button>
        </div>
    </form>
    </div>


</form>
</body>
</html>
