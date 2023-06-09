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

    <title>Вхід</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
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
    <form method="POST" action="${contextPath}/login" class="form-custom">
        <h2 class="form-heading" style="text-align: center;">Вхід</h2>
        <div class="form-group">
            <input name="email" type="text" class="form-control" placeholder="Електронна пошта"
                   autofocus="true" value="${email}"/>
            <input name="password" type="password" class="form-control" placeholder="Пароль"/>
            <c:if test="${not empty error}">
                <p style="color: red"><%= request.getAttribute("error") %>
                </p>
            </c:if>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Увійти</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Реєстрація</a></h4>
        </div>
    </form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>