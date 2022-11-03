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
    <div style="display: flex; justify-content: center;">
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

<%
    // Retrieves the servlet which caused this jsp to be called
    String servletPath = request.getServletPath();
    // The view stores servlet
    if(servletPath.equals("/stores")){
%>
<%--View Stores--%>
<%--Store Dropdown--%>
<div style="display: flex; justify-content: center;"><h1>VIEW</h1></div>
<div style="display: flex; justify-content: center; margin-top: 10px">


    <form id="form1" method="get" action="stores" onsubmit="return false">
        <%
            //            int selectedIndex = request.getAttribute("selectedStoreIndex") != null ?
//                    (int) request.getAttribute("selectedStoreIndex") : -1;
        %>
        <select onchange="document.getElementById('form1').submit();" name="selectedStore" id="selectedStore">
            <option disabled selected>Select a store</option>
            <%
                ArrayList<Store> stores = (ArrayList) request.getAttribute("stores");

                for (int i = 0; i < stores.size(); i++) {
            %>
            <option value="<%=i%>"><%=stores.get(i).getStoreName()%></option>
            <%
                }
            %>
        </select>
    </form>
</div>

<%--Store Information--%>
<div style="display: flex; justify-content: center; margin-top: 20px">
    <%
        Store selectedStore = (Store) request.getAttribute("selectedStore");
        if(selectedStore != null){
    %>
    <p style="margin-right: 10px"><%=selectedStore.getStoreId()%></p>
    <p style="margin-right: 10px"><%=selectedStore.getStoreName()%></p>
    <p style="margin-right: 10px"><%=selectedStore.getStoreAddress()%></p>
    <p><%=selectedStore.getGamesList()%></p>
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
<div style="display: flex; justify-content: center;"><h1>ADD</h1></div>

<%
    }
    // Edit stores servlet
    else if(servletPath.equals("/editstores")){
%>
<%--Edit Stores--%>
<div style="display: flex; justify-content: center;"><h1>EDIT</h1></div>

<%
}
    // Remove stores servlet
    else if(servletPath.equals("/removestores")){
%>
<%--Remove Stores--%>
<div style="display: flex; justify-content: center;"><h1>REMOVE</h1></div>

<%
}
%>


</body>
</html>
