<%--
  Created by IntelliJ IDEA.
  User: moe
  Date: 2022-11-01
  Time: 5:29 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="text-align: center">
    <h1>Login</h1>
    <br/>
    <p><% if (request.getAttribute("error") != null) { %>
    <%= request.getAttribute("error") %>
        <% } %></p>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Login">
    <br/>
        <p>New user? Sign-up <a href="${pageContext.request.contextPath}/signup">here.</a></p>
        <p>Forgot your password? Click <a href="${pageContext.request.contextPath}/passwordRecovery">here.</a></p>
    </form>
</div>
</body>
</html>
