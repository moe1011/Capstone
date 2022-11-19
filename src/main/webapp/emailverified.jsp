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
    <title>E-mail Verification</title>
</head>
<body>
<div style="text-align: center">
    <h1>E-mail verification</h1>
    <p><% if (request.getAttribute("emailValidatedMessage") != null) { %>
        <%=request.getAttribute("emailValidatedMessage")%>
        <% } %> </p>

    <p>Continue the login by clicking <a href="${pageContext.request.contextPath}/home">here </a></p>
    <p><a href="${pageContext.request.contextPath}/index.jsp">Back to main page.</a></p>
</div>
</body>
</html>
