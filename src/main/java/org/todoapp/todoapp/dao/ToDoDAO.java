package org.todoapp.todoapp.dao;

import org.todoapp.todoapp.beans.ToDoBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToDoDAO {
    public static ArrayList<ToDoBean> GetNotesFromDB(DataSource dataSource, String username) throws SQLException {

        ArrayList<ToDoBean> notes = new ArrayList<>();

        // Get the connection from the DataSource
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM todo WHERE created_by = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                // Execute the query that returns a ResultSet (a list of notes), and add them to the ArrayList
                // that will contain ToDoBean instances
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String content = resultSet.getString("content");
                        String isComplete = resultSet.getString("iscomplete");
                        int id = resultSet.getInt("id");
                        notes.add(new ToDoBean(id, content, username, isComplete));
                        // We could use the setter methods to set the values of the ToDoBean instance
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notes;
    }

    public static int AddNoteToDB(DataSource dataSource, String noteInput, String username, String isComplete) {
        String query = "INSERT INTO todo(id,content,created_by,iscomplete) VALUES(null,?,?,?)";

        // Getting the connection from the DataSource
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, noteInput);
            ps.setString(2, username);
            ps.setString(3, isComplete);

            // Execute the query, if success, return 1, else 0
            int count = ps.executeUpdate();
            return count;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}


