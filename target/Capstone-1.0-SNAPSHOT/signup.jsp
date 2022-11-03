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
    <h1>Sign-up</h1>
    <p><% if (request.getAttribute("feedbackMessage") != null) { %>
       <%=request.getAttribute("feedbackMessage")%>
       <% } %> </p>
    <form action="${pageContext.request.contextPath}/signup" method="post">
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" maxlength="60" required><br><br>
        <label for="email">E-mail:</label>
        <input type="text" id="email" name="email" maxlength="45" required><br><br>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" maxlength="20" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" maxlength="20" required><br><br>
        <label for="passwordConfirm">Re-enter Password:</label>
        <input type="password" id="passwordConfirm" name="passwordConfirm" maxlength="20" required><br><br>
        <input type="submit" value="signup">
    </form>
    <p><a href="${pageContext.request.contextPath}/index.jsp">Back to main page.</a></p>
</div>
</body>
</html>
