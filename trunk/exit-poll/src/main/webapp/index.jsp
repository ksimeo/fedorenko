<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Payments</title>
</head>
<body>
<h1>Избирательный список</h1>
<table style="border-style: solid">
    <tr>
        <td>№</td>
        <td>Название партии</td>
        <td>Краткое описание</td>
        <td></td>
    </tr>
    <c:forEach var="payment" items="${payments}">
        <tr>
            <td><a href="/payments/${payment.id}">${payment.id}</a></td>
            <td>${payment.amount}</td>
            <td>${payment.payerId}</td>
            <td>${payment.recipientId}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="add-payment">New payment</a>
</body>
</html>