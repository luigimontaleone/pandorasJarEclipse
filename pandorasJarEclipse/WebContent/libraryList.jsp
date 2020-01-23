<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>LibraryMenu</title>
    <link rel="stylesheet" href="css/menuStyle.css">
</head>

<body>
    <div id="navMenu">
        <ul class="nav nav-tabs flex-column text-left" id="ulMenu">
            <c:forEach items="${library}" var="game">
                <li class="nav-item"><a class="nav-link" href="/library?id=${game.id}" style="color: rgba(255,255,255,0.5);">${game.name}</a></li>
            </c:forEach>
        </ul>
    </div>

</body>

</html>