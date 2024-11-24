package bank;

import java.util.ArrayList;

public class BankAccountClass {
    public static class BankAccount {
        String accountNumber;
        double balance;
        String username;
        String birthday;
        String email;
        String address;
        String name;
        ArrayList<Transaction> transactions = new ArrayList<>();
        String password;
        String pin;

        private String getAccountNumber() {
            return this.accountNumber;
        }

        private double getBalance() {
            return this.balance;
        }

        private String getUsername() {
            return this.username;
        }

        private String getBirthday() {
            return this.birthday;
        }

        private String getEmail() {
            return this.email;
        }

        






        private boolean transferMoney() {
            return true;
        }




        // Need more getters and setters

    }

    public static class Transaction {
        String accountNumber;
        String date;
        String recipient;
        double amount;
    }

}
