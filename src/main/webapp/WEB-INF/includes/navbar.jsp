    <%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="navbar navbar-expand-md navbar-dark static-top navbar-custom ">

    <a class="logo" href="http://localhost:8080/webapptemplate_war_exploded/frontpage">
        <div class="logo">
            <img src="<c:url value="/images/fog2.png"/>" class="img-fluid">
        </div>

    </a>

    <a class="navbar-home" href="frontpage"> Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
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
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            <form class="px-3 py-3" method="post" action="<c:url value="/bestilling"/>">
            </form>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse3">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                </li>

            </ul>

            <c:if test="${sessionScope.employer==\"yes\"}">
                <a class="navbar-ordrer" href="ordrer"> Ordrer</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    <form class="px-3 py-3" method="post" action="<c:url value="/ordrer"/>">
                    </form>
                </button>
                <a class="navbar-ordrer" href="opretSeller"> Opret kollega</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    <form class="px-3 py-3" method="post" action="<c:url value="/opretSeller"/>">
                    </form>
                </button>
            </c:if>
            <a class="navbar-kontakt" href="kontakt"> Kontakt</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
                <form class="px-3 py-3" method="post" action="<c:url value="/kontakt"/>">
                </form>
            </button>

            <div class="collapse navbar-collapse" id="navbarCollapse2">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">

                    </li>

                </ul>

                <a class="navbar-user" href="minSide"> ${sessionScope.username}</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    <form class="px-3 py-3" method="post" action="<c:url value="/minSide"/>">
                    </form>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse4">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">


                        </li>

                    </ul>

                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                        <form class="px-3 py-3" method="get" action="<c:url value="/oprettelse"/>">

                        </form>

                    </button>

                </div>


                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="<c:url value="/oprettelse"/>">Opret Bruger</a>
                    </li>
                </ul>
                <c:if test="${sessionScope.username != null && sessionScope.username !=\"\"}">

                    <form method="post" action="<c:url value="/frontpage"/>">
                        <input type="hidden" name="Log ud">
                        <button type="submit" name="Log ud" value="Log ud" class="btn btn-primary">Log ud</button>

                    </form>

                </c:if>


            </div>
            <c:if test="${sessionScope.username == null || sessionScope.username ==\"\"}">
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle btn btn-primary" href="" id="dropdown01" data-toggle="dropdown"
                   aria-haspopup="true"
                   aria-expanded="false">Log Ind</a>


                <div class="dropdown-menu dropdown-menu-right dropdown-menu-larger">

                    <form class="px-3 py-3" method="post" action="<c:url value="/oprettelse"/>">
                        <div class="form-group">
                            <label for="exampleDropdownFormEmail1">Email</label>
                            <input type="text" class="form-control" id="exampleDropdownFormEmail1"
                                   name="Loginemail"

                            >
                        </div>

                        <div class="form-group">
                            <label for="exampleDropdownFormPassword1">Kodeord</label>
                            <input type="password" class="form-control" id="exampleDropdownFormPassword1"
                                   name="Loginpassword">

                        </div>
                        <button type="submit" class="btn btn-primary">Log in</button>

                        </c:if>
                    </form>
                </div>
            </div>

        </div>
    </div>
    </div>
    </div>
</nav>
