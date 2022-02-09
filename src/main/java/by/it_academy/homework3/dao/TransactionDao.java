package by.it_academy.homework3.dao;

import by.it_academy.homework3.constant.SQLConstant;
import by.it_academy.homework3.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDao {

    private PreparedStatement preparedStatement;

    public boolean createTransactionDB(int accountId, double amount) throws SQLException {
        try {
            Connection connection = DataBaseConnection.connect();
            preparedStatement = connection.prepareStatement(SQLConstant.CREATE_TRANSACTION_SQL);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setDouble(2, amount);
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
}
