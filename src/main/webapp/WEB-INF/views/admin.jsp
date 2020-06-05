<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список пользователей</title>
    <link href="../resources/css/style.css" type="text/css" rel="stylesheet" media="all"/>
</head>
<body>
<div id="headerMain">
    <div id="header">
        <p class="userRole">Роль:
            <span class="userRole">${user.role}</span>
            <a href="/logout">Выйти из системы</a>
        </p>
        <h2>Добавление нового пользователя:</h2>
        <form action="${pageContext.request.contextPath}admin/add" method="POST" name="addUser">
            <span>
                <label>Имя: <input type="text" name="firstName" value="" placeholder="Имя" autofocus required></label>
            </span>
            <span>
                <label>Фамилия: <input type="text" name="lastName" value="" placeholder="Фамилия" required></label>
            </span>
            <br>
            <span>
                <label>Возраст: <input type="number" name="age" value="" min="1" max="127" required></label>
            </span>
            <span>
                <label>Роль: <select name="role" required>
                    <option value="admin">admin</option>
                    <option value="user" selected>user</option>
                </select></label>
            </span>
            <br>
            <span>
                <label>Email: <input type="email" name="email" value="" placeholder="Email" required></label>
            </span>
            <span>
                <label>Пароль: <input type="password" name="password" value="" placeholder="Пароль" required></label>
            </span>
            <br>
            <input type="submit" value="Добавить">
        </form>
        <h2>Список пользователей:</h2><br>
    </div>
</div>

<c:if test="${users ne null}">
    <c:forEach items="${users}" var="user">
        <div class="userBlock">
            <span>Имя: <b>${user.firstName}</b></span>
            <span>Фамилия: <b>${user.lastName}</b></span>
            <br>
            <span>Возраст: <b>${user.age}</b></span>
            <span>Email: <b>${user.email}</b></span>
            <br>
            <span>Роль: <b>${user.role}</b></span>
            <br>
            <span>
                <form action="${pageContext.request.contextPath}/admin/del" method="GET" name="deleteUser">
                    <input type="number" hidden name="id" value="${user.id}">
                    <span><input type="submit" value="Удалить"></span>
                </form>
            </span>
            <span>
                <form action="${pageContext.request.contextPath}/admin/edit" method="GET" name="editUser">
                <input type="number" hidden name="id" value="${user.id}">
                <span><input type="submit" value="Изменить"></span>
                </form>
            </span>
            <hr/>
        </div>
    </c:forEach>
</c:if>

<c:if test="${users eq null}">
    <div class="userBlock">
        <p id="noDB"><b>База девственно чиста, скорее добавьте нового пользователя!</b></p>
    </div>
</c:if>

</body>
</html>
