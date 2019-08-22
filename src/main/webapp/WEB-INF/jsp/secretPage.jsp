<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Секретная страничка</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>text</th>
        <th>password</th>
    </tr>
    <c:forEach var="note" items="${noteList}">
        <tr>
            <td>${note.id}</td>
            <td>${note.name}</td>
            <td>${note.text}</td>
            <td>${note.pass}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>