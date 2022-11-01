package com.example.capstone.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is where the user Sign up / Log in (and possibly Log out) pages will be made.
 **/

@WebServlet(name = "auth", value = "/auth")
public class Auth extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Authentication" + "</h1>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // Signup Servlet
    @WebServlet(name = "signup", value = "/signup")
    public static class SignUp extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            PrintWriter out = response.getWriter();
            // Loads signup jsp file
            request.getRequestDispatcher("/signup.jsp").include(request, response);


        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }
    }

    // Login Servlet
    @WebServlet(name = "login", value = "/login")
    public static class Login extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Loads signup jsp file
            request.getRequestDispatcher("/login.jsp").include(request, response);

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }
    }

} // End of Auth class

