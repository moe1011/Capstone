package com.example.capstone.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "home", value = "/home")
public class Application extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/home.jsp").include(request, response);
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + "Home Page" + "</h1>");
//        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/home.jsp").include(request, response);
    }



}
