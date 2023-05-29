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


    <title>Лікарі</title>

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

    <form method="POST" action="${contextPath}/admin/doctors/delete" class="table-custom">
        <div class="form-heading-container">
            <h2 class="form-heading" style="text-align: center;">Лікарі</h2>
            <h4 class="custom-link"><a href="#" onclick="submitForm()">Додати лікаря</a></h4>
            <script>
                function submitForm() {
                    var form = document.createElement('form');
                    form.method = 'GET';
                    form.action = '${contextPath}/admin/doctors/create';

                    document.body.appendChild(form);
                    form.submit();
                }
            </script>
        </div>
        <c:if test="${not empty error}">
            <p style="color: red"><%= request.getAttribute("error") %>
            </p>
        </c:if>
        <c:if test="${not empty success}">
            <p style="color: limegreen"><%= request.getAttribute("success") %>
            </p>
        </c:if>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>Ім'я</th>
                    <th>Прізвище</th>
                    <th>Електронна пошта</th>
                    <th>Номер телефону</th>
                    <th>Спеціальність</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="doctor" items="${doctors}">
                    <tr>
                        <td>
                            <input name="firstName${doctor.id}" type="text" class="form-control"
                                   value="${doctor.firstName}"/>
                        </td>
                        <td>
                            <input name="lastName${doctor.id}" type="text" class="form-control"
                                   value="${doctor.lastName}"/>
                        </td>
                        <td>
                            <input name="email${doctor.id}" type="text" class="form-control"
                                   value="${doctor.email}"/>
                        </td>
                        <td>
                            <input name="phoneNumber${doctor.id}" type="text" class="form-control"
                                   value="${doctor.phoneNumber}"/>
                        </td>
                        <td>
                            <input name="specialization${doctor.id}" type="text" class="form-control"
                                   value="${doctor.specialization}"/>
                        </td>
                        <td>
                            <div class="button-container">
                                <button class="delete-btn" type="submit" name="id" value="${doctor.id}">Видалити
                                </button>
                                <button class="update-btn" type="submit" name="doctorId" value="${doctor.id}"
                                        formaction="${contextPath}/admin/doctors/update">Оновити
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
