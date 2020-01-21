<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Friends</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
        <script src="scripts/friendsListScript.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
        <link rel="stylesheet" href="css/friendsListStyle.css">
    </head>
    <body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="row" id="firstRow">
        <div class="col-3" id="divProfileMenu">
            <jsp:include page="profileMenu.jsp"></jsp:include>
        </div>
        <div class="col" >
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <h2 style="width: 343px;" id="h2Friends">Friends:</h2>
                    </div>
                    <div class="col-md-4 d-flex justify-content-end align-self-start"><i class="fas fa-search d-xl-flex justify-content-xl-center align-items-xl-center" ></i><input placeholder="Insert id" class="border rounded d-xl-flex justify-content-xl-center align-items-xl-center search-field" type="search" id="search-field" style="background-color: #eaeaea;width: 80%;height: 38px;padding: 0px;margin-left: 17px;"
                                                                                                                                                                                                            name="search"></div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Username</th>
                                <th>Email</th>
                                <th>Azione</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${friends}" var="friend">
                                <tr class="tr-color">
                                    <td>${friend.id}</td>
                                    <td>${friend.username}</td>
                                    <td>${friend.email}</td>
                                    <td>
                                        <button type="button" id="deleteFriend" onclick="deleteFriend(${friend.id})" class="btn btn-danger"><i class="far fa-trash-alt d-xl-flex justify-content-xl-center align-items-xl-center"></i></button>
                                        <a type="button" href="/profile?id=${friend.id}" class="btn btn-primary"><i class="fas fa-address-card d-xl-flex justify-content-xl-center align-items-xl-center"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.html" />
    </body>
</html>
