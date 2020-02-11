<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<c:forEach items="${charactersList}" var="character">
    Id: ${character.id}</br>
    First name: ${character.firstName}</br>
    Last name: ${character.lastName}</br>
    Birth date: ${character.birthDate}</br>
</c:forEach>
</body>
</html>
