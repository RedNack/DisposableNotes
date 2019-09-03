<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Внимание ! После прочтения записка удалится !</h1>
<c:url value="/id=${note}" var="var"/>

<form action="${var}" method="POST">
    <label for="pass">Пароль</label>
    <input type="text" name="password" id="pass">
    <input type="submit" value="Открыть">
</form>

<c:url value="/save/id=${note}" var="var2"/>
<form action="${var2}" method="POST">
    <label for="pass2">Пароль</label>
    <input type="text" name="password" id="pass2">
    <input type="submit" value="Сохранить">
</form>

<h2><a href="/">Вернуться на главную страницу</a></h2>
</body>
</html>
