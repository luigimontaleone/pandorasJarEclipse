<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Games</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/searchGameScript.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/searchGameStyle.css">
    <link rel="stylesheet" href="css/filterStyle.css">
</head>

<body style="background-color: #284c67;">
    <jsp:include page="header.jsp"></jsp:include>
    <div id="mainDiv">
        <c:if test="${fn:length(games) == 0}">
            <h1 class="text-left d-block" id="h1GiochiRicercati">La ricerca non ha prodotto risultati</h1>
        </c:if>
        <c:if test="${fn:length(games) > 0}">
            <h1 class="text-left d-block" id="h1GiochiRicercati">Ecco i giochi che hai ricercato:</h1>
            <div class="filter border rounded" id="filter">
                <form>
                    <select id="categoria" name="categoria">
                        <option value="" selected="selected">Categoria</option>
                        <option value="shooter">Shooter</option>
                        <option value="arcade">Arcade</option>
                        <option value="avventura">Avventura</option>
                        <option value="azione">Azione</option>
                    </select>
                    <select id="prezzo" name="prezzo">
                        <option value="" selected="selected">Prezzo</option>
                        <option value="0">0€-9€</option>
                        <option value="10">10€-19€</option>
                        <option value="20">20€-29€</option>
                        <option value="30">30€-39€</option>
                        <option value="40">40€-49€</option>
                        <option value="50">50€ ></option>
                    </select>
                    <select id="valutazione" name="valutazione">
                        <option value="" selected="selected">Valutazione media</option>
                        <option value="1">1⭐</option>
                        <option value="2">2⭐</option>
                        <option value="3">3⭐</option>
                        <option value="4">4⭐</option>
                        <option value="5">5⭐</option>
                    </select>
                </form>
            </div>
            <c:set var="index" scope="request" value="${0}"></c:set>
            <c:forEach items="${games}" var="game">
                <c:if test="${index % 4 == 0}">
                    </div>
                    <div class="row row-games">
                </c:if>
                    <div class="col-xl-3" style="height: 100%;"><a href="/GameDataSheet?gameId=${game.id}"><img style="width: 100%;height: 100%;" src="${game.frontImage}"></a></div>
                <c:set var="index" scope="request" value="${index + 1}"></c:set>
            </c:forEach>
            </div>
        </c:if>
    </div>
    <jsp:include page="footer.html"></jsp:include>
</body>

</html>