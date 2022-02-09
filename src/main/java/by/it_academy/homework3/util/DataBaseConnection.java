package by.it_academy.homework3.util;

import by.it_academy.homework3.db_properties.DataBaseProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static Connection connection = null;

    public static Connection connect() throws SQLException {
        try {
            Class.forName(DataBaseProperty.getDriver());
            if (connection == null) {
                connection = DriverManager.getConnection(DataBaseProperty.getURL());
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("\"Connection Failed\", e");
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new SQLException();
            }
        }
    }
}
