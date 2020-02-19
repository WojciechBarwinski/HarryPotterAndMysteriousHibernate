<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Characters</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
    p.error {
        color: red;
    }
</style>
</head>
<body>
<%@include file="/WEB-INF/component/header.jsp"%>
<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-3 col-md-offset-3"></div>
        <div class="col-md-6 col-md-offset-3">

            <form action="/find-character" class="form-inline justify-content-center" method="post">
                <label for="id" class="mr-sm-2">Id to find:</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" placeholder="id" name="idToFind" id="id">
                <button type="submit" class="btn btn-primary" name="idToDelete">Find</button>
            </form>
            <p class="error" align="center">${noId}</p>
            <p class="error" align="center">${invalidId}</p>

            <div class="card bg-warning">
                <div class="card-header">
                    <h1 class="card-title" align="center">Harry Potter Characters</h1>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover ">
                            <thead class="thead-inverse">
                            <tr>
                                <th>ID</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Birth date</th>
                                <th>View</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <c:forEach items="${charactersList}" var="character">
                                <tr>
                                    <td>${character.id}</td>
                                    <td>${character.firstName}</td>
                                    <td>${character.lastName}</td>
                                    <td>${character.birthDate}</td>

                                    <td>
                                            <a href="/view-character?idToView=${character.id}">
                                                <button type="submit" class="btn btn-primary" name="idToView">View</button>
                                            </a>

                                    </td>

                                    <td>
                                        <form action="/delete-character" method="post">
                                            <button type="submit" class="btn btn-danger" name="idToDelete" value=${character.id} >Delete</button>
                                        </form>
                                    </td>

                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>

            <form class="form-inline justify-content-center" action="/add-character" method="post">
                <p> <label for="firstName" class="mr-sm-2">First Name:</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" placeholder="First name" name="firstName" id="firstName"></p>

                <p> <label for="lastName" class="mr-sm-2">Last Name:</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Last name" name="lastName" id="lastName"></p>

                <p><label for="birthDate" class="mr-sm-2">BirthDate:</label>
                    <input type="date" class="form-control mb-2 mr-sm-2" placeholder="YYYY-MM-DD" name="birthDate" id="birthDate" value=""></p>
                <button type="submit" class="btn btn-primary mb-2">Add</button>
            </form>
            <p class="error" align="center">${noValue}</p>
            <p class="error" align="center">${wrongName}</p>
            <p class="error" align="center">${wrongLastName}</p>
            <p class="error" align="center">${wrongData}</p>
            <p class="error" align="center">${characterExists}</p>

        </div>
        <div class="col-md-3 col-md-offset-3"></div>
    </div>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
