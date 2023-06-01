<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title>Сторінка клієнта</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container-fluid">
    <div class="row header">
        <div class="col-12">
            <img src="${contextPath}/resources/img/logo.jpg" alt="Логотип клініки" class="logo">
            <div class="clinic-info">
                <h1>MediClub</h1>
                <p>Адреса: вул. Прикладна, 123</p>
                <p>Телефон: +1234567890</p>
            </div>
        </div>
    </div>
</div>

<div class="container">

    <h2 class="form-heading" style="text-align: center;">Виберіть опцію</h2>
    <form method="POST" action="${contextPath}/client/appointment/create" class="form-custom">
        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Запис до лікаря</button>
        </div>
    </form>
    <form id="appointments-form" class="form-custom">
        <div class="form-group">
            <button id="appointments-button" class="btn btn-lg btn-primary btn-block" type="button">Наявні записи
            </button>
        </div>
    </form>
    <script>
        document.getElementById("appointments-button").addEventListener("click", function () {
            var form = document.getElementById("appointments-form");
            form.method = "GET";
            form.action = "${contextPath}/client/appointments";
            form.submit();
        });
    </script>
    <h4 class="text-center"><a href="${contextPath}/logout">Вихід</a></h4>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
