<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создать записку</title>
</head>
<body>
<c:url value="/addNote" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" id="id">
    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="text">Text</label>
    <input type="text" name="text" id="text">
    <label for="pass">Password</label>
    <input type="text" name="pass" id="pass">
    <input type="submit" value="Ok !">
</form>
</body>
</html>