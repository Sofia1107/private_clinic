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


    <title>Реєстрація лікаря</title>

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

    <form method="POST" action="${contextPath}/admin/doctors/create" class="form-custom">
        <h2 class="form-heading" style="text-align: center;">Реєстрація лікаря</h2>
        <div class="form-group">
            <input name="firstName" type="text" class="form-control" placeholder="Ім'я"
                   value="${firstName}"/>
            <input name="lastName" type="text" class="form-control" placeholder="Прізвище"
                   value="${lastName}"/>
            <input name="email" type="text" class="form-control" placeholder="Електронна пошта"
                   value="${email}"/>
            <input name="phoneNumber" type="text" class="form-control" placeholder="Номер телефону"
                   value="${phoneNumber}"/>
            <input name="specialization" type="text" class="form-control" placeholder="Спеціальність"
                   value="${specialization}"/>
            <h4 class="custom-link"><a href="${contextPath}/admin/doctors">Список лікарів</a></h4>
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
