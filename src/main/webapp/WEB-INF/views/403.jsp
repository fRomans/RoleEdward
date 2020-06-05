<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>403</title>
    <link href="../resources/css/style.css" type="text/css" rel="stylesheet" media="all" />
</head>
<body>
<div class="userBlock">
    <p class="statusCode">403</p>
    <p class="statusCodeText"><b>Доступ запрещен!</b></p>
    <form action="${pageContext.request.contextPath}/" method="GET">
        <input type="submit" value="Домой" autofocus>
    </form>
</div>
</body>
</html>
