<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
    <link href="../resources/css/style.css" type="text/css" rel="stylesheet" media="all"/>
</head>
<body>
<div class="userBlock">
    <p class="statusCode">404</p>
    <p class="statusCodeText"><b>Пользователь с таким ID не найден в базе или неверный запрос!</b></p>
    <form action="${pageContext.request.contextPath}/admin" method="GET">
        <input type="submit" value="Назад" autofocus>
    </form>
</div>
</body>
</html>
