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


    <title>Сторінка адміністратора</title>

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
    <form id="doctors-form" class="form-custom">
        <div class="form-group">
            <button id="doctors-button" class="btn btn-lg btn-primary btn-block" type="button">Інформація про лікарів
            </button>
        </div>
    </form>
    <script>
        document.getElementById("doctors-button").addEventListener("click", function () {
            var form = document.getElementById("doctors-form");
            form.method = "GET";
            form.action = "${contextPath}/admin/doctors";
            form.submit();
        });
    </script>
    <form id="clients-form" class="form-custom">
        <div class="form-group">
            <button id="clients-button" class="btn btn-lg btn-primary btn-block" type="button">Інформація про клієнтів
            </button>
        </div>
    </form>
    <script>
        document.getElementById("clients-button").addEventListener("click", function () {
            var form = document.getElementById("clients-form");
            form.method = "GET";
            form.action = "${contextPath}/admin/clients";
            form.submit();
        });
    </script>
    <form id="appointments-form" class="form-custom">
        <div class="form-group">
            <button id="appointments-button" class="btn btn-lg btn-primary btn-block" type="button">Інформація про
                записи на прийом
            </button>
        </div>
    </form>
    <script>
        document.getElementById("appointments-button").addEventListener("click", function () {
            var form = document.getElementById("appointments-form");
            form.method = "GET";
            form.action = "${contextPath}/admin/appointments";
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
