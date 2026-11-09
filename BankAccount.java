
public class BankAccount {

    private String accountNumber;
    private String accountHolder;
    private int pin;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolder,
                       int pin, double balance) {

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.pin = pin;
        this.balance = balance;
    }

    // Get account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Get account holder name
    public String getAccountHolder() {
        return accountHolder;
    }

    // Check PIN
    public void verifyPIN(int enteredPIN)
            throws InvalidPINException {

        if (this.pin != enteredPIN) {
            throw new InvalidPINException("Incorrect PIN!");
        }
    }

    // Check balance
    public double checkBalance() {
        return balance;
    }

    // Deposit money
    public void deposit(double amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                "Deposit amount must be greater than zero!"
            );
        }

        balance += amount;

        System.out.println("Amount deposited successfully!");
    }

    // Withdraw money
    public void withdraw(double amount)
            throws InvalidAmountException,
                   InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                "Withdrawal amount must be greater than zero!"
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient account balance!"
            );
        }

        balance -= amount;

        System.out.println("Please collect your cash.");
    }

    // Change PIN
    public void changePIN(int oldPIN, int newPIN)
            throws InvalidPINException,
                   InvalidAmountException {

        verifyPIN(oldPIN);

        if (newPIN < 1000 || newPIN > 9999) {
            throw new InvalidAmountException(
                "New PIN must contain exactly 4 digits!"
            );
        }

        if (oldPIN == newPIN) {
            throw new InvalidAmountException(
                "New PIN must be different from the old PIN!"
            );
        }

        this.pin = newPIN;

        System.out.println("PIN changed successfully!");
    }
}
