package by.it_academy.homework3.service;

import by.it_academy.homework3.dao.AccountDao;
import by.it_academy.homework3.dao.TransactionDao;
import by.it_academy.homework3.dao.UserDao;
import by.it_academy.homework3.model.Account;
import by.it_academy.homework3.model.User;


import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private String currencyForDB;

    private Account account;
    private AccountDao accountDao;
    private User user;
    private UserDao userDao;
    private TransactionDao transactionDao;

    public Service(Account account, AccountDao accountDao, User user, UserDao userDao, TransactionDao transactionDao) {
        this.account = account;
        this.accountDao = accountDao;
        this.user = user;
        this.userDao = userDao;
        this.transactionDao = transactionDao;
    }

    public boolean isUserExist(String name, int userId) {
        try {
            List<User> userFromDB = userDao.readAllUsersDB()
                    .stream()
                    .filter(n -> n.getName().equals(name) && n.getUserId() == userId)
                    .collect(Collectors.toList());
            if (userFromDB.size() != 0) {
                user = userFromDB.get(0);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("LOGIN ERROR. CHECK DATABASE CONNECTION");
        }
        return false;
    }

    public int createUser(String name, String address) {
        try {
            int randomUserId = getRandomId();
            int userID = (int) userDao.readAllUsersDB()
                    .stream()
                    .filter(n -> n.getUserId() == randomUserId)
                    .count();
            if (userID == 0 && userDao.createUserDB(randomUserId, name, address)) {
                return randomUserId;
            }
        } catch (SQLException e) {
            System.out.println("USER CREATE ERROR. CHECK DATABASE CONNECTION");
        }
        return 0;
    }

    public boolean isAccountCreate(int currencyCode) {
        currencyForDB = getCurrencyAsString(currencyCode);
        try {
            account = accountDao.readAccountDB(user.getUserId(), currencyForDB);
            if (account == null) {
                return accountDao.createAccountDB(user.getUserId(), currencyForDB);
            }
        } catch (SQLException e) {
            System.out.println("ACCOUNT CREATE ERROR. CHECK DATABASE CONNECTION");
        }
        return false;
    }

    public boolean isAccountRefill(int currencyForRefill, double numberForRefill) {
        currencyForDB = getCurrencyAsString(currencyForRefill);
        try {
            account = accountDao.readAccountDB(user.getUserId(), currencyForDB);
            if (account != null && numberForRefill > 0 && numberForRefill <= 100000000
                    && (account.getBalance() + numberForRefill) <= 2000000000) {
                if (accountDao.updateAccountDB(account.getBalance() + roundNumber(numberForRefill),
                        user.getUserId(), currencyForDB)) {
                    if (transactionDao.createTransactionDB(account.getAccountId(), roundNumber(numberForRefill))) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("REFiLL ACCOUNT ERROR. CHECK DATABASE CONNECTION");
        }
        return false;
    }

    public boolean isFundsWithdraw(int currencyForWithdrawal, double numberForWithdrawal) {
        currencyForDB = getCurrencyAsString(currencyForWithdrawal);
        try {
            account = accountDao.readAccountDB(user.getUserId(), currencyForDB);
            if (account != null && (account.getBalance() - numberForWithdrawal) >= 0 && numberForWithdrawal > 0) {
                if (accountDao.updateAccountDB(account.getBalance() - roundNumber(numberForWithdrawal),
                        user.getUserId(), currencyForDB)) {
                    if (transactionDao.createTransactionDB(account.getAccountId(), -roundNumber(numberForWithdrawal))) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("WITHDRAW FUNDS ERROR. CHECK DATABASE CONNECTION");
        }
        return false;
    }

    private int getRandomId() {
        return (int) (1 + Math.random() * 100);
    }

    private String getCurrencyAsString(int currencyCode) {
        return switch (currencyCode) {
            case 1 -> "USD";
            case 2 -> "EUR";
            case 3 -> "UAH";
            case 4 -> "GBP";
            case 5 -> "PLN";
            default -> null;
        };
    }

    private double roundNumber(double number) {
        return (double) Math.round(number * 1000) / 1000;
    }
}