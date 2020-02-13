<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Characters</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<%@include file="/WEB-INF/component/header.jsp"%>
<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="card bg-warning">

                <div class="card-header">
                    <h1 class="card-title">Harry Potter Characters</h1>
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
                            </tr>
                            </thead>
                            <c:forEach items="${charactersList}" var="character">
                                <tr>
                                    <td>${character.id}</td>
                                    <td>${character.firstName}</td>
                                    <td>${character.lastName}</td>
                                    <td>${character.birthDate}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form class="form-inline" action="/add-character" method="get">
   <p> <label for="firstName" class="mr-sm-2">First Name:</label>
    <input type="email" class="form-control mb-2 mr-sm-2" placeholder="First name" id="firstName"></p>
    <p> <label for="lastName" class="mr-sm-2">Last Name:</label>
    <input type="password" class="form-control mb-2 mr-sm-2" placeholder="Last name" id="lastName"></p>
    <p><label for="birthDate" class="mr-sm-2">BirthDate:</label>
    <input type="password" class="form-control mb-2 mr-sm-2" placeholder="YYYY-MM-DD" id="birthDate"></p>
    <button type="submit" class="btn btn-primary mb-2">Add</button>
</form>
</body>


<script src="webjars/bootstrap/4.4.1-1.4.0/js/bootstrap.min.js"/>
<script src="webjars/jquery/3.4.1/jquery.min.js"/>
</body>
</html>
