<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${character.firstName} ${character.lastName}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<%@include file="/WEB-INF/component/header.jsp"%>
<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-3 col-md-offset-3"></div>
        <div class="col-md-2 col-md-offset-3">
            <img src="" class="img-fluid" alt="/image/tmpFoto.jpg">
        </div>
        <div class="col-md-4 col-md-offset-3">
            <h3>${character.firstName} ${character.lastName}</h3>
            <h5>Birth date: ${character.birthDate}</h5>
            <h5>Pet: ${character.pet.species} - ${character.pet.name}</h5>
        </div>
        <div class="col-md-3 col-md-offset-3"></div>
    </div>
</div>



<script src="webjars/bootstrap/4.4.1-1.4.0/js/bootstrap.min.js"/>
<script src="webjars/jquery/3.4.1/jquery.min.js"/>
</body>
</html>
