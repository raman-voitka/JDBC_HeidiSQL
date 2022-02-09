package by.it_academy.homework3.model;

public class Account {
    private int accountId;
    private int userId;
    private double balance;
    private String currency;

    public Account() {
    }

    public Account(int accountId, int userId, double balance, String currency) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public int getUserId() {
        return userId;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Account: " +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '.';
    }
}