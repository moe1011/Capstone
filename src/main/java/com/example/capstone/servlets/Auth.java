package com.example.capstone.servlets;

import com.example.capstone.beans.Admin;
import com.example.capstone.dao.ApplicationDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

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

            //filled bean with information from Post request
            Admin user = new Admin();
            user.setFullName(request.getParameter("fullName"));
            user.setEmail(request.getParameter("email"));
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));

            //Verification that re-entered password matches password
            String passwordVerification = request.getParameter("passwordConfirm");

            //Feedback message to user after submit.
            String message = "";

            //called the dao method to create user, only if the two password fields match
            if (user.getPassword().equals(passwordVerification)) {
                ApplicationDao dao = new ApplicationDao();
                //verification if username already present in the database.
                if (dao.verifyUserExists(user.getUsername())) {
                    message = "Username already exists. Please enter a different username.";
                }
                else {
                    dao.createUser(user);
                    message = "User created successfully.";
                }
            }
            else {
                message = "Please enter the same password in both fields.";
            }

            //sending feedback message as attribute to JSP page.
            request.setAttribute("feedbackMessage",message);

            doGet(request, response);
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
            Admin user = new Admin();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));

            //call Dao to verify if user in DB
            ApplicationDao dao = new ApplicationDao();
            boolean isValidUser = dao.validateUser(user.getUsername(), user.getPassword());
            user.setLoggedIn(isValidUser);

            //check if user is valid
            if (user.isLoggedIn()){
                //Set up HTTP session object
                HttpSession session = request.getSession();

                //Set username as an attribute in the session
                session.setAttribute("username", user.getUsername());

                //Check in DB if email is validated
                boolean isEmailVerified = dao.checkEmailVerified(user.getUsername());
                user.setVerifiedEmail(isEmailVerified);

                //If email is not verified, send verification email
                if (!isEmailVerified) {
                    String token = String.valueOf(UUID.randomUUID());
                    sendVerificationEmail(user, token);

                    //add generated token to database
                    dao.addEmailVerificationToken(user.getUsername(), token);

                    //forward to email verification
                    request.getRequestDispatcher("/emailverification.jsp").forward(request, response);
                }

                request.getRequestDispatcher("/home.jsp").forward(request, response);
            }
            else {
                String errorMessage = "Invalid username and password combination";
                request.setAttribute("error", errorMessage);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }

        private void sendVerificationEmail(Admin user, String token) {
            //send email
            System.out.println("Hello, " + user.getUsername() + "\n\n" +
                    "You are receiving this email because your account is not verified. \n" +
                    "Please click the following link: \n" +
                    getServletContext().getContextPath() + "/emailverified?username=" + user.getUsername() +
                    "&token=" + token + "\n\n" +
                    "Alternatively, you can copy and paste the link to your browser.");
        }
    }

    @WebServlet(name = "logout", value = "/logout")
    public static class Logout extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //Obtain the session object (if current is existing)
            HttpSession session = request.getSession();

            //Close the session by invalidate
            session.invalidate();

            //Redirect to login page with a thank you message
            request.setAttribute("error","Thank you for using the application.");
            request.getRequestDispatcher("/login.jsp").include(request, response);

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }
    }

    @WebServlet(name = "emailverified", value = "/emailverified")
    public static class emailverified extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String token = request.getParameter("token");
            String username = request.getParameter("username");

            //Obtain token from database
            ApplicationDao dao = new ApplicationDao();
            String tokenFromDatabase = dao.getEmailVerificationToken(username);

            if (token.equals(tokenFromDatabase)) {
                //call db to mark email as verified for this user.
                dao.markEmailVerified(username);
                System.out.println("Token verified!");

                request.setAttribute("emailValidatedMessage", "Your email has been verified.");
            } else {
                request.setAttribute("emailValidatedMessage", "Invalid token. Your e-mail will be verified the next time you log in to your account.");
            }
            request.getRequestDispatcher("/emailverified.jsp").forward(request,response);

        }
    }

    @WebServlet(name = "passwordRecovery", value = "/passwordRecovery")
    public static class passwordRecovery extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "Password recovery process" + "</h1>");
            out.println("<p>" + "Site under construction. Please come back later." + "</p>");
            out.println("</body></html>");

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }
    }
} // End of Auth class