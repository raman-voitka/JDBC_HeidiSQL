package by.it_academy.homework3.dao;

import by.it_academy.homework3.constant.SQLConstant;
import by.it_academy.homework3.model.User;
import by.it_academy.homework3.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean createUserDB(int randomId, String name, String address) throws SQLException {
        try {
            Connection connection = DataBaseConnection.connect();
            preparedStatement = connection.prepareStatement(SQLConstant.CREATE_USER_SQL);
            preparedStatement.setInt(1, randomId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            closePreparedStatement();
            DataBaseConnection.closeConnection();
        }
    }

    public List<User> readAllUsersDB() throws SQLException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = DataBaseConnection.connect();
            preparedStatement = connection.prepareStatement(SQLConstant.READ_ALL_USERS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                users.add(new User(userId, name, address));
            }
            return users;
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            closePreparedStatement();
            closeResultSet();
            DataBaseConnection.closeConnection();
        }
    }

    private void closePreparedStatement() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResultSet() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}