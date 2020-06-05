<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <link href="../resources/css/style.css" type="text/css" rel="stylesheet" media="all"/>
</head>
<body>
<div>
    <p class="userRole">Роль:
        <span class="userRole">${user.role}</span>
        <a href="/logout">Выйти из системы</a>
    </p>
</div>
<div class="userBlock">
    <h2>Страница пользователя</h2>
    <span>Имя: <b>${user.firstName}</b></span>
    <span>Фамилия: <b>${user.lastName}</b></span>
    <br>
    <span>Возраст: <b>${user.age}</b></span>
    <span>Email: <b>${user.email}</b></span>
    <br>
    <span>Роль: <b>${user.role}</b></span>
    <br>
</div>
</body>
</html>
