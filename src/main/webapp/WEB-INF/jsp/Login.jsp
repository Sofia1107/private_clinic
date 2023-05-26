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

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

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