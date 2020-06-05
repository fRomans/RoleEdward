<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование информации о пользователе</title>
    <link href="../resources/css/style.css" type="text/css" rel="stylesheet" media="all"/>
</head>
<body>
<div class="userBlock">
    <h2>Редактирование информации о пользователе</h2>
    <p>После внесения изменений, нажмите кнопку "Изменить"</p>
    <form id="userEdit" action="${pageContext.request.contextPath}/admin/edit" method="POST">
        <span>
            <label>Имя: <input type="text" name="firstName" value="${user.firstName}" autofocus></label>
        </span>
        <span>
            <label>Фамилия: <input type="text" name="lastName" value="${user.lastName}"></label>
        </span>
        <br><br>
        <span>
            <label>Возраст: <input type="number" name="age" value="${user.age}" min="1" max="127"></label>
        </span>
        <span>
            <c:if test="${user.role eq 'admin'}">
                <label>Роль: <select name="role">
                    <option value="admin" selected>admin</option>
                    <option value="user">user</option>
                </select></label>
            </c:if>
            <c:if test="${user.role eq 'user'}">
                <label>Роль: <select name="role">
                    <option value="admin">admin</option>
                    <option value="user" selected>user</option>
                </select></label>
            </c:if>
            <c:if test="${user.role ne 'user' && user.role ne 'admin'}">
                <label>Роль: <select name="role">
                    <option value="admin">admin</option>
                    <option value="user" selected>user</option>
                </select></label>
            </c:if>
        </span>
        <br><br>
        <span>
            <label>Email: <input type="email" name="email" value="${user.email}"></label>
        </span>
        <span>
            <label>Пароль: <input type="password" name="password" value="${user.password}"></label>
        </span>
        <br><br>
        <input type="number" hidden name="id" value="${user.id}">
        <span><input type="submit" value="Изменить"></span>
        <span><input type="reset"></span>
    </form>
</div>
</body>
</html>
