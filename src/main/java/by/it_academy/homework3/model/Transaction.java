package by.it_academy.homework3.model;

public class Transaction {
    private int transactionId;
    private int accountId;
    private int amount;

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction: " +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", amount=" + amount +
                '.';
    }
}