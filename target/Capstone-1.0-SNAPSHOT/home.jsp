<%--
  Created by IntelliJ IDEA.
  User: moe
  Date: 2022-11-01
  Time: 5:35 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="styles.css">
    <title>Home</title>
</head>

<body>
<div>
    <h1 style="text-align: center">Home</h1>
    <br/>
    <div style="justify-content: center; display: flex;">
        <a href="home" class="Nav"><button class="btn">Home</button></a>
        <div class="dropdown">
            <button class="btn Nav">Stores</button>
            <div class="dropdown-content">
                <a href="stores">View Stores</a>
                <a href="editstores">Edit Stores</a>
                <a href="addstores">Add Stores</a>
                <a href="removestores">Remove Stores</a>
            </div>
        </div>

        <button class="btn Nav">Company Logo / Current Date</button>
    </div>

</div>


</body>
</html>