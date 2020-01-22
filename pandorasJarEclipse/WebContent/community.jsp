<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 1/22/2020
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <c:forEach items="${posts}" var="post">
        <div class="row filtr-container">
            <div class="col-md-11 col-lg-6 filtr-item" data-category="2,3" style="margin: auto;">
                <div class="card border-dark">
                    <div class="card-header bg-dark text-light">
                        <h5 class="m-0">${post.title}</h5>
                    </div>
                    <img class="img-fluid card-img w-100 d-block rounded-0" src="${post.image}">
                    <div class="card-body">
                        <a href="/profile?id=${post.authorId}"><p class="card-text">Autore: ${post.author}</p></a>
                        <p class="card-text">${post.description}</p>
                    </div>
                    <div class="d-flex card-footer">
                        <button class="btn btn-success btn-sm" type="button"><i class="fas fa-thumbs-up"></i>${post.numLike}</button>
                        <button class="btn btn-danger btn-sm ml-auto" type="button"><i class="fas fa-thumbs-down"></i>${post.numDislike}</button>
                        <button class="btn btn-primary btn-sm ml-auto" type="button"><i class="fas fa-comments"></i>${fn:length(post.comments)}</button>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <jsp:include page="footer.html"></jsp:include>
</body>
</html>
