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


    <title>Записи до лікарів</title>

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

    <form method="POST" action="${contextPath}/admin/appointments/delete" class="table-custom">
        <h2 class="form-heading" style="text-align: center;">Наявні записи</h2>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>Клієнт</th>
                    <th>Лікар</th>
                    <th>Дата запису</th>
                    <th>Опис скарги</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <td>${appointment.clientLastname} ${appointment.clientFirstName}</td>
                        <td>${appointment.doctorLastname} ${appointment.doctorFirstName}</td>
                        <td>
                            <input name="dateTime" type="datetime-local" class="form-control"
                                   value="${appointment.dateTime}">
                        </td>
                        <td>
                            <textarea name="summary" type="text" class="form-control"
                                      placeholder="Опис скарги">${appointment.summary}</textarea>
                        </td>
                        <td>
                            <div class="button-container">
                                <button class="delete-btn" type="submit" name="id" value="${appointment.id}">Видалити
                                </button>
                                <input type="hidden" name="appointmentId" value="${appointment.id}">
                                <button class="update-btn" type="submit" name="id"
                                        formaction="${contextPath}/admin/appointments/update">Оновити
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <h4 class="text-center"><a href="${contextPath}/logout">Вихід</a></h4>
    </form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
