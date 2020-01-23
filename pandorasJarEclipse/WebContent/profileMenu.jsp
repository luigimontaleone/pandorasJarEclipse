<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/menuStyle.css">
</head>
<body>
    <div id="navMenu">
        <ul class="nav nav-tabs flex-column text-left" id="ulMenu">
            <li class="nav-item"><a class="nav-link" href="profile" style="color: rgba(255,255,255,0.5);">General</a></li>
            <li class="nav-item"><a class="nav-link" href="friendsList" style="color: rgba(255,255,255,0.5);">Friends</a></li>
            <li class="nav-item"><a class="nav-link" href="inviteFriend" style="color: rgba(255,255,255,0.5);">Invite friend</a></li>
            <li class="nav-item"><a class="nav-link" href="friendRequests" style="color: rgba(255,255,255,0.5);">Richieste in sospeso</a></li>
            <li class="nav-item"><a class="nav-link" href="chat" style="color: rgba(255,255,255,0.5);">Chat with friends</a></li>
            <li class="nav-item"><a class="nav-link" href="UserStats" style="color: rgba(255,255,255,0.5);">Game Statistics</a></li>
            <li class="nav-item"><a class="nav-link" href="devStats" style="color: rgba(255,255,255,0.5);">Developer Statistics</a></li>
        </ul>
    </div>
</body>
</html>