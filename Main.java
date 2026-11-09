

public class Main {

    public static void main(String[] args) {

        // Create a sample bank account
        BankAccount account = new BankAccount(
            "ATM1001",
            "Zain",
            1234,
            10000.00
        );

        // Create ATM object
        ATM atm = new ATM(account);

        // Start ATM application
        System.out.println("********************************");
        System.out.println("   WELCOME TO JAVA ATM SYSTEM");
        System.out.println("********************************");

        if (atm.login()) {
            atm.showMenu();
        } else {
            System.out.println("Please restart the application.");
        }
    }
}
