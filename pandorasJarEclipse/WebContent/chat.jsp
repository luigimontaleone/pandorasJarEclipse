<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>chat</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/chatStyle.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/chatScript.js"></script>
</head>

<body>
<div class="bootstrap_chat">
    <div class="container py-5 px-4">
        <!-- For demo purpose-->
        <header class="text-center">
            <h1 class="display-4 text-white">Messaggi recenti</h1>
        </header>

        <div class="row rounded-lg overflow-hidden shadow">
            <!-- Users box-->
            <div class="col-5 px-0">
                <div class="bg-white">
                    <div class="bg-gray px-4 py-2 bg-light">
                        <p class="h5 mb-0 py-1">Tutte le tue chat</p>
                    </div>
                    <div class="messages-box">
                        <div id="divChat" class="list-group rounded-0">
                            <c:set var="index" scope="request" value="${0}"></c:set>
                            <c:forEach items="${usersBox}" var="userBox">
                            <c:if test="${index == 0}">
                                <a id="${userBox.userId}" class="list-group-item list-group-item-action active text-white rounded-0 first">
                            </c:if>
                            <c:if test="${index > 0}">
                                <a id="${userBox.userId}" class="list-group-item list-group-item-action rounded-0">
                            </c:if>
                                    <div class="media">
                                        <img src="/PrintImage?id=${userBox.userId}" alt="user" width="50" class="rounded-circle">
                                        <div class="media-body ml-4">
                                            <div class="d-flex align-items-center justify-content-between mb-1">
                                                <h6 class="mb-0">${userBox.username}</h6>
                                            </div>
                                            <p class="font-italic mb-0 text-small">Apri per chattare con ${userBox.username}</p>
                                        </div>
                                    </div>
                                </a>
                            <c:set var="index" scope="request" value="${index + 1}"></c:set>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Chat Box-->
            <div class="col-7 px-0">
                <div id="messages" class="px-4 py-5 chat-box bg-white">

                </div>

                <!-- Typing area -->
                <form class="bg-light">
                    <div id="${userId}" class="input-group">
                        <input type="text" id="typedText" placeholder="Scrivi un messaggio" class="form-control rounded-0 border-0 py-4 bg-light">
                        <a class="input-group-append">
                            <button id="sendMessage" type="button" onclick="send()" class="btn btn-link"><i class="fa fa-paper-plane"></i></button>
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>