package com.example.capstone.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = {"home", "stores", "addstores", "editstores", "removestores"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //cast the request and response into the Http specific classes
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //Get the session from the request object
        HttpSession session = request.getSession();

        //check if username attribute does not exist (indicate not logged in)
        if (session.getAttribute("username") == null) {
            //redirect to login page if not logged in. Sending error message.
            request.setAttribute("error", "Please login before accessing.");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

        //sending back to servlet. configuration added in POM.xml
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
