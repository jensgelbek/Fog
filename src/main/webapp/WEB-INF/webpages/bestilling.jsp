<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<div class="container">

    <div class="row" style="margin-top: 5em">

        <div class ="col-md-3">
            <h6><a href="http://localhost:8080/webapptemplate/bestilling"><b style="color: darkblue">CARPORT MED FLADT TAG</b></a></h6>
            <hr>
            <h6><a href="http://localhost:8080/webapptemplate/bestillingRejsning"><b style="color: darkblue">CARPORT MED REJSNING</b></a></h6>
            <hr>

        </div>

        <div class="col-md-9">

            <div>
                <h2>QUICK-BYG TILBUD - CARPORT MED FLADT TAG</h2>
            </div>

            <div>
                <p>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram, der tilpasses dine specifikke ønsker.</p>
            </div>

            <div>
            <p>Tilbud og skitsetegning fremsendes med post hurtigst muligt.<br>Ved bestilling medfølger standardbyggevejledning.</p>
            </div>

            <p><b>Udfyld nedenstående omhyggeligt og klik på "Bestil tilbud"</b><br>Felter markeret * SKAL udfyldes!</p>

            <p>Ønsket carport mål:</p>


            <div class="container">

                <form method="post">

                    <input type="hidden" name="target" value="bestilling">

                    <!-- Dropdown length -->
                    <div class="form-group ">
                        <label for="bredde">Carport bredde</label>
                        <select class="form-control" name="bredde" id="bredde">
                            <option value="">Vælg bredde</option>
                            <%!
                                private class embed {
                                }
                            %><c:forEach items="${requestScope.carportMeasure}" var="bred">
                                <option value="${bred}">
                                        ${bred}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Dropdown width -->
                    <div class="form-group ">
                        <label for="længde">Carport længde</label>
                        <select class="form-control" name="laengde" id="længde">
                            <option value="">Vælg længde</option>
                            <c:forEach  items="${requestScope.carportMeasure.subList(0, carportMeasure.size()-1)}" var="len">
                                <option value="${len}">
                                        ${len}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Dropdown roof -->
                    <div class="form-group ">
                        <label for="tag">Tag</label>
                        <select class="form-control" name="tag" id="tag">
                            <c:forEach  items="${requestScope.tag}" var="tag">
                                <option value="${tag}">
                                        ${tag}
                                </option>
                            </c:forEach>
                        </select>
                    </div>


                    <br>

                    <P><b>Redskabsrum:</b><br>NB! Der skal beregnes 15 cm tagudhæng på hver side af redskabsrummet*</P>

                    <!-- Dropdown Shed-width -->
                    <div class="form-group ">
                        <label for="shedW">Redskabsskur bredde</label>
                        <select class="form-control" name="number" id="shedW">
                            <option value="shed">Ønsker ikke redskabsskur</option>
                            <c:forEach  items="${requestScope.shed.subList(2, shed.size())}" var="shedW">
                                <option value="${shedW}">
                                        ${shedW} cm
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Dropdown Shed-width -->
                    <div class="form-group ">
                        <label for="shedL">Redskabsskur længde</label>
                        <select class="form-control" name="number" id="shedL">
                            <option value="shed">Ønsker ikke redskabsskur</option>
                            <c:forEach  items="${requestScope.shed.subList(0, shed.size()-1)}" var="shedL">
                                <option value="${shedL}">
                                        ${shedL} cm
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Vis tegning</button>
                    <br>
                    <hr>
                    <br>
                    <h6> Du har valgt følgende carport mål:<br></h6>
                    --<br>
                    <div class="row">
                        <div class="col-md-3">
                        Bredde:<br>
                            Længde:<br>
                            Tag: <br>
                            <br>
                            Redskabsskur bredde: <br>
                            Redskabsskur længde:

                        </div>
                        <div class="col-md-9">
                            ${bredde}<br>
                                ${langde}<br>
                                ${tag2}<br>
                                <br>
                            N/A<br>
                            N/A
                        </div>
                    </div>
                    <br>
                    <hr>
                    <br>
                    <div class="row">
                        <h6> Tegning af din carport:<br></h6>
                        ${svg}
                    </div>

                    <br>
                    <hr>
                    <br>
                    <button type="" class="btn btn-primary">Send bestilling</button>

                </form>
                <br>
                <p>* Hvis du f.eks. har valgt en carport med målene 240x360 cm kan redskabsrummet maksimalt måle 210x330 cm.</p>



                    <div class="form-group">
                        <label for="navn">Navn</label>
                        <input type="text" class="form-control" id="navn"
                               aria-describedby="kundeNavn" name="navn">
                    </div>

                    <div class="form-group">
                        <label for="adresse">Adresse</label>
                        <input type="text" class="form-control" id="adresse"
                               aria-describedby="kundeAdresse" name="adresse">
                    </div>

                    <div class="form-group">
                        <label for="postBy">Postnummer & by</label>
                        <input type="text" class="form-control" id="postBy"
                               aria-describedby="kundePostBy" name="postBy">
                    </div>

                    <div class="form-group">
                        <label for="tlfNummer">Telefon</label>
                        <input type="text" class="form-control" id="tlfNummer"
                               aria-describedby="kundeTlfNummer" name="tlfNummer">
                    </div>

                    <div class="form-group">
                        <label for="email">Email adresse</label>
                        <input type="text" class="form-control" id="email"
                               aria-describedby="kundeEmail" name="email">
                    </div>

                    <div class="form-group">
                        <label for="bemærkninger">Evt. bemærkninger</label>
                        <input type="text" class="form-control" id="bemærkninger"
                               aria-describedby="kundeBemærkninger" name="bemærkninger">
                    </div>

            </div>
        </div>
    </div>
</div>