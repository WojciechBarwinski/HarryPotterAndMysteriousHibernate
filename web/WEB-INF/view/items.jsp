<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <title>${type}</title>
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
            <div class="card text-white bg-dark mb-3">
                <div class="card-header">
                    <h1 class="card-title" align="center">Items</h1>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover ">
                            <thead class="thead-inverse">
                            <tr>
                                <th style="color: white">Name</th>
                                <th style="color: white">Value</th>
                                <th style="color: white">Delete</th>
                            </tr>
                            </thead>
                            <fmt:setLocale value="en_GB"/>
                            <c:forEach items="${itemList}" var="item">
                                <tr>
                                    <td style="color: white">${item.name}</td>
                                    <td style="color: white"><fmt:formatNumber type="currency" minFractionDigits="2"> ${item.value}</fmt:formatNumber></td>
                                    <td>
                                        <form action="/delete-item" method="post">
                                            <button type="submit" class="btn btn-danger" name="itemToDelete" value=${item.id} >Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>

            <form class="form-inline justify-content-center" action="/add-item" method="post">

                <p><label for="name" class="mr-sm-2">Name:</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Name" name="name" id="name"></p>

                <p><label for="value" class="mr-sm-2">Value:</label>
                    <input type="number" min="1" max="10000000" class="form-control mb-2 mr-sm-2" placeholder="£ Value" name="value" id="value"></p>

                <button type="submit" class="btn btn-primary mb-2" value="${type}" name="type">Add</button>
            </form>

            <p class="error" align="center">${noValue}</p>
            <p class="error" align="center">${wrongName}</p>
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


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
