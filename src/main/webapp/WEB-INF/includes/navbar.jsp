<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    /* Modify the background color */

    .navbar-oprettelse {
        overflow: hidden;
        float: right;


    }
    .navbar-custom {
        background-color: #003D76;
    }
    .navbar-brand {
        border-color: #ae142a;
        text-transform: uppercase;
        font-family: Montserrat, "Helvetica Neue", Helvetica, Arial, sans-serif;
        font-weight: 700;
        color: #fff;
        background-color: #003D76;
    }
    .navbar-home {

        color: #ffffff;
        background-color: #003D76;
        margin: 5px;

    }
    .navbar-ordrer {
        color: #FFFFFF;
        margin: 5px;

    }
    .logo {
        width: 80px;
        height: 80px;
        border-radius: 0%;
        overflow: auto;
        margin-top: 0px;



    }
    .navbar-kontakt {
        color: #ffffff;
        margin: 5px;

    }
    .navbar-carporte {
        color: #FFFFFF;
        margin: 5px;

    }
    .nav-item {
        margin: 5px;
    }




    /* Modify brand and text color */


</style>

<nav class="navbar navbar-expand-md navbar-dark static-top navbar-custom ">

   <a class="logo" href="http://localhost:8080/webapptemplate_war_exploded/frontpage">
       <div class="logo">
            <img src="<c:url value="/images/fog2.png"/>" class="img-fluid">
       </div>

   </a>

    <a class="navbar-home" href="frontpage"> Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        <form class="px-3 py-3" method="get" action="<c:url value="/frontpage"/>">

        </form>

    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">

            </li>

        </ul>
        <a class="navbar-carporte" href="bestilling">Carporte</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            <form class="px-3 py-3" method="post" action="<c:url value="/bestilling"/>">
            </form>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse3">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                </li>

            </ul>


            <a class="navbar-ordrer" href="ordrer"> Ordrer</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
                <form class="px-3 py-3" method="post" action="<c:url value="/ordrer"/>">
                </form>
            </button>
            <a class="navbar-ordrer" href="minSide"> Min side</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
                <form class="px-3 py-3" method="post" action="<c:url value="/minSide"/>">
                </form>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse4">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">


                    </li>

                </ul>
                <a class="navbar-kontakt" href="kontakt"> Kontakt</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    <form class="px-3 py-3" method="post" action="<c:url value="/kontakt"/>">
                    </form>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse2">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">

                        </li>

                    </ul>

                    <a class="navbar-oprettelse" href="oprettelse"> Opret Bruger</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                        <form class="px-3 py-3" method="get" action="<c:url value="/oprettelse"/>">

                        </form>

                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse5">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">

                            </li>

                        </ul>

    </div>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle btn btn-secondary" href="#" id="dropdown01" data-toggle="dropdown"
                   aria-haspopup="true"
                   aria-expanded="false">Log Ind</a>
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-larger">

                    <form class="px-3 py-3" method="post" action="<c:url value="/oprettelse"/>">
                        <div class="form-group">
                            <label for="exampleDropdownFormEmail1">Email</label>
                            <input type="email" class="form-control" id="exampleDropdownFormEmail1"
                                   name="Loginemail"
                            >
                        </div>
                        <div class="form-group">
                            <label for="exampleDropdownFormPassword1">Kodeord</label>
                            <input type="password" class="form-control" id="exampleDropdownFormPassword1"
                                   name="Loginpassword">

                        </div>
                        <button type="submit" class="btn btn-primary">Log in</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
    </div>
</nav>
