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
                        <select class="form-control" name="width" id="bredde">
                            <option value="">Vælg bredde</option>
                            <c:forEach items="${requestScope.carportMeasure}" var="bred">
                                <option value="${bred}" <c:if test="${bred==carport.width}">selected</c:if>>
                                        ${bred}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Dropdown width -->
                    <div class="form-group ">
                        <label for="længde">Carport længde</label>
                        <select class="form-control" name="length" id="længde">
                            <option value="">Vælg længde</option>
                            <c:forEach items="${requestScope.carportMeasure.subList(0, carportMeasure.size()-1)}"
                                       var="len">
                                <option value="${len}" <c:if test="${len==carport.length}">selected</c:if>>
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
                    <hr>
                    <h6> Du har valgt følgende carport mål:<br></h6>
                    --<br>
                    <div class="row">
                        <div class="col-md-3">
                            Bredde:
                        </div>
                        <div class="col-md-9">
                            ${sessionScope.carport.width}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            Længde:
                        </div>
                        <div class="col-md-9">
                            ${sessionScope.carport.length}

                        </div>
                    </div>

                    <hr>
                    <div class="row">
                        <h6> Tegning af din carport:</h6>
                    </div>
                    <div class="row">
                        <p>Bredde & længde udgør 'Stern' (for/bag og siderne) her indkeret med <b><i>Rødt</i></b> <br>
                            Tværgående 'Spær' med <b><i>Lilla</i></b>. <br>
                        2 x bærende 'Rem' i siderne på langs med <b><i>sort</i></b>  <br>
                            Stolper er firkanter placeret langs remmene <b><i>sort</i></b> <br>
                            Den <b><i>blå</i></b> linje til venste er bredden indenfor stolperne <br></p>
                    </div>

                        <div class="row">
                        <p><b><u>Du skal bruge følgende materialer:</u></b></p>
                        ${requestScope.sternWidthCalc.toString()} <br>
                        ${requestScope.sternLengthCalc} <br>
                            ${requestScope.spaerCalc.toString()}
                        ${carport.drawing}
                    </div>


                    <br>
                    <hr>
                    <br>
                    <button type="" class="btn btn-primary">Send bestilling</button>

                </form>
                <br>
                <p>* Hvis du f.eks. har valgt en carport med målene 240x360 cm kan redskabsrummet maksimalt måle 210x330 cm.</p>

            </div>
        </div>
    </div>
</div>