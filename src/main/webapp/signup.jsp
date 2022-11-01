<%--
  Created by IntelliJ IDEA.
  User: moe
  Date: 2022-11-01
  Time: 5:17 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
</head>
<body>
<div style="text-align: center">
    <h1>Signup</h1>
    <br/>
    <form action="${pageContext.request.contextPath}/home" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="text" id="password" name="password"><br><br>
        <input type="submit" value="Signup">
    </form>
</div>
</body>
</html>
