<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Invite Friends</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
        <script src="scripts/inviteFriendScript.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
        <link rel="stylesheet" href="css/inviteFriendStyle.css">
    </head>
    <body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="row" id="firstRow">
        <div class="col-3" id="divProfileMenu">
            <jsp:include page="profileMenu.jsp"></jsp:include>
        </div>
        <div class="col" >
            <div class="jumbotron" id="friend-jumbotron">
                <h1 style="color: rgb(255,165,0);">Aggiungi un amico!</h1>
                <p>Invia una richiesta d'amicizia ad un altro utente scrivendo il suo id qui sotto!</p>
                <input class="border rounded d-block" type="text" id="friend-input">
                <a class="btn btn-primary border rounded" type="button" id="inviaRichiesta">invia richiesta</a>
            </div>
        </div>
    </div>
    <jsp:include page="footer.html" />
    </body>
</html>
