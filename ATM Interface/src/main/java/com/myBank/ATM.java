package com.myBank;

import com.myBank.bankServer.Bank;
import com.myBank.bankServer.UserAccount;

public class ATM extends Bank {
    private UserAccount currentUser;

    ATM() {
        initializeAtm();
    }

    private void initializeAtm() {
        System.out.println("Welcome to the ATM!");
        System.out.println("What do you want to do?");
        int choice;
        while (true) {
            try {
                System.out.println("1. Create an account\n2. Login");
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid mobile number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    createAccount();
                    System.out.println("Want to login? (y/n)");
                    String response = sc.nextLine();
                    if (response.equalsIgnoreCase("y")) {
                        login();
                        userPanel();
                    }else if (response.equalsIgnoreCase("n")){
                        System.out.println("Thank you for using the ATM. Have a nice day!");
                    }else {
                        System.out.println("Invalid input. Exiting the ATM.");
                        return;
                    }
                    return;
                case 2:
                    login();
                    userPanel();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void userPanel() {
        System.out.println("Welcome, " + getUsername(currentUser) + "!");
        System.out.println("What would you like to do?");

        while (true) {
            try {
                System.out.println("1. Check Balance\n2. Withdraw\n3. Deposit\n4. Transfer Money\n5. Update Account\n6. Delete Account\n7. Back\n8. Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.printf("Your current balance is ₹%.2f%n", getBalance(currentUser));
                        break;
                    case 2:
                        System.out.println("Enter the amount you want to withdraw:");
                        double withdrawAmount = sc.nextDouble();
                        sc.nextLine();
                        updateBalance(currentUser,withdrawAmount,false);
                        break;
                    case 3:
                        System.out.println("Enter the amount you want to deposit:");
                        double depositAmount = sc.nextDouble();
                        sc.nextLine();
                        updateBalance(currentUser,depositAmount,true);
                        break;
                    case 4:
                        transferMoney(currentUser);
                        break;
                    case 5:
                        updateAccount(currentUser);
                        break;
                    case 6:
                        deleteAccount(currentUser);
                        break;
                    case 7:
                        initializeAtm();
                        break;
                    case 8:
                        System.out.println("Thank you for using the ATM. Have a nice day!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                for (int i = 0; i < 25; i++) {
                    System.out.print("-");
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                sc.nextLine();
            }
        }
    }

    private void login() {
        long accountNumber;

        while (true) {
            try {
                System.out.println("Please enter your account number:");
                accountNumber = sc.nextLong();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid account number.");
                sc.nextLine();
                continue;
            }

            System.out.println("Please enter your password:");
            String password = sc.nextLine();

            if (validateUser(accountNumber, password)) {
                currentUser = getUserAccount(accountNumber, password);
                System.out.println("Login successful!");
                break;
            } else {
                System.out.println("Invalid account number or password. Please try again.");
            }
        }
    }

}
