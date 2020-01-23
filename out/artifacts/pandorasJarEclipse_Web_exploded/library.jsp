<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
    <link rel="stylesheet" href="css/libraryStyle.css">
    <script src="scripts/gameDataSheetScript.js"></script>
    <script src="scripts/libraryScript.js"></script>

</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div id="rowLibrary" class="row">
        <div class="col-3" id="divLibraryMenu">
            <jsp:include page="libraryList.jsp"></jsp:include>
        </div>
        <c:if test="${user.getLibrary().size() != 0}">
            <div id="gameDetails" class="col-9">
                <jsp:include page="libraryGamePage.jsp"></jsp:include>
            </div>
        </c:if>
    </div>
    <jsp:include page="footer.html"></jsp:include>
</body>
</html>