<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Password Reset</title>
</head>
<body>
<div style="text-align: center">
    <h1>Password Reset</h1>
    <p>Please enter the new password for your account.</p>
    <br/>
    <p><% if (request.getAttribute("passwordResetMessage") != null) { %>
    <%= request.getAttribute("passwordResetMessage") %>
        <% } %></p>
    <form action="${pageContext.request.contextPath}/passwordReset" method="post">
        <table style="margin-left: auto;margin-right: auto">
        <tr>
            <td><label for="email">Email:</label></td>
            <td><input type="text" id="email" name="email" value="<%= request.getAttribute("email")%>" readonly></td>
        </tr>
        <tr>
            <td><label for="password">Password:</label></td>
            <td><input type="password" id="password" name="password" maxlength="20"></td>
        </tr>
        </table>
        <br><br>
        <input type="submit" value="Submit"><br>
    </form>
        <p>Return to login screen <a href="${pageContext.request.contextPath}/login">here.</a></p>

</div>
</body>
</html>
