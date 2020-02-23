<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${pet.name}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        p.error {
            color: red;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/component/header.jsp" %>
<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-3 col-md-offset-3"></div>

        <div class="col-md-2 col-md-offset-3" align="center">
            <img src="${pet.imagePath}" class="img-fluid" alt="">

            <c:set var="noImg" value="/image/tmpFoto.jpg" />
            <c:set var="imagePath" value="${pet.imagePath}"/>
            <c:if test="${noImg == imagePath}">
                <form action="/add-photo-to-pet" class="justify-content-center" method="post" name="petId" value="${pet.id}">
                    <label for="id" class="mr-sm-2">Link to image</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" placeholder="https: ... .jpg/.png" name="imagesPath" id="id">
                    <button type="submit" class="btn btn-primary" name="petId" value=${pet.id}>Add image</button>
                </form>
            </c:if>
            <p class="error" align="center">${noValue}</p>
            <p class="error" align="center">${wrongPath}</p>
        </div>
        <div class="col-md-4 col-md-offset-3">
            <h3>${pet.name}</h3>
            <h5>Species: ${pet.species}</h5>
            <h5>Owner: ${pet.owner.firstName} ${pet.owner.lastName}</h5>
        </div>

        <div class="col-md-3 col-md-offset-3"></div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
