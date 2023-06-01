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


    <title>Запис на прийом</title>

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

    <form method="POST" action="${contextPath}/client/appointment/create" class="form-custom">
        <h2 class="form-heading" style="text-align: center;">Запис на прийом</h2>
        <div class="form-group">
            <select id="doctor" name="doctorId" class="form-control">
                <c:forEach var="doctor" items="${doctors}">
                    <OPTION value="${doctor.id}">${doctor.lastName} ${doctor.firstName}
                        - ${doctor.specialization}</OPTION>
                </c:forEach>
            </select>
            <input name="dateTime" type="datetime-local" class="form-control">
            <br>
            <textarea name="summary" type="text" class="form-control" placeholder="Опис скарги"></textarea>
            <h4 class="custom-link"><a href="${contextPath}/client/appointments">Існуючі записи</a></h4>
            <c:if test="${not empty error}">
                <p style="color: red"><%= request.getAttribute("error") %>
                </p>
            </c:if>
            <c:if test="${not empty success}">
                <p style="color: limegreen"><%= request.getAttribute("success") %>
                </p>
            </c:if>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Створити</button>
            <h4 class="text-center"><a href="${contextPath}/logout">Вихід</a></h4>
        </div>
    </form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
