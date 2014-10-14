<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.09.2014
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new political party in bulletin</title>
</head>
<body>
<h1>New party</h1>
<f:form method="POST" commandName="party" action="bulletin">
    <table>
        <tr>
            <td>
                <i>Official name of party:</i>
            </td>
            <td>
                <f:input path="partyName"/>
            </td>
        </tr>
        <tr>
            <td>
                <i>Short description of the party:</i>
            </td>
            <td>
                <f:input path="partyDescr"/>
            </td>
        </tr>
        <tr>
            <td><input type="submit"/></td>
        </tr>
    </table>
</f:form>
</body>
</html>
