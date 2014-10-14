<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.09.2014
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bulletin</title>
</head>
<body>
<h1>Votes bulletin</h1>
<table style="border-style: solid">
    <tr>
        <td>Number at list</td>
        <td>Name of the political party</td>
        <td>Short description</td>
        <td>Your vote</td>
    </tr>
    <c:forEach var="party" items="${billetin}">
        <tr>
            <td><a href="/bulletin/${party.id}">${party.id}</a></td>
            <td>${party.partyName}<br>${party.partyDescr}</td>
            <td><input type="checkbox" name="a" value="${party.partyName}"></p></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="add-party">Add new party</a>
</body>
</html>
