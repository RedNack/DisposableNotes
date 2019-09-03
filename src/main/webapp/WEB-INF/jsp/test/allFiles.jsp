<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Загловок</title>
</head>
<body>
<c:forEach var="file" items="${fileList}">
    <h2> <a href="${file}">${file}</a>
    </h2>
</c:forEach>

</body>
</html>
