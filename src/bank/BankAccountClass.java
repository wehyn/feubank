package bank;

import java.util.ArrayList;
import java.util.Random;

public class BankAccountClass {
    public static class UserAccount {
        String accountNumber; // Random Generated Account Number
        String username; // Auto generated First Name Initial + Middle Name Initial + Surname
        String firstName;
        String middleName;
        String lastName;
        String email;
        String birthday;
        String address;
        double balance;
        String password;
        String pin;
        ArrayList<Transaction> transactions = new ArrayList<>();

        public UserAccount(String firstName, String middleName, String lastName, String email, String birthday, String address, double balance, String password, String pin) {
            this.accountNumber = generateAccountNumber();
            this.username = (String.valueOf(firstName.charAt(0)) + middleName.charAt(0) + lastName).toLowerCase();
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.email = email;
            this.birthday = birthday;
            this.address = address;
            this.balance = balance;
            this.password = password;
            this.pin = pin;
        }

        public boolean checkUserCredentials(String username, String inputPassword) {
            return (username.equals(this.username) || username.equals(this.email)) && this.password.equals(inputPassword);
        }


        public ArrayList<Transaction> getTransactions() {
            return this.transactions;
        }

        private String generateAccountNumber() {
            Random random = new Random();
            int randomNumber = 1000 + random.nextInt(9000); // Generates a random number between 1000 and 9999
            return "FEU-" + randomNumber;
        }

        public String getAccountNumber() {
            return this.accountNumber;
        }

        public double getBalance() {
            return this.balance;
        }

        public String getUsername() {
            return this.username;
        }

        public String getBirthday() {
            return this.birthday;
        }

        public String getEmail() {
            return this.email;
        }

        public boolean buyLoad(String number, double amount, String serviceProvider) {
            double transactionFee = 1;

            if (amount <= 0) {
                System.out.println("1");
                return false;
            }

            if (this.balance < 0) {
                System.out.println("2");
                return false;
            }

            if (this.balance < amount + transactionFee) {
                System.out.println("3");
                return false;
            }

            if (number.length() != 11){
                System.out.println("4");
                return false;
            }

            this.balance -= amount + transactionFee;

            Transaction buyLoad = new Transaction();
            buyLoad.accountNumber = this.accountNumber;
            buyLoad.details = "Buy Load " + serviceProvider;
            buyLoad.amount = -amount;
            buyLoad.recipient = "FEU Bank";
            buyLoad.date = java.time.LocalDate.now().toString();
            return true;

        }



        public boolean transferMoney(UserAccount recipient, double amount) {
            if (amount <= 0) {
                return false;
            }

            if (this.balance < 0) {
                return false;
            }

            if (this.balance < amount) {
                return false;
            }

            this.balance -= amount;

            recipient.balance += amount;

            Transaction senderTransaction = new Transaction();
            senderTransaction.accountNumber = this.accountNumber;
            senderTransaction.details = "Send Money";
            senderTransaction.date = java.time.LocalDate.now().toString();
            senderTransaction.recipient = recipient.accountNumber;
            senderTransaction.amount = -amount;

            Transaction recipientTransaction = new Transaction();
            recipientTransaction.accountNumber = recipient.accountNumber;
            recipientTransaction.details = "Send Money";
            recipientTransaction.date = java.time.LocalDate.now().toString();
            recipientTransaction.recipient = this.accountNumber;
            recipientTransaction.amount = amount;

            this.transactions.add(senderTransaction);
            recipient.transactions.add(recipientTransaction);

            return true;
        }


    }

    public static class Transaction {
        String accountNumber;
        String details;
        String date;
        String recipient;
        double amount;
    }

}
