<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${character.firstName} ${character.lastName}</title>
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
        <div class="col-md-6 col-md-offset-3">
            <div class="container-fluid" style="margin: 10px">
                <div class="row">
                    <div class="col-md-4 col-md-offset-3" align="center">
                        <img src="${character.imagePath}" class="img-fluid" alt="">

                        <c:set var="noImg" value="/image/tmpFoto.jpg"/>
                        <c:set var="imagePath" value="${character.imagePath}"/>
                        <c:if test="${noImg == imagePath}">
                            <form action="/add-photo-to-character" class="justify-content-center" method="post"
                                  name="characterId" value="${character.id}">
                                <label for="id" class="mr-sm-2">Link to image</label>
                                <input type="text" class="form-control mb-2 mr-sm-2" placeholder="https: ... .jpg/.png"
                                       name="imagesPath" id="id">
                                <button type="submit" class="btn btn-primary" name="characterId" value=${character.id}>
                                    Add
                                    image
                                </button>
                            </form>
                        </c:if>
                        <p class="error" align="center">${noValue}</p>
                        <p class="error" align="center">${wrongPath}</p>
                    </div>

                    <div class="col-md-8 col-md-offset-3">
                        <h3>${character.firstName} ${character.lastName}</h3>
                        <h5>Birth date: ${character.birthDate}</h5>

                        <c:if test="${fn:length(character.pet.name) > 0}">
                            <h5>Pet: ${character.pet.species} - ${character.pet.name}</h5>
                        </c:if>

                        <c:if test="${character.student.yearOfStudy > 0}">
                            <h5>Student: ${character.student.house.name} : ${character.student.yearOfStudy}</h5>
                        </c:if>

                        <c:if test="${fn:length(character.hogwartsJob) > 0}">
                            <h5>Job/s:
                                <ul>
                                    <c:forEach items="${character.hogwartsJob}" var="job">
                                        <li>${job.positionName}: ${job.salary}</li>
                                    </c:forEach>
                                </ul>
                            </h5>
                        </c:if>

                        <c:if test="${fn:length(character.location) > 0}">
                            <h5>Location: ${character.location}</h5>
                        </c:if>

                        <c:if test="${fn:length(character.itemList) > 0}">
                            <h5>Items:
                                <ul>
                                    <c:forEach items="${character.itemList}" var="item">
                                        <li>${item.type}: ${item.name}</li>
                                    </c:forEach>
                                </ul>
                            </h5>
                        </c:if>
                    </div>
                </div>
            </div>

            <div align="center">
                <h4>Zmień dane postaci</h4>
                <form class="form-inline justify-content-center" action="/update-character" method="post">
                    <p><label for="firstName" class="mr-sm-2">First Name:</label>
                        <input type="text" class="form-control mb-2 mr-sm-2" placeholder="First name" name="firstName"
                               id="firstName"></p>

                    <p><label for="lastName" class="mr-sm-2">Last Name:</label>
                        <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Last name" name="lastName"
                               id="lastName"></p>

                    <p><label for="birthDate" class="mr-sm-2">BirthDate:</label>
                        <input type="date" class="form-control mb-2 mr-sm-2" placeholder="YYYY-MM-DD" name="birthDate"
                               id="birthDate" value=""></p>

                    <button type="submit" class="btn btn-primary mb-2" value="${character.id}">Update</button>
                </form>

                <p class="error" align="center">${noValue}</p>
                <p class="error" align="center">${wrongName}</p>
                <p class="error" align="center">${wrongLastName}</p>
                <p class="error" align="center">${wrongData}</p>
                <p class="error" align="center">${characterExists}</p>
            </div>
            <div align="center">
                <h4>Dodaj usuń wyposażenie</h4>
                <form class="form-inline justify-content-center" action="/add-resident" method="post">
                    <p>
                        <label for="residentToAdd" class="mr-sm-2">Add item</label>

                        <select class="form-control mr-sm-2" id="residentToAdd" name="residentToAdd">
                            <option value=" " disabled selected value>Select</option>
                            <c:forEach items="${residentsAvailable}" var="resident">
                                <option>${resident.firstName} ${resident.lastName}</option>
                            </c:forEach>
                        </select>

                        <button type="submit" class="btn btn-primary mb-2" value="${location.id}" name="locationId">Add</button>

                </form>

                <form class="form-inline justify-content-center" action="/add-resident" method="post">

                        <label for="residentToAdd" class="mr-sm-2">Remove item</label>

                        <select class="form-control mr-sm-2" id="residentToAdd2" name="residentToAdd">
                            <option value=" " disabled selected value>Select</option>
                            <c:forEach items="${residentsAvailable}" var="resident">
                                <option>${resident.firstName} ${resident.lastName}</option>
                            </c:forEach>
                        </select>

                        <button type="submit" class="btn btn-primary mb-2" value="${location.id}" name="locationId">Add</button>
                    </p>
                </form>
            </div>
        </div>
    </div>

    <div class="col-md-3 col-md-offset-3"></div>
</div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
