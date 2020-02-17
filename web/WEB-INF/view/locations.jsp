<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maja
  Date: 2020-02-17
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Locations</title>
</head>
<body>
<%@include file="/WEB-INF/component/header.jsp"%>
<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-2 col-md-offset-3"></div>
        <div class="col-md-8 col-md-offset-3">

            <c:forEach items="${locationsList}" var="location">
                <div class="container-fluid" style="margin: 10px">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-3" align="center">
                            <h3>${location.name}</h3>
                            <img src="${location.imagePath}" class="img-fluid"/>
                        </div>
                        <div class="col-md-3 col-md-offset-3" style="padding-top: 35px">
                            <h5>Residents</h5>
                            <ul>
                                <c:forEach items="${location.residents}" var="resident">
                                    <li>${resident.firstName} ${resident.lastName}</li>
                                </c:forEach>

                            </ul>
                        </div>
                        <div class="col-md-5 col-md-offset-3" style="padding-top: 30px">
                            <form class="form-inline justify-content-center" action="/add-resident" method="post">
                            <p>
                                <label for="residentToAdd" class="mr-sm-2">Add residents</label>
                                <select class="form-control mr-sm-2" id="residentToAdd" name="residentToAdd">
                                    <option value=" " disabled selected value>Select</option>
                                    <c:forEach items="${residentsAvailable}" var="resident">
                                        <option>${resident.firstName} ${resident.lastName}</option>
                                    </c:forEach>
                                </select>
                                <button type="submit" class="btn btn-primary mb-2">Add</button>
                            </p>
                        </form>
                        <form class="form-inline justify-content-center" action="/delete-resident" method="post">
                            <p>
                                <label for="residentToDelete" class="mr-sm-2">Remove residents</label>
                                <select class="form-control mr-sm-2" id="residentToDelete" name="residentToDelete">
                                    <option value=" " disabled selected value>Select</option>
                                    <c:forEach items="${location.residents}" var="resident">
                                        <option>${resident.firstName} ${resident.lastName}</option>
                                    </c:forEach>
                                </select>
                                <button type="submit" class="btn btn-primary mb-2">Remove</button>
                            </p>
                        </form>

                        </div>

                    </div>
                </div>

            </c:forEach>



        </div>
        <div class="col-md-2 col-md-offset-3"></div>
    </div>
</div>
<script src="webjars/bootstrap/4.4.1-1.4.0/js/bootstrap.min.js"/>
<script src="webjars/jquery/3.4.1/jquery.min.js"/>
</body>
</html>
