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
    <label for="pass">Password</label>
    <input type="text" name="password" id="pass">
    <input type="submit" value="Ок!">
</form>
<h2><a href="/">Вернуться на главную страницу</a></h2>
</body>
</html>
