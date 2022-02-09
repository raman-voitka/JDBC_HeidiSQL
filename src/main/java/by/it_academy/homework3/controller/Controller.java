package by.it_academy.homework3.controller;

import by.it_academy.homework3.service.Service;

import java.util.Scanner;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public void start() {
        System.out.println("--------------------------");
        System.out.println("Welcome To The Application");
        System.out.println("--------------------------");
        while (true) {
            Scanner enter = new Scanner(System.in);
            System.out.println("For Log In Press \"1\"");
            System.out.println("For Sign Up Press \"2\"");
            System.out.print("For Exit Press \"0\"");
            System.out.println("   -> ");
            if (enter.hasNextInt()) {
                int choice = enter.nextInt();
                if (choice == 0) {
                    break;
                } else if (choice == 1) {
                    logIn();
                } else if (choice == 2) {
                    signUp();
                } else {
                    System.out.println("PLEASE, ENTER CORRECT NUMBER" + '\n');
                }
            } else {
                System.out.println("YOU ENTERED NOT A NUMBER. PLEASE, ENTER A NUMBER" + '\n');
            }
        }
    }

    private void logIn() {
        System.out.println("-----------------------");
        System.out.println("Please, Enter Your Name");
        System.out.println("-----------------------");
        String name = getString();
        System.out.println("---------------------");
        System.out.println("Please, Enter Your ID");
        System.out.println("---------------------");
        int userId = (int) getDouble();
        if (service.isUserExist(name, userId)) {
            getUserMenu();
        } else {
            System.out.println("SORRY. THERE IS NO USER WITH ENTERED NAME AND ENTERED ID" + '\n');
        }
    }

    private void signUp() {
        System.out.println("--------------------------------------------------");
        System.out.println("Please, Enter the Name Which You Would Like to Use");
        System.out.println("--------------------------------------------------");
        String name = getString();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Please, Enter the Address Which You Would Like to Use (Unnecessary field)");
        System.out.println("-------------------------------------------------------------------------");
        String address = getString();
        int id = service.createUser(name, address);
        if (id != 0) {
            System.out.println("Success. Your ID is " + id + ". Please, Remember It!!!");
        } else {
            System.out.println("SORRY. ERRORS OF REGISTRATION. TRY TO CHANGE YOUR NAME");
        }
        System.out.println();
    }

    public void getUserMenu() {
        while (true) {
            Scanner userMenu = new Scanner(System.in);
            System.out.println();
            System.out.println("Dear User, You Can Choose Next Options:");
            System.out.println("To Add The Account Press \"1\"");
            System.out.println("To Refill Account Press \"2\"");
            System.out.println("To Withdrawal Of Funds From The Account Press \"3\"");
            System.out.print("For Exit Press \"0\"");
            System.out.println("   -> ");
            if (userMenu.hasNextInt()) {
                int choice = userMenu.nextInt();
                if (choice == 0) {
                    break;
                } else {
                    switch (choice) {
                        case 1 -> {
                            printInfoAboutCurrency();
                            int currencyCode = getCurrencyCode();
                            if (service.isAccountCreate(currencyCode)) {
                                System.out.println("Congratulation! The Account Was Added");
                            } else {
                                System.out.println("SORRY. CAN'T CREATE ACCOUNT. CHECK:");
                                System.out.println("    1. ENTERED CURRENCY");
                                System.out.println("    2. MAYBE YOU ALREADY HAD ACCOUNT IN ENTERED CURRENCY");
                            }
                        }
                        case 2 -> {
                            printInfoAboutCurrency();
                            int currencyForRefill = getCurrencyCode();
                            System.out.println("----------------------------------------------");
                            System.out.println("Please, Enter The Number Which You Want To Add");
                            System.out.println("----------------------------------------------");
                            double refillNumber = getDouble();
                            if (service.isAccountRefill(currencyForRefill, refillNumber)) {
                                System.out.println("Congratulations! You Have Just Funded Your Account");
                            } else {
                                System.out.println("SO SORRY. THE OPERATION FAILED. POSSIBLE REASONS:");
                                System.out.println("    1. YOU DON'T HAVE ACCOUNT IN THE SELECTED CURRENCY");
                                System.out.println("    2. CHECK ENTERED CURRENCY");
                                System.out.println("    3. VALUE OF TRANSACTION MORE THAN 100'000'000");
                                System.out.println("    4. AS A RESULT OF TRANSACTION, THE BALANCE WILL EXCEED THE LIMIT OF 2'000'000'000");
                                System.out.println("    5. CHECK THE NUMBER FOR REFILL ACCOUNT");
                            }
                        }
                        case 3 -> {
                            printInfoAboutCurrency();
                            int currencyForWithdrawal = getCurrencyCode();
                            System.out.println("---------------------------------------------------");
                            System.out.println("Please, Enter The Number Which You Want To Withdraw");
                            System.out.println("---------------------------------------------------");
                            double withdrawalNumber = getDouble();
                            if (service.isFundsWithdraw(currencyForWithdrawal, withdrawalNumber)) {
                                System.out.println("The Operation Completed Successfully.");
                            } else {
                                System.out.println("MISTAKE. POSSIBLE REASONS:");
                                System.out.println("    1. YOU DON'T HAVE ACCOUNT IN THE SELECTED CURRENCY");
                                System.out.println("    2. INSUFFICIENT MONEY ON THE ACCOUNT");
                                System.out.println("    3. CHECK ENTERED CURRENCY");
                                System.out.println("    4. CHECK THE NUMBER FOR WITHDRAWAL FUNDS");
                            }
                        }
                        default -> System.out.println("PLEASE, ENTER CORRECT NUMBER");
                    }
                }
            } else {
                System.out.println("YOU ENTERED NOT A NUMBER. PLEASE, ENTER A NUMBER");
            }
        }
    }

    private String getString() {
        Scanner enter = new Scanner(System.in);
        return enter.nextLine();
    }

    private int getCurrencyCode() {
        while (true) {
            Scanner enterCode = new Scanner(System.in);
            enterCode.useDelimiter("\n");
            if (enterCode.hasNextInt()) {
                int enteredCode = enterCode.nextInt();
                if (enteredCode >= 1 && enteredCode <= 5) {
                    return enteredCode;
                } else {
                    System.out.println("PLEASE, ENTER CORRECT NUMBER");
                }
            } else {
                System.out.println("YOU ENTERED NOT A NUMBER. PLEASE, ENTER A NUMBER");
            }
        }
    }

    private double getDouble() {
        while (true) {
            Scanner enterDouble = new Scanner(System.in);
            enterDouble.useDelimiter("\n");
            if (enterDouble.hasNextDouble()) {
                return enterDouble.nextDouble();
            } else {
                System.out.println("YOU ENTERED NOT A NUMBER. PLEASE, ENTER A NUMBER");
            }
        }
    }

    private void printInfoAboutCurrency() {
        System.out.println("------------------------------------------------");
        System.out.println("Please, Enter The Currency Which You Want To Use");
        System.out.println("Possible Currencies:");
        System.out.println("1. USD");
        System.out.println("2. EUR");
        System.out.println("3. UAH");
        System.out.println("4. GBP");
        System.out.println("5. PLN");
        System.out.println("Please, Enter Your Choice: 1, 2, 3, 4 or 5");
        System.out.println("------------------------------------------------");
    }
}