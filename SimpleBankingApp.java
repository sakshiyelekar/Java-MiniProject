package Project; // Package declaration

import java.util.HashMap; // Importing the HashMap class from the java.util package
import java.util.Map; // Importing the Map interface from the java.util package
import java.util.Scanner; // Importing the Scanner class from the java.util package
import java.util.ArrayList; // Importing the ArrayList class from the java.util package
import java.util.List; // Importing the List interface from the java.util package

// Define a BankAccount class to manage account balance and operations
class BankAccount {
    private double balance; // Declare a private variable to store the account balance
    private List<String> transactionHistory; // Declare a private List to store the transaction history

    public BankAccount() {
        this.balance = 0; // Initialize balance to 0 when creating an account
        this.transactionHistory = new ArrayList<>(); // Create a new ArrayList to store transaction history
    }

    public double getBalance() {
        return balance; // Return the current balance
    }

    public void deposit(double amount) {
        balance += amount; // Increase balance by the deposited amount
        transactionHistory.add("Deposited Rs" + amount); // Add the transaction to the transaction history
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) { // Check if there's sufficient balance for withdrawal
            balance -= amount; // Decrease balance by the withdrawn amount
            transactionHistory.add("Withdrawn Rs" + amount); // Add the transaction to the transaction history
            return true; // Return true if withdrawal is successful
        }
        return false; // Return false if there's insufficient balance for withdrawal
    }

    public List<String> getTransactionHistory() {
        return transactionHistory; // Return the transaction history
    }
}

// Define a SimpleBankingApp class to manage user registration, login, and banking operations
class SimpleBankingApp {
    private static final Map<String, BankAccount> accounts = new HashMap<>(); // Store accounts
    private static final Map<String, String> passwords = new HashMap<>(); // Store passwords

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        while (true) {
            System.out.println("1. Register\n2. Login\n3. Exit"); // Print menu options
            System.out.println("Enter Your Choice: "); // Prompt for user input
            int choice = scanner.nextInt(); // Read user input as an integer
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    registerUser(scanner); // Call the user registration function
                    break;
                case 2:
                    loginUser(scanner); // Call the user login function
                    break;
                case 3:
                    System.out.println("Exiting..."); // Print exit message
                    scanner.close(); // Close the Scanner object
                    System.exit(0); // Exit the program
                    break;
                default:
                    System.out.println("Invalid choice. Please select again."); // Print error message for invalid choice
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter a username: "); // Prompt for username
        String username = scanner.nextLine(); // Read username from the user

        if (!accounts.containsKey(username)) { // Check if the username is available
            System.out.print("Enter a password: "); // Prompt for password
            String password = scanner.nextLine(); // Read password from the user

            accounts.put(username, new BankAccount()); // Create a new account and store it
            passwords.put(username, password); // Store the password associated with the username

            System.out.println("Account registered successfully."); // Print success message
        } else {
            System.out.println("Username already exists."); // Print error message for existing username
        }
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter username: "); // Prompt for username
        String username = scanner.nextLine(); // Read username from the user

        System.out.print("Enter password: "); // Prompt for password
        String password = scanner.nextLine(); // Read password from the user

        if (accounts.containsKey(username) && passwords.containsKey(username)) {
            // Check if the username and password are valid
            if (password.equals(passwords.get(username))) { // Validate the password
                performBankOperations(scanner, accounts.get(username)); // Perform banking operations for the logged-in user
            } else {
                System.out.println("Invalid password."); // Print error message for invalid password
            }
        } else {
            System.out.println("Username not found."); // Print error message for username not found
        }
    }

    private static void performBankOperations(Scanner scanner, BankAccount account) {
        while (true) {
            System.out.println("\n1. Check Balance\n2. Deposit\n3. Withdraw\n4. Transaction History\n5. Logout"); // Print menu options
            System.out.println("Enter Your Choice: "); // Prompt for user input
            int choice = scanner.nextInt(); // Read user input as an integer
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.println("Balance: RS" + account.getBalance()); // Display account balance
                    break;
                case 2:
                    System.out.print("Enter deposit amount: "); // Prompt for deposit amount
                    double depositAmount = scanner.nextDouble(); // Read deposit amount from the user
                    account.deposit(depositAmount); // Deposit the amount into the account
                    System.out.println("Deposited RS" + depositAmount + " successfully."); // Print success message
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: "); // Prompt for withdrawal amount
                    double withdrawAmount = scanner.nextDouble(); // Read withdrawal amount from the user

                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawn RS" + withdrawAmount + " successfully."); // Print success message
                    } else {
                        System.out.println("Insufficient balance."); // Print error message for insufficient balance
                    }
                    break;
                case 4:
                    displayTransactionHistory(account); // Call the new method for displaying transaction history
                    break;
                case 5:
                    System.out.println("Logged out.\nThank you for using Simple Banking App!\n"); // Print logout message
                    return; // Exit the banking operations loop
                default:
                    System.out.println("Invalid choice. Please select again."); // Print error message for invalid choice
            }
        }
    }

    private static void displayTransactionHistory(BankAccount account) {
        List<String> history = account.getTransactionHistory(); // Get the transaction history from the account

        if (history.isEmpty()) {
            System.out.println("No transactions yet."); // Print a message if transaction history is empty
        } else {
            System.out.println("Transaction History:"); // Print a heading for the transaction history

            for (String transaction : history) {
                System.out.println(transaction); // Print each transaction in the history
            }
        }
    }
}   