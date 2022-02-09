package by.it_academy.homework3.constant;

public class SQLConstant {
    public static final String CREATE_USER_SQL = "INSERT INTO users (userId, name, address) VALUES (?,?,?)";
    public static final String READ_ALL_USERS_SQL = "SELECT * FROM users";

    public static final String CREATE_ACCOUNT_SQL = "INSERT INTO accounts (userId, balance, currency) VALUES (?, 0, ?)";
    public static final String READ_ACCOUNT_SQL = "SELECT * FROM accounts WHERE userId = ? AND currency = ?";
    public static final String UPDATE_ACCOUNT_SQL = "UPDATE accounts SET balance = ? WHERE userId = ? AND currency = ?;";

    public static final String CREATE_TRANSACTION_SQL = "INSERT INTO transactions (accountId, amount) VALUES (?,?)";
}
