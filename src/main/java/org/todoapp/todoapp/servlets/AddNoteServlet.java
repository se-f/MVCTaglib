package org.todoapp.todoapp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.todoapp.todoapp.dao.ToDoDAO;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddNoteServlet", value = "/addnote")
public class AddNoteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        String noteInput = request.getParameter("noteInput");
        String username = (String) session.getAttribute("username");
        String isComplete = "0";

        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");

        // Add note to the database using the DAO
        if (ToDoDAO.AddNoteToDB(dataSource, noteInput, username, isComplete) == 1) {
            // If success, redirect to getnotes
            pw.println("<script>alert('Note ajouté avec succès!'); window.location.href='getnotes';</script>");

        } else {
            // Else, display error popup
            pw.println("<script>alert('Echec d'ajout de note!');</script>");
        }

    }

}