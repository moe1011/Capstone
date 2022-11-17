<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Password Recovery</title>
</head>
<body>
<div style="text-align: center">
    <h1>Password Recovery</h1>
    <h3>Please enter your e-mail address to reset your password.</h3>
    <br/>
    <p><% if (request.getAttribute("passwordRecoveryMessage") != null) { %>
    <%= request.getAttribute("passwordRecoveryMessage") %>
        <% } %></p>
    <form action="${pageContext.request.contextPath}/passwordRecovery" method="post">
        <label for="email">E-mail:</label>
        <input type="text" id="email" name="email"><br><br>
        <input type="submit" value="Submit"><br>
    </form>
        <p>Return to login screen <a href="${pageContext.request.contextPath}/login">here.</a></p>

</div>
</body>
</html>
