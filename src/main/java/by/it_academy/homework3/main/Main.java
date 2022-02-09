package by.it_academy.homework3.main;

import by.it_academy.homework3.controller.Controller;
import by.it_academy.homework3.dao.AccountDao;
import by.it_academy.homework3.dao.TransactionDao;
import by.it_academy.homework3.dao.UserDao;
import by.it_academy.homework3.model.Account;
import by.it_academy.homework3.model.User;
import by.it_academy.homework3.service.Service;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        AccountDao accountDao = new AccountDao();
        User user = new User();
        UserDao userDao = new UserDao();
        TransactionDao transactionDao = new TransactionDao();
        Service service = new Service(account, accountDao, user, userDao, transactionDao);
        Controller application = new Controller(service);
        application.start();
    }
}