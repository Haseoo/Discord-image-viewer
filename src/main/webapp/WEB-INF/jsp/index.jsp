<jsp:useBean id="serverName" scope="request" type="java.lang.String"/>
<jsp:useBean id="images" scope="request" type="java.util.Collection"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="font-size: 20pt; text-align: center">
    <p>Obrazy z ${serverName}</p>
</div>
<c:forEach items="${images}" var="entry">
    <div class="container">
        <p>Wysyłany przez: ${entry.username}</p>
        <p>Na kanale: ${entry.channel}</p>
        <p>${entry.sendTime}</p>
        <a href="${entry.imageUrl}">${entry.imageUrl}</a>
    </div>
</c:forEach>
</body>
</html>
