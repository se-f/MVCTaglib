package org.todoapp.todoapp.dao;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public static boolean isValidUser(DataSource dataSource, String username, String password) {

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If there is a matching user in the database
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
