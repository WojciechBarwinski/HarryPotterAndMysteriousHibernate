<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <form class="form-inline">
        <div class="navbar-brand mr-5 font-weight-bold font-italic">Harry Potter Application</div>
        <div class="text-center">
        <button class="btn btn-light btn-lg mr-5" type="submit">Characters</button>
        <button class="btn btn-light btn-lg mr-5" type="submit">Locations</button>
        <button class="btn btn-light btn-lg mr-5" type="submit">Pets</button>
        <button class="btn btn-light btn-lg mr-5" type="submit">Students</button>
        <button class="btn btn-light btn-lg mr-5" type="submit">Jobs</button>
        <button class="btn btn-light btn-lg mr-5" type="submit">Items</button>
            <img src="/image/logo.png" width="60" height="60"/>
        </div>
    </form>
</nav>
<c:forEach items="${charactersList}" var="character">
    Id: ${character.id}</br>
    First name: ${character.firstName}</br>
    Last name: ${character.lastName}</br>
    Birth date: ${character.birthDate}</br>
</c:forEach>

<script src="webjars/bootstrap/4.4.1-1.4.0/js/bootstrap.min.js"/>
<script src="webjars/jquery/3.4.1/jquery.min.js"/>
</body>
</html>
