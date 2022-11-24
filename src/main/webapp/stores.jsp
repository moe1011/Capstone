<%@ page import="com.example.capstone.beans.Store" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: moe
  Date: 2022-11-02
  Time: 9:12 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="styles.css">
    <title>Stores</title>
</head>
<body>
<%--Nav--%>
<div>
    <h1 style="text-align: center">Store</h1>
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
        <br/>
        <div class="dropdown">
            <button class="btn Nav">Games</button>
            <div class="dropdown-content">
                <a href="addgames">Add Games</a>
                <a href="removegames">Remove Games</a>
            </div>
        </div>
        <br/>

        <div style="justify-content: center; display: flex;">
            <button class="btn Nav">Company Logo / Current Date</button>
        </div>
        <div style="justify-content: center; display: flex;">
            <form action="${pageContext.request.contextPath}/logout">
                <input type="submit" class="btn Nav" value="Logout">
            </form>
        </div>
    </div>

</div>

<%
    ArrayList<Store> stores;
    Store selectedStore;
    // Retrieves the servlet which caused this jsp to be called
    String servletPath = request.getServletPath();
    // The view stores servlet
    if(servletPath.equals("/stores")){
%>
<%--View Stores--%>
<%--Store Dropdown--%>
<div style="display: flex; justify-content: center;"><h1>VIEW</h1></div>
<div style="display: flex; justify-content: center; margin-top: 10px">

    <form id="viewForm" method="get" action="stores" onsubmit="return false">
        <%
            //            int selectedIndex = request.getAttribute("selectedStoreIndex") != null ?
//                    (int) request.getAttribute("selectedStoreIndex") : -1;
        %>
        <select onchange="document.getElementById('viewForm').submit();" name="selectedStore" id="selectedStore">
            <option disabled selected>Select a store</option>
            <%
                stores = (ArrayList) request.getAttribute("stores");

                for (int i = 0; i < stores.size(); i++) {
            %>
            <option value="<%=i%>"><%= stores.get(i).getStoreId()+" | "+ stores.get(i).getStoreName()%></option>
            <%
                }
            %>
        </select>
    </form>
</div>

<%--Store Information--%>
<div style="display: flex; justify-content: center; margin-top: 20px">
    <%
        selectedStore = (Store) request.getAttribute("selectedStore");
        if(selectedStore != null){
    %>
    <h4 style="margin-right: 10px"><%=selectedStore.getStoreId()%> | <%=selectedStore.getStoreName()%>
        | <%=selectedStore.getStoreAddress()%> | <%=!selectedStore.getGamesList().isEmpty() ? selectedStore.getGamesList()
                : "[No Games in the Store]"%></h4>
    <%
        }
    %>

</div>
<%
    }
    // Add stores servlet
    else if(servletPath.equals("/addstores")){
%>
<%--Add Stores--%>
<div style="display: flex; justify-content: center;"><h1>ADD STORE</h1></div>
<div style="display: flex; justify-content: center;">
<%--    <% String hidden = "hidden";%>--%>
<form action="">
    <label for="storeName">Store Name:</label><br>
    <input type="text" id="storeName" name="storeName" placeholder="My Arcade" required><br>
    <label for="storeAddress">Store Address:</label><br>
    <input type="text" id="storeAddress" name="storeAddress" placeholder="123 Street" required><br>
    <input style="margin-top: 10px" type="submit" value="Submit">
<%--    <p <%=hidden%>>Store Added!</p>--%>
</form>
</div>
<%
    }
    // Edit stores servlet
    else if(servletPath.equals("/editstores")){
%>
<%--Edit Stores--%>
<div style="display: flex; justify-content: center;"><h1>EDIT STORE</h1></div>
<div style="display: flex; justify-content: center; margin-top: 10px">

    <form id="editForm" method="get" action="editstores" onsubmit="return false">

        <select onchange="document.getElementById('editForm').submit();" name="selectedStore">
            <option disabled selected>Select a store</option>
            <%
                stores = (ArrayList) request.getAttribute("stores");

                for (int i = 0; i < stores.size(); i++) {
            %>
            <option value="<%=i%>"><%= stores.get(i).getStoreId()+" | "+ stores.get(i).getStoreName()%></option>
            <%
                }
            %>
        </select>
    </form>
</div>
<div style="display: flex; justify-content: center;">
    <%
        selectedStore = (Store) request.getAttribute("selectedStore");
        if(selectedStore != null){
    %>
    <form action="editstores">
        <label for="storeName">Store Name:</label><br>
        <input type="text" name="storeName" value="<%=selectedStore.getStoreName()%>" required><br><br>
        <label for="storeAddress">Store Address:</label><br>
        <input type="text" name="storeAddress" value="<%=selectedStore.getStoreAddress()%>" required><br><br>
        <input value="<%=selectedStore.getStoreId()%>" hidden name="id">
        <input type="submit" value="Edit">
    </form>
    <%
        }
    %>
</div>

<%
}
    // Remove stores servlet
    else if(servletPath.equals("/removestores")){
%>
<%--Remove Stores--%>
<div style="display: flex; justify-content: center;"><h1>REMOVE STORE</h1></div>
<div style="display: flex; justify-content: center; margin-top: 10px">


    <form id="removeForm" method="get" action="removestores" onsubmit="return false">

        <select onchange="document.getElementById('removeForm').submit();" name="selectedStore">
            <option disabled selected>Select a store</option>
            <%
                stores = (ArrayList) request.getAttribute("stores");

                for (int i = 0; i < stores.size(); i++) {
            %>
            <option value="<%=i%>"><%= stores.get(i).getStoreId()+" | "+ stores.get(i).getStoreName()%></option>
            <%
                }
            %>
        </select>
    </form>
</div>
<div>
    <%
        selectedStore = (Store) request.getAttribute("selectedStore");
        if(selectedStore != null){
    %>
    <form action="removestores" style="display: flex; justify-content: center; margin-top: 20px">
        <h4 style="margin-right: 10px"><%=selectedStore.getStoreId()%> | <%=selectedStore.getStoreName()%>
            | <%=selectedStore.getStoreAddress()%> | <%=!selectedStore.getGamesList().isEmpty() ? selectedStore.getGamesList()
                    : "[No Games in the Store]"%></h4>

        <button name="removeStore" type="submit" value="Remove">Remove</button>
    </form>

    <%
        }
    %>
</div>

<%
}
    // Add Games Servlet
    else if(servletPath.equals("/addgames")){
%>
<%--Add Games--%>
<div style="display: flex; justify-content: center;"><h1>ADD GAME</h1></div>
<div style="display: flex; justify-content: center; margin-top: 10px">

    <form id="addGameForm" method="get" action="addgames" onsubmit="return false">

        <select onchange="document.getElementById('addGameForm').submit();" name="selectedStore">
            <option disabled selected>Select a store</option>
            <%
                stores = (ArrayList) request.getAttribute("stores");

                for (int i = 0; i < stores.size(); i++) {
            %>
            <option value="<%=i%>"><%= stores.get(i).getStoreId()+" | "+ stores.get(i).getStoreName()%></option>
            <%
                }
            %>
        </select>
    </form>
</div>
<div style="display: flex; justify-content: center; flex-direction: column; align-items: center">
    <%
        selectedStore = (Store) request.getAttribute("selectedStore");
        if(selectedStore != null){
    %>
    <h4><%=selectedStore.getStoreId()%> | <%=selectedStore.getStoreName()%> | <%=selectedStore.getStoreAddress()%></h4>
    <br>

    <form action="addgames">
        <label for="gameName">Game Name:</label><br>
        <input type="text" name="gameName" required><br><br>

<%--        <input value="<%=selectedStore.getStoreId()%>" hidden name="id">--%>
        <input type="submit" value="Add">
    </form>
    <%
        }
    %>
</div>

<%
}
// Remove Games Servlet
else if(servletPath.equals("/removegames")){
%>
<%--Remove Games--%>
<div style="display: flex; justify-content: center;"><h1>REMOVE GAME</h1></div>
<div style="display: flex; justify-content: center; margin-top: 10px">

    <form id="removeGameForm" method="get" action="removegames" onsubmit="return false">

        <select onchange="document.getElementById('removeGameForm').submit();" name="selectedStore">
            <option disabled selected>Select a store</option>
            <%
                stores = (ArrayList) request.getAttribute("stores");

                for (int i = 0; i < stores.size(); i++) {
            %>
            <option value="<%=i%>"><%= stores.get(i).getStoreId()+" | "+ stores.get(i).getStoreName()%></option>
            <%
                }
            %>
        </select>
    </form>
</div>
<div style="display: flex; justify-content: center; flex-direction: column; align-items: center">
    <%
        selectedStore = (Store) request.getAttribute("selectedStore");
        if(selectedStore != null){
            if(selectedStore.getGamesList().isEmpty()){
    %>
            <h4>[No Games in the Store]</h4>
    <%
        }
        for (int i = 0; i < selectedStore.getGamesList().size(); i++) {
    %>

    <form action="removegames" style="display: flex; justify-content: center; margin-top: 20px">
        <h4><%=selectedStore.getGamesList().get(i)%></h4>
        <button name="removeGame" type="submit" value="<%=i%>">Remove</button>
    </form>

    <%
            }
        }
    %>

</div>

<%
    }
%>

</body>
</html>
