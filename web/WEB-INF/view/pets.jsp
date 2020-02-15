<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Pets</title>
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

            <form action="/find-pet" class="form-inline justify-content-center" method="post">
                <label for="id" class="mr-sm-2">Id to find:</label>
                <input type="text" class="form-control mb-2 mr-sm-2" placeholder="id" name="idToFind" id="id">
                <button type="submit" class="btn btn-primary" name="idToDelete">Find</button>
            </form>
            <p class="error" align="center">${noId}</p>
            <p class="error" align="center">${invalidId}</p>

            <div class="card bg-success">
                <div class="card-header">
                    <h1 class="card-title" align="center">Pets</h1>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover ">
                            <thead class="thead-inverse">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Species</th>
                                <th>View</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <c:forEach items="${petsList}" var="pet">
                                <tr>
                                    <td>${pet.id}</td>
                                    <td>${pet.name}</td>
                                    <td>${pet.species}</td>
                                    <td>
                                        <a href="/view-pet?idToView=${pet.id}">
                                            <button type="submit" class="btn btn-primary" name="idToView">View</button>
                                        </a>
                                    </td>
                                    <td>
                                        <form action="/delete-pet" method="post">
                                            <button type="submit" class="btn btn-danger" name="idToDelete"
                                                    value=${pet.id}>Delete
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>


            <form class="form-inline justify-content-center" action="/add-pet" method="post">

                <p><label for="name" class="mr-sm-2">Name:</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Name" name="name" id="name"></p>

                <p><label for="species" class="mr-sm-2">Species:</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Species" name="species" id="species"></p>


                <p><label for="owners" class="mr-sm-2">Owners:</label>
                    <select class="form-control mr-sm-2" id="owners" name="owner">
                        <option value="" disabled selected>Select</option>
                        <c:forEach items="${ownersAvailable}" var="owner">
                            <option>${owner.firstName} ${owner.lastName}</option>
                        </c:forEach>
                    </select>
                </p>


                <button type="submit" class="btn btn-primary mb-2">Add</button>
            </form>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>


        </div>
        <div class="col-md-3 col-md-offset-3"></div>
    </div>
</div>


<script src="webjars/bootstrap/4.4.1-1.4.0/js/bootstrap.min.js"/>
<script src="webjars/jquery/3.4.1/jquery.min.js"/>
</body>
</html>
