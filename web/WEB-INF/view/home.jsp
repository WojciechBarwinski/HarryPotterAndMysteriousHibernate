<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>

    <title>Home Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">


</head>
<body>
<%@include file="/WEB-INF/component/header.jsp"%>

<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-3 col-md-offset-3"></div>
        
        <div class="col-md-6 col-md-offset-3">

            <h2>Welcome to magical world of Harry Potter!</h2>
            <p align="justify">Ever wondered if it's hard to manage such a huge school as Hogwarts from organizational point of view?
                It must be a tough task and a nasty piece of work to mind the store and keep all information about students
                and workers up-to-date. But thanks to this application it's no longer the problem!
                We give you an user-friendly interactive database which will make administrating Hogwarts School Of Witchcraft and Wizardry a pure pleasure!</p>

            <h4>Main functionalities:</h4>

            <ul>
                <li>adding and deleting characters to/from database</li>
                <li>updating information about characters</li>
                <li>giving characters pets(each character in Hogwarts can have only one!) and items</li>
                <li>keeping and updating information about characters place of residence</li>
                <li>transparent view page for each character</li>
            </ul><br/>

            <h5>More features to come in application's next version!</h5><br/>

            <div align="center">
                <h4>Technology stack: Java 11, JSP, JSTL, Servlets, jUnit, Hibernate, MySQL, Tomcat, HTML, CSS, Bootstrap</h4>
                <br/>
                <h5>See our GitHub and LinkedIn :)</h5>
                <br/>
                <h5>Authors:</h5>
                <h5>Maja Barwińska</h5>
                <a href="https://github.com/mbarwinska">
                    https://github.com/mbarwinska
                </a><br/>
                <a href="https://www.linkedin.com/in/maja-barwińska-/">
                    https://www.linkedin.com/in/maja-barwińska-/
                </a><br/>

                <h5>Wojciech Barwiński</h5>
                <a href="https://github.com/WojciechBarwinski">
                    https://github.com/WojciechBarwinski
                </a><br/>
                <a href="https://www.linkedin.com/in/wojciech-barwiński/">
                    https://www.linkedin.com/in/wojciech-barwiński/
                </a><br/><br/>


                PS If you happen to find any bugs, please let us now so that we can fix them! We're still learners ;)
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
