package com.myBank.bankServer;

public class UserAccount {
    private final String USERNAME;
    private final long ACCOUNTNUMBER;
    private String password;
    private long mobileNumber;
    private double balance = 0.0;

    UserAccount(String userName, long accountNumber, String password, long mobileNumber) {
        this.USERNAME = userName;
        this.ACCOUNTNUMBER = accountNumber;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }

    protected String getUSERNAME() {
        return USERNAME;
    }

    protected long getAccountNumber() {
        return ACCOUNTNUMBER;
    }

    protected double getBalance() {
        return balance;
    }

    protected long getMobileNumber() {
        return mobileNumber;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    protected boolean isUserValid(long accountNumber, String password) {
        return accountNumber == this.ACCOUNTNUMBER && this.password.equals(password);
    }

    protected boolean isMobileNumberRegistered(long mobileNumber){
        return this.mobileNumber == mobileNumber;
    }

    protected void withdraw(double amount) {
        if (amount > this.balance) {
            System.out.println("Insufficient funds. Transaction failed.");
            return;
        }
        this.balance -= amount;
    }

    protected void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        this.balance += amount;
    }
}
