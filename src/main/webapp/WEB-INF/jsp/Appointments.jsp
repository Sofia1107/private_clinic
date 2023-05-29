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

<div class="container">

    <form method="POST" action="${contextPath}/client/appointment/delete" class="table-custom">
        <div class="form-heading-container">
            <h2 class="form-heading">Наявні записи</h2>
            <h4 class="custom-link"><a href="#" onclick="submitForm()">Записатись на прийом</a></h4>
            <script>
                function submitForm() {
                    var form = document.createElement('form');
                    form.method = 'POST';
                    form.action = '${contextPath}/client/appointment/create';

                    document.body.appendChild(form);
                    form.submit();
                }
            </script>
        </div>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>Лікар</th>
                    <th>Дата запису</th>
                    <th>Опис скарги</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <td>${appointment.doctorLastname} ${appointment.doctorFirstName}</td>
                        <td>
                            <script>
                                var dateTimeString = "${appointment.dateTime}";

                                // Створити об'єкт дати
                                var dateTime = new Date(dateTimeString);

                                // Функція для додавання нуля перед числами меншими за 10
                                function padZero(number) {
                                    return number < 10 ? "0" + number : number;
                                }

                                // Отримати значення різних компонентів дати
                                var year = dateTime.getFullYear();
                                var month = padZero(dateTime.getMonth() + 1); // Місяці в JavaScript починаються з 0
                                var day = padZero(dateTime.getDate());
                                var hours = padZero(dateTime.getHours());
                                var minutes = padZero(dateTime.getMinutes());

                                // Сформувати рядок у вказаному форматі
                                var formattedDateTime = year + "-" + month + "-" + day + " " + hours + ":" + minutes;

                                // Вивести сформатовану дату у HTML
                                document.write(formattedDateTime);
                            </script>
                        </td>
                        <td>${appointment.summary}</td>
                        <td>
                            <button class="delete-btn" type="submit" name="id" value="${appointment.id}">Скасувати
                            </button>
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