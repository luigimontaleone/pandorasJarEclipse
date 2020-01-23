<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>LibraryGamePage</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
</head>

<body style="background-color: #284c67">
    <div class="row" id="firstRow">
        <div class="col-xl-7" style="width: 60%;">
            <div class="carousel slide" data-ride="carousel" id="carousel-1">
                <div class="carousel-inner" role="listbox">
                    <c:set var="index" scope="request" value="${0}"></c:set>
                    <c:forEach items="${gameLibrary.previewsIMG}" var="img">
                    <c:if test="${index == 0}">
                    <div class="carousel-item size-div-preview active">
                        </c:if>
                        <c:if test="${index > 0}">
                        <div class="carousel-item size-div-preview">
                            </c:if>
                            <img class="w-100 d-block float-left size-div-preview" src="${img}">
                        </div>

                        <c:set var="index" scope="request" value="${index + 1}"></c:set>
                        </c:forEach>

                        <c:forEach items="${gameLibrary.previewsVID}" var="video">
                        <c:if test="${index == 0}">
                        <div class="carousel-item size-div-preview active">
                            </c:if>
                            <c:if test="${index > 0}">
                            <div class="carousel-item size-div-preview">
                                </c:if>
                                <iframe src="${video}" class="size-div-preview" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                            </div>
                            <c:set var="index" scope="request" value="${index + 1}"></c:set>
                            </c:forEach>

                        </div>
                        <div><a class="carousel-control-prev" href="#carousel-1" role="button" data-slide="prev"><span class="carousel-control-prev-icon"></span><span class="sr-only">Previous</span></a><a class="carousel-control-next" href="#carousel-1" role="button"
                                                                                                                                                                                                             data-slide="next"><span class="carousel-control-next-icon"></span><span class="sr-only">Next</span></a></div>
                        <ol class="carousel-indicators">
                            <c:forEach items="${totalSize}" var="i">
                                <c:if test="${i == 0}">
                                    <li data-target="#carousel-1" data-slide-to="${i}" class="active"></li>
                                </c:if>
                                <c:if test="${i > 0}">
                                    <li data-target="#carousel-1" data-slide-to="${i}"></li>
                                </c:if>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
                <div class="col float-left" style="width: 40%;">
                    <h1 class="text-center color-orange" >${gameLibrary.name}</h1>
                    <div>
                        <textarea readonly class="border rounded" id="gameDescription" rows="5">${gameLibrary.description}</textarea>
                        <div>
                            <label class="d-block label-game-info">Data Rilascio : ${gameLibrary.release}</label>
                            <label class="d-block label-game-info">Sviluppatore : <a href="/profile?id=${gameLibrary.idDeveloper}">${developer}</a></label>
                        </div>
                        <form class="text-center" method="post" action="/help?emailTo=${gameLibrary.helpEmail}&send=false">
                            <button class="btn btn-primary border rounded background-color-orange" type="submit" id="richiediAssistenza">Richiedi assistenza</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row" id="secondRow">
                <div class="col-xl-7" style="width: 60%;">
                    <p class="d-inline" id="pCategory">Questo gioco appartiene alla categoria :</p><a href="/SearchFilterIndex?categoria=${gameLibrary.category}&prezzo=&valutazione=" style="font-size: 20px;">${gameLibrary.category}</a>
                </div>
                <div class="col float-left" style="width: 40%;">
                    <div class="float-left">
                        <p id="prezzo">ACQUISTATO PER: ${gameLibrary.price}€</p>
                    </div>
                    <div class ="float-right">
                        <button id="download" class="btn btn-success">Download!</button>
                    </div>
                </div>
            </div>
            <div class="row" id="thirdRow">
                <div id="reviewsDiv" class="col">
                    <c:forEach items="${reviews}" var="review">
                        <div class="border rounded" id="divReviews">
                            <label class="d-block color-orange" style="font-size: 20px;"><a href="/profile?id=${review.author}">${review.username}</a></label>
                            <label class="d-block color-orange" style="font-size: 20px;">${review.stars}</label>
                            <p class="p-review">${review.comment}</p>
                        </div>
                    </c:forEach>
                    <button style="margin-top: 1%;" type="button" id="loadMoreRevs" onclick="addMoreRev(${gameLibrary.id})" class="btn btn-secondary btn-sm btn-block">Carica tutte le recensioni</button>
                </div>
            </div>
            <div class="row" id="fourthRow">
                <div class="form-group border rounded" id="form-add-comment">
                    <form>
                        <div class="row margin-auto" id="row-comment">
                            <div class="col">
                                <textarea class="form-control" id="commentContent" name="content" placeholder="Inserisci qui il tuo commento" required></textarea>
                            </div>
                        </div>
                        <div class="row margin-auto">
                            <div class="col-4" id="div-label-star">
                                <label id="insert-stars">Inserisci le tue stelle:</label>
                            </div>
                            <div class="col-2" id="div-input-star">
                                <input type="number" min="0" max="5" step="1" class="form-control" id="commentStars"/>
                            </div>
                        </div>
                        <div class="row margin-auto" id="div-button-comment">
                            <button class="btn btn-light" type="button" id="addComment">Aggiungi commento</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row" id="fifthRow">
                <table id="ranking" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th class="th-color">#</th>
                        <th class="th-color">Username</th>
                        <th class="th-color">Score</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="index" scope="request" value="${0}"></c:set>
                    <c:forEach items="${ranking}" var="rankI">
                        <c:set var="index" scope="request" value="${index + 1}"></c:set>
                        <tr>
                            <td class="td-color">${index}</td>
                            <td class="td-color">${rankI.username}</td>
                            <td class="td-color">${rankI.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>