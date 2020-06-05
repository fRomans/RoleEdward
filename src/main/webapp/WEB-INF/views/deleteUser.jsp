<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удаление пользователя</title>
    <link href="../resources/css/style.css" type="text/css" rel="stylesheet" media="all" />
</head>
<body>
<div class="userBlock">
    <h2>Удаление пользователя</h2>
    <p><b>Вы уверены, что хотите окончательно удалить пользователя из базы?</b></p>
    <span>Имя: <b>${user.firstName}</b></span>
    <span>Фамилия: <b>${user.lastName}</b></span>
    <br>
    <span>Возраст: <b>${user.age}</b></span>
    <span>Email: <b>${user.email}</b></span>
    <br>
    <span>Роль: <b>${user.role}</b></span>
    <br><br>
    <span>
        <form action="${pageContext.request.contextPath}/admin/del" method="POST">
            <input type="number" hidden name="id" value="${user.id}">
            <span><input type="submit" value="Удалить" autofocus></span>
        </form>
    </span>
    <span>
        <form action="${pageContext.request.contextPath}/admin" method="GET">
            <span><input type="submit" value="Отмена"></span>
        </form>
    </span>
</div>
</body>
</html>
