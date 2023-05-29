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


    <title>Клієнти</title>

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

    <form method="POST" action="${contextPath}/admin/clients/delete" class="table-custom">
        <h2 class="form-heading" style="text-align: center;">Клієнти</h2>
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
                    <th>Дата народження</th>
                    <th>Група крові</th>
                    <th>Резус фактор</th>
                    <th>Алергія</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="client" items="${clients}">
                    <tr>
                        <td>
                            <input name="firstName${client.id}" type="text" class="form-control"
                                   value="${client.firstName}"/>
                        </td>
                        <td>
                            <input name="lastName${client.id}" type="text" class="form-control"
                                   value="${client.lastName}"/>
                        </td>
                        <td>
                            <input name="email${client.id}" type="text" class="form-control"
                                   value="${client.email}"/>
                        </td>
                        <td>
                            <input name="phoneNumber${client.id}" type="text" class="form-control"
                                   value="${client.phoneNumber}"/>
                        </td>
                        <td>
                            <input name="birthDate${client.id}" type="date" class="form-control"
                                   value="${client.dateOfBirth}"/>
                        </td>
                        <td>
                            <select name="bloodGroup${client.id}" class="form-control">
                                <option value=""></option>
                                <option value="1" ${client.bloodGroup == 1 ? 'selected' : ''}>1</option>
                                <option value="2" ${client.bloodGroup == 2 ? 'selected' : ''}>2</option>
                                <option value="3" ${client.bloodGroup == 3 ? 'selected' : ''}>3</option>
                                <option value="4" ${client.bloodGroup == 4 ? 'selected' : ''}>4</option>
                            </select>
                        </td>
                        <td>
                            <select name="rh${client.id}" class="form-control">
                                <option value=""></option>
                                <option value="+" ${client.RH == '+' ? 'selected' : ''}>+</option>
                                <option value="-" ${client.RH == '-' ? 'selected' : ''}>-</option>
                            </select>

                        </td>
                        <td>
                            <input name="allergy${client.id}" type="text" class="form-control"
                                   value="${client.allergy}"/>
                        </td>
                        <td>
                            <div class="button-container">
                                <button class="delete-btn" type="submit" name="id" value="${client.id}">Видалити
                                </button>
                                <button class="update-btn" type="submit" name="clientId" value="${client.id}"
                                        formaction="${contextPath}/admin/clients/update">Оновити
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
