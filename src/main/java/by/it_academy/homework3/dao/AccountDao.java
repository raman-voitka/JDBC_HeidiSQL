package by.it_academy.homework3.dao;

import by.it_academy.homework3.constant.SQLConstant;
import by.it_academy.homework3.model.Account;
import by.it_academy.homework3.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean createAccountDB(int userId, String currency) throws SQLException {
        try {
            Connection connection = DataBaseConnection.connect();
            preparedStatement = connection.prepareStatement(SQLConstant.CREATE_ACCOUNT_SQL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, currency);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            closePreparedStatement();
            DataBaseConnection.closeConnection();
        }
    }

    public Account readAccountDB(int userID, String currency) throws SQLException {
        try {
            Connection connection = DataBaseConnection.connect();
            preparedStatement = connection.prepareStatement(SQLConstant.READ_ACCOUNT_SQL);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, currency);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int accountIdDB = resultSet.getInt("accountId");
                int userIdDB = resultSet.getInt("userId");
                double balanceDB = resultSet.getDouble("balance");
                String currencyDB = resultSet.getString("currency");
                return new Account(accountIdDB, userIdDB, balanceDB, currencyDB);
            }
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            closePreparedStatement();
            closeResultSet();
            DataBaseConnection.closeConnection();
        }
        return null;
    }

    public boolean updateAccountDB(double numberForRefill, int userID, String currencyForRefill) throws SQLException {
        try {
            Connection connection = DataBaseConnection.connect();
            preparedStatement = connection.prepareStatement(SQLConstant.UPDATE_ACCOUNT_SQL);
            preparedStatement.setDouble(1, numberForRefill);
            preparedStatement.setInt(2, userID);
            preparedStatement.setString(3, currencyForRefill);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            closePreparedStatement();
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