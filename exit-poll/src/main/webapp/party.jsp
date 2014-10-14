<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.09.2014
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Political party</title>
</head>
<body>
<h1>Party number ${party.id}</h1>
<table>
    <tr>
        <td><i>Name: </i></td>
        <td>${party.partyName}</td>
    </tr>
    <tr>
        <td><i>Short description of party: </i></td>
        <td>${party.partyDescr}</td>
    </tr>
</table>
<a href="/bulletin">Back</a>
</body>
</html>