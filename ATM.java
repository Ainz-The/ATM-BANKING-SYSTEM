
import java.util.Scanner;

public class ATM {

    private BankAccount account;
    private Scanner sc;

    public ATM(BankAccount account) {
        this.account = account;
        this.sc = new Scanner(System.in);
    }

    // Customer login
    public boolean login() {

        System.out.println("\n===== ATM LOGIN =====");

        try {
            System.out.print("Enter Account Number: ");
            String enteredAccount = sc.nextLine();

            if (!account.getAccountNumber().equals(enteredAccount)) {
                throw new AccountNotFoundException(
                    "Account not found!"
                );
            }

            System.out.print("Enter PIN: ");
            int enteredPIN = Integer.parseInt(sc.nextLine());

            account.verifyPIN(enteredPIN);

            System.out.println("\nLogin successful!");
            System.out.println("Welcome, " + account.getAccountHolder());

            return true;

        } catch (AccountNotFoundException |
                 InvalidPINException e) {

            System.out.println("Login failed: " + e.getMessage());
            return false;

        } catch (NumberFormatException e) {

            System.out.println("Login failed: PIN must be numeric.");
            return false;
        }
    }

    // Display ATM menu
    public void showMenu() {

        int choice;

        do {
            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        checkBalance();
                        break;

                    case 2:
                        depositMoney();
                        break;

                    case 3:
                        withdrawMoney();
                        break;

                    case 4:
                        changePIN();
                        break;

                    case 5:
                        System.out.println("Thank you for using our ATM!");
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid menu number.");
                choice = 0;
            }

        } while (choice != 5);
    }

    // Check balance
    private void checkBalance() {

        System.out.println(
            "Available Balance: Rs. " + account.checkBalance()
        );
    }

    // Deposit money
    private void depositMoney() {

        try {
            System.out.print("Enter deposit amount: Rs. ");
            double amount = Double.parseDouble(sc.nextLine());

            account.deposit(amount);

            System.out.println(
                "Updated Balance: Rs. " + account.checkBalance()
            );

        } catch (InvalidAmountException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid amount.");
        }
    }

    // Withdraw money
    private void withdrawMoney() {

        try {
            System.out.print("Enter withdrawal amount: Rs. ");
            double amount = Double.parseDouble(sc.nextLine());

            account.withdraw(amount);

            System.out.println(
                "Updated Balance: Rs. " + account.checkBalance()
            );

        } catch (InvalidAmountException |
                 InsufficientBalanceException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid amount.");
        }
    }

    // Change PIN
    private void changePIN() {

        try {
            System.out.print("Enter old PIN: ");
            int oldPIN = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new PIN: ");
            int newPIN = Integer.parseInt(sc.nextLine());

            account.changePIN(oldPIN, newPIN);

        } catch (InvalidPINException |
                 InvalidAmountException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Please enter numeric PINs.");
        }
    }
}
