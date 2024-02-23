package org.todoapp.todoapp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.todoapp.todoapp.dao.LoginDAO;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/loginservice")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get the DataSource from the ServletContext in order to get the connection
        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");

        // Verify if the user is valid using the DAO
        if (LoginDAO.isValidUser(dataSource, username, password)) {

            // Set username in the session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirect to the servlet "getnotes" to fetch the user notes
            response.sendRedirect("getnotes");

        } else {
            PrintWriter out = response.getWriter();
            out.println("<html><body><p>Login failed. Invalid username or password.</p></body></html>");
        }
    }


}
