package org.todoapp.todoapp.servlets;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.todoapp.todoapp.beans.ToDoBean;
import org.todoapp.todoapp.dao.ToDoDAO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GetNotesServlet", value = "/getnotes")
public class GetNotesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Create an ArrayList to store the notes of type ToDoBean
        ArrayList<ToDoBean> notes;

        // Get username from session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Get the DataSource from the ServletContext in order to get the connection
        DataSource dataSource = (DataSource) request.getServletContext().getAttribute("dbConnection");

        try {
            // Get notes using the DAO and affect them to the "notes" varialbe
            notes = ToDoDAO.GetNotesFromDB(dataSource, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Set the notes in the session and redirect to home
        session.setAttribute("notes", notes);
        response.sendRedirect("home.jsp");
    }
}
