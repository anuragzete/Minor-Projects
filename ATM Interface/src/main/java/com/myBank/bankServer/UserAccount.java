package com.myBank.bankServer;

/**
 * Represents a user account in the bank system.
 * Stores account details including username, account number, password,
 * mobile number, and balance. Provides methods for updating and
 * retrieving account information.
 */
public class UserAccount {

    /** The username associated with the account. */
    private final String USERNAME;

    /** The unique account number for the user. */
    private final long ACCOUNTNUMBER;

    /** The password used for authentication. */
    private String password;

    /** The mobile number linked to the account. */
    private long mobileNumber;

    /** The current balance of the account. */
    private double balance = 0.0;


    /**
     * Constructs a new user account with the specified details.
     *
     * @param userName      The username for the account.
     * @param accountNumber The unique account number.
     * @param password      The password for authentication.
     * @param mobileNumber  The mobile number linked to the account.
     */

    UserAccount(String userName, long accountNumber, String password, long mobileNumber) {
        this.USERNAME = userName;
        this.ACCOUNTNUMBER = accountNumber;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }

    /**
     * Retrieves the username associated with the account.
     *
     * @return The username of the account.
     */
    protected String getUSERNAME() {
        return USERNAME;
    }

    /**
     * Retrieves the account number of the user.
     *
     * @return The account number.
     */
    protected long getAccountNumber() {
        return ACCOUNTNUMBER;
    }

    /**
     * Retrieves the current balance of the account.
     *
     * @return The current balance.
     */
    protected double getBalance() {
        return balance;
    }

    /**
     * Retrieves the mobile number linked to the account.
     *
     * @return The mobile number.
     */
    protected long getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Updates the password of the account.
     *
     * @param password The new password to set.
     */
    protected void setPassword(String password) {
        this.password = password;
    }

    /**
     * Updates the mobile number associated with the account.
     *
     * @param mobileNumber The new mobile number to set.
     */
    protected void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Validates the user by checking the account number and password.
     *
     * @param accountNumber The account number to verify.
     * @param password      The password to verify.
     * @return {@code true} if the credentials match, otherwise {@code false}.
     */
    protected boolean isUserValid(long accountNumber, String password) {
        return accountNumber == this.ACCOUNTNUMBER && this.password.equals(password);
    }

    /**
     * Checks if the given mobile number is already registered.
     *
     * @param mobileNumber The mobile number to check.
     * @return {@code true} if the mobile number is registered, otherwise {@code false}.
     */
    protected boolean isMobileNumberRegistered(long mobileNumber){
        return this.mobileNumber == mobileNumber;
    }

    /**
     * Withdraws the specified amount from the account balance.
     * Displays an error message if funds are insufficient.
     *
     * @param amount The amount to withdraw.
     */
    protected void withdraw(double amount) {
        if (amount > this.balance) {
            System.out.println("Insufficient funds. Transaction failed.");
            return;
        }
        this.balance -= amount;
    }


    /**
     * Deposits the specified amount into the account balance.
     * Displays an error message if the amount is invalid.
     *
     * @param amount The amount to deposit.
     */
    protected void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        this.balance += amount;
    }
}
