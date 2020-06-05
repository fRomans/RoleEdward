<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <link href="../resources/css/style.css" type="text/css" rel="stylesheet" media="all"/>
</head>
<body>
<div class="userBlock">
    <h2>Авторизация</h2>
    <p><strong>Введите логин (email) и пароль для входа в систему:</strong></p>
    <form id="userLogin" action="${pageContext.request.contextPath}/" method="POST">
        <span>
            <label>Логин (email): <input type="email" name="email" value="" autofocus required></label>
        </span>
        <br><br>
        <span>
            <label>Пароль: <input type="password" name="password" value="" required></label>
        </span>
        <br><br>
        <span><input type="submit" value="Войти"></span>
    </form>
    <c:if test="${warnText ne null}">
        <p class="warnText">${warnText}</p>
    </c:if>
</div>
</body>
</html>
