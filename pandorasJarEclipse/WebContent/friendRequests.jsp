<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>Richieste d'amicizia</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/friendRequestsScript.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
    <link rel="stylesheet" href="css/friendRequestsStyle.css">
</head>

<body id="page-top">
<jsp:include page="header.jsp" />
<div class="row" id="firstRow">
    <div class="col-3" id="divProfileMenu">
        <jsp:include page="profileMenu.jsp"></jsp:include>
    </div>
    <div class="col" id="wrapper">
    <div class="d-flex flex-column" id="content-wrapper" style="background-color: rgba(248,249,252,0);">
        <div id="content">
            <div class="row">
                <div class="col list-container">
                    <div class="row">
                        <div class="col-md-8">
                            <h2 style="width: 343px;" class="color-orange">Richieste inviate:</h2>
                        </div>
                        <div class="col-md-4 d-flex justify-content-end align-self-start"><i class="fas fa-search d-xl-flex justify-content-xl-center align-items-xl-center" ></i><input placeholder="Insert id" class="border rounded d-xl-flex justify-content-xl-center align-items-xl-center search-field" type="search" id="search-field-sent" style="background-color: #eaeaea;width: 80%;height: 38px;padding: 0px;margin-left: 17px;"
                                                                                                                                                                                         name="search"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped table-bordered table-options">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Azione</th>
                                </tr>
                                </thead>
                                <tbody id="tbody-sent">
                                <c:forEach items="${sentRequests}" var="request">
                                    <tr class="tr-color-sent">
                                        <td>${request.id}</td>
                                        <td>${request.username}</td>
                                        <td>${request.email}</td>
                                        <td>
                                            <button type="button" id="deleteRequest" onclick="deleteRequestFriend(${request.id})" class="btn btn-danger btn-table"><i class="far fa-times-circle d-xl-flex justify-content-xl-center align-items-xl-center"></i></button>
                                            <a type="button" href="/profile?id=${request.id}" class="btn btn-primary btn-table"><i class="fas fa-address-card d-xl-flex justify-content-xl-center align-items-xl-center"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col list-container">
                    <div class="row">
                        <div class="col-md-8">
                            <h2 style="width: 343px;" class="color-orange">Richieste ricevute:</h2>
                        </div>
                        <div class="col-md-4 d-flex justify-content-end align-self-start"><i class="fas fa-search d-xl-flex justify-content-xl-center align-items-xl-center" ></i><input placeholder="Insert id" class="border rounded d-xl-flex justify-content-xl-center align-items-xl-center search-field" type="search" id="search-field-received" style="background-color: #eaeaea;width: 80%;height: 38px;padding: 0px;margin-left: 17px;"
                                                                                                                                                                                         name="search"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped table-bordered table-options">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Azione</th>
                                </tr>
                                </thead>
                                <tbody id="tbody-received">
                                <c:forEach items="${receivedRequests}" var="request">
                                    <tr class="tr-color-received">
                                        <td>${request.id}</td>
                                        <td>${request.username}</td>
                                        <td>${request.email}</td>
                                        <td>
                                            <button type="button" id="addFriend" onclick="addFriend(${request.id})" class="btn btn-success btn-table"><i class="fas fa-check d-xl-flex justify-content-xl-center align-items-xl-center"></i></button>
                                            <a type="button" href="/profile?id=${request.id}" class="btn btn-primary btn-table"><i class="fas fa-address-card d-xl-flex justify-content-xl-center align-items-xl-center"></i></a>
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
    </div>
</div>
</div>
<jsp:include page="footer.html" />
</body>

</html>