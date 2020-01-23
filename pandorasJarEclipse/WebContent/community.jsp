<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

<head>
    <title>Community</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/communityScript.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
    <link rel="stylesheet" href="css/communityStyle.css">
</head>

<body id="page-top" style="background-color: #284c67;">
<jsp:include page="header.jsp" />
<div class="row" id="firstRow">
    <div class="col" id="wrapper">
    <div class="d-flex flex-column" id="content-wrapper" style="background-color: rgba(248,249,252,0);">
        <div id="content">
            <c:if test="${logged}">
                <div class="row" style="margin-top: 2%;">
                    <div class="col-md-6" style="margin: auto;">
                        <form class="border rounded form-addPost">
                            <h1 class="text-center" style="font-size: 40px;margin-bottom: 4%; color:white;">Aggiungi un post!</h1>
                            <input class="border rounded input-addPost" type="text" id="userId" readonly value="${userId}">
                            <input class="border rounded input-addPost" type="text" id="titolo" placeholder="Titolo">
                            <input class="border rounded input-addPost" type="text" id="descrizione" placeholder="Descrizione">
                            <input class="border rounded input-addPost" type="text" id="immagine" placeholder="Immagine">
                            <button class="btn btn-primary" id="btn-addPost" type="button" style="margin-right: 0px;margin-left: 0px;background-color: #00baff;">Salva</button>
                        </form>
                    </div>
                </div>
            </c:if>
            <div id="container">
                <c:forEach items="${posts}" var="post">
                    <div class="row filtr-container">
                        <div class="col-md-11 col-lg-6 filtr-item" style="margin: auto;">
                            <div class="card border-dark">
                                <div class="card-header bg-dark text-light">
                                    <h5 class="m-0 h5-title">${post.title}</h5>
                                    <c:if test="${userId == post.authorId}">
                                        <button type="button" id="deletePost" onclick="deletePost(${post.id})" class="btn btn-danger">
                                            <i class="far fa-times-circle d-xl-flex justify-content-xl-center align-items-xl-center"></i>
                                        </button>
                                    </c:if>
                                </div>
                                <img class="img-fluid card-img w-100 d-block rounded-0" src="${post.image}">
                                <div class="card-body">
                                    <p class="card-text">Autore: <a href="/profile?id=${post.authorId}">${post.author}</a></p>
                                    <p class="card-text">${post.description}</p>
                                </div>
                                <div class="d-flex card-footer">
                                    <c:if test="${logged}">
                                        <button class="btn btn-success btn-sm" type="button" onclick="addLikeDislike(${post.id},1)"><i class="fas fa-thumbs-up"></i> ${post.numLike}</button>
                                        <button class="btn btn-danger btn-sm ml-auto" type="button" onclick="addLikeDislike(${post.id},0)"><i class="fas fa-thumbs-down"></i> ${post.numDislike}</button>
                                    </c:if>
                                    <c:if test="${not logged}">
                                        <button class="btn btn-success btn-sm" type="button"><i class="fas fa-thumbs-up"></i> ${post.numLike}</button>
                                        <button class="btn btn-danger btn-sm ml-auto" type="button"><i class="fas fa-thumbs-down"></i> ${post.numDislike}</button>
                                    </c:if>
                                    <button class="btn btn-primary btn-sm ml-auto btn-open-comments" onclick="openComments(${post.id})" type="button"><i class="fas fa-comments"></i> ${fn:length(post.comments)}</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="commentsList" class="modal fade">
                        <div class="modal-dialog modal-comments">
                            <h2 class="color-orange">Commenti:</h2>
                            <div class="modal-content">
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="footer.html" />
</body>
</html>