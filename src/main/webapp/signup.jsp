<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
        <table style="margin-left: auto;margin-right: auto">
            <tr>
                <td><label for="fullName">Full Name:</label></td>
                <td><input type="text" id="fullName" name="fullName" maxlength="60" required></td>
            </tr>
            <tr>
                <td><label for="email">E-mail:</label></td>
                <td><input type="text" id="email" name="email" maxlength="45" required></td>
            </tr>
            <tr>
                <td><label for="username">Username:</label></td>
                <td><input type="text" id="username" name="username" maxlength="20" required></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" id="password" name="password" maxlength="20" required></td>
            </tr>
            <tr>
                <td><label for="passwordConfirm">Re-enter Password:</label></td>
                <td><input type="password" id="passwordConfirm" name="passwordConfirm" maxlength="20" required></td>
            </tr>
        </table>
        <input type="submit" value="Sign Up">

    </form>
    <p><a href="${pageContext.request.contextPath}/index.jsp">Back to main page.</a></p>
</div>
</body>
</html>
