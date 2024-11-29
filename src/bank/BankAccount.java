package bank;

public abstract class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public abstract boolean buyLoad(String number, double amount, String serviceProvider);

    public abstract boolean transferMoney(BankAccountClass.UserAccount recipient, double amount);
}
