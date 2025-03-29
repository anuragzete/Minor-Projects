package com.myBank.bankServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The {@code Bank} class manages all banking operations, including account creation, deletion,
 * updates, transfers, and balance management. It acts as the core banking service layer,
 * maintaining a list of user accounts.
 *
 * <h2>Features:</h2>
 * - Create new user accounts with unique account numbers.
 * - Delete existing accounts with confirmation prompts.
 * - Update user account details such as mobile number or password.
 * - Transfer money between accounts.
 * - Authenticate users and retrieve account details.
 * - Modify account balances for deposits and withdrawals.
 */
public class Bank {

    /**
     * List to store all registered user accounts.
     */
    private final List<UserAccount> accounts = new ArrayList<>();

    /**
     * Scanner instance for user input.
     */
    protected final Scanner sc = new Scanner(System.in);

    /**
     * Creates a new user account with a unique account number.
     * <p>
     * - Generates a random 10-digit account number starting with 607.
     * - Ensures the account number and mobile number are unique.
     * - Verifies password confirmation before account creation.
     * </p>
     */
    protected void createAccount() {
        Random rand = new Random();
        System.out.println("Please enter the name of the user:");
        String name = sc.nextLine();

        long mobileNumber;
        while (true) {
            try {
                System.out.println("Please enter your mobile number:");
                mobileNumber = sc.nextLong();
                sc.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid mobile number.");
                sc.nextLine();
            }
        }

        for (UserAccount user : accounts) {
            if (user.isMobileNumberRegistered(mobileNumber)) {
                System.out.println("Sorry, this mobile number is already registered.");
                return;
            }
        }

        long accountNumber;
        while (true) {
            accountNumber = 607L;
            for (int i = 0; i < 7; i++) {
                accountNumber = accountNumber * 10 + rand.nextInt(10);
            }

            boolean exists = false;
            for (UserAccount user : accounts) {
                if (user.getAccountNumber() == accountNumber) {
                    exists = true;
                    break;
                }
            }

            if (!exists) break;
        }

        String password;
        while (true) {
            System.out.println("Enter your new password:");
            password = sc.nextLine();
            System.out.println("Confirm your new password:");
            String confirmPassword = sc.nextLine();

            if (password.equals(confirmPassword)) {
                break;
            } else {
                System.out.println("Passwords do not match. Please try again.");
            }
        }

        accounts.add(new UserAccount(name, accountNumber, password, mobileNumber));
        System.out.println("Congratulations, you have successfully created a new account.");
        System.out.println("Your account number is " + accountNumber + ". Please note it for future reference.");
    }

    /**
     * Deletes an existing user account after confirmation.
     *
     * @param account The user account to delete.
     */
    protected void deleteAccount(UserAccount account) {
        if (account != null) {
            while (true) {
                System.out.println("Are you sure you want to delete this account? (y/n)");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    accounts.remove(account);
                    System.out.println("Account successfully deleted.");
                    break;
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println("Account deletion canceled.");
                    break;
                } else {
                    System.out.println("Enter a valid option.");
                }
            }
        } else {
            System.out.println("Sorry, this account does not exist.");
        }
    }

    /**
     * Updates a user's mobile number or password.
     *
     * @param account The user account to update.
     */
    protected void updateAccount(UserAccount account) {
        while (true) {
            System.out.println("Choose what you want to update:");
            System.out.println("1] Mobile Number \n2] Password");
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid option.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    while (true) {
                        try {
                            System.out.println("Enter your new mobile number:");
                            long newMobileNumber = sc.nextLong();
                            sc.nextLine();
                            System.out.printf("Are you sure you want to update your existing mobile number %d to %d? (y/n) ", account.getMobileNumber(), newMobileNumber);
                            String confirm = sc.nextLine().trim();
                            if (confirm.equalsIgnoreCase("y")) {
                                account.setMobileNumber(newMobileNumber);
                                System.out.println("Mobile number successfully updated.");
                            } else {
                                System.out.println("Update canceled.");
                            }
                            return;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid mobile number.");
                            sc.nextLine();
                        }
                    }

                case 2:
                    System.out.println("Enter your new password:");
                    String newPassword = sc.nextLine();
                    System.out.println("Confirm your new password:");
                    String confirmPassword = sc.nextLine();
                    if (newPassword.equals(confirmPassword)) {
                        System.out.println("Are you sure you want to update your password? (y/n)");
                        String confirm = sc.nextLine().trim();
                        if (confirm.equalsIgnoreCase("y")) {
                            account.setPassword(newPassword);
                            System.out.println("Password successfully updated.");
                        } else {
                            System.out.println("Update canceled.");
                        }
                    } else {
                        System.out.println("Passwords do not match. Please try again.");
                    }
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }

    /**
     * Transfers money between two user accounts.
     *
     * @param account The sender's account.
     */
    protected void transferMoney(UserAccount account) {
        UserAccount sender = account;

        System.out.println("Enter recipient account number:");
        long recipientAccountNumber = sc.nextLong();
        sc.nextLine();

        UserAccount recipient = null;
        for (UserAccount user : accounts) {
            if (user.getAccountNumber() == recipientAccountNumber) {
                recipient = user;
                break;
            }
        }

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.println("Enter amount to transfer:");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (sender.getBalance() >= amount) {
            sender.withdraw(amount);
            recipient.deposit(amount);
            System.out.printf("Successfully transferred ₹%.2f from %s to %s%n", amount, sender.getUSERNAME(), recipient.getUSERNAME());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    /**
     * Retrieves the {@code UserAccount} instance for the given account number and password.
     *
     * @param accountNumber The user's account number.
     * @param password      The user's password.
     * @return The matching {@code UserAccount} or {@code null} if not found.
     */
    protected UserAccount getUserAccount(long accountNumber, String password) {
        for (UserAccount user : accounts) {
            if (user.getAccountNumber() == accountNumber) {
                if (user.isUserValid(accountNumber, password)) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * Validates if the given credentials match any registered user.
     *
     * @param accountNumber The account number.
     * @param password      The password.
     * @return {@code true} if valid, otherwise {@code false}.
     */
    protected boolean validateUser(long accountNumber, String password) {
        for (UserAccount user : accounts) {
            if (user.getAccountNumber() == accountNumber) {
                if (user.isUserValid(accountNumber, password)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates the balance for deposits or withdrawals.
     *
     * @param account   The user account.
     * @param amount    The transaction amount.
     * @param isDeposit {@code true} for deposit, {@code false} for withdrawal.
     */
    protected void updateBalance(UserAccount account, double amount, boolean isDeposit) {
        if (isDeposit) {
            account.deposit(amount);
            System.out.printf("Successfully deposited ₹%.2f. New balance: ₹%.2f%n", amount, account.getBalance());
        } else {
            account.withdraw(amount);
            System.out.printf("Successfully withdrawn ₹%.2f. New balance: ₹%.2f%n", amount, account.getBalance());
        }
    }

    /**
     * Retrieves the username associated with the given user account.
     *
     * @param account The user account whose username is to be retrieved.
     * @return The username of the specified account.
     */
    protected String getUsername(UserAccount account) {
        return account.getUSERNAME();
    }

    /**
     * Retrieves the account number of the given user account.
     *
     * @param account The user account whose account number is to be retrieved.
     * @return The account number of the specified account.
     */
    protected long getAccountNumber(UserAccount account) {
        return account.getAccountNumber();
    }

    /**
     * Retrieves the current balance of the given user account.
     *
     * @param account The user account whose balance is to be retrieved.
     * @return The current balance of the specified account.
     */
    protected double getBalance(UserAccount account) {
        return account.getBalance();
    }
}
