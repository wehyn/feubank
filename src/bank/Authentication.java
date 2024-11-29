package bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Authentication {
    public List<BankAccountClass.UserAccount> accounts;
    private BankAccountClass.UserAccount loggedInAccount;

    public Authentication() {
        accounts = new ArrayList<>();
        createTestAccount();
    }

    private void createTestAccount() {
        BankAccountClass.UserAccount testAccount = new BankAccountClass.UserAccount(
                 "john", "doe", "doe",
                "johndoe@gmail.com", "10292004", "Pasig City",
                1000.0, "admin", "1029"
        );

        BankAccountClass.UserAccount secondTestAccount = new BankAccountClass.UserAccount(
                "jane", "coe", "doe",
                "janedoe@gmail.com", "10292005", "Quezon City",
                1000.0, "admin", "1029"
        );
        accounts.add(testAccount);
        accounts.add(secondTestAccount);

    }

    public BankAccountClass.UserAccount findAccountNumber(String accountNumber) {
        for (BankAccountClass.UserAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void addUser(BankAccountClass.UserAccount newUser) {
        accounts.add(newUser);
    }

    public boolean authenticate(String username, String password) {
        for (BankAccountClass.UserAccount account : accounts) {
            if (account.checkUserCredentials(username, password)) {
                loggedInAccount = account;
                return true;
            }
        }
        return false;
    }

    public BankAccountClass.UserAccount getLoggedInAccount() {
        return loggedInAccount;
    }

    public boolean addUserToFile(BankAccountClass.UserAccount newUser) {
        if (newUser == null) {
            return false;
        }

        File file = new File("src/bank/accounts.txt");

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(newUser.getAccountNumber() + ",");
            bufferedWriter.write(newUser.getFirstName() + ",");
            bufferedWriter.write(newUser.getMiddleName() + ",");
            bufferedWriter.write(newUser.getLastName() + ",");
            bufferedWriter.write(newUser.getEmail() + ",");
            bufferedWriter.write(newUser.getBirthday() + ",");
            bufferedWriter.write(newUser.getAddress() + ",");
            bufferedWriter.write(newUser.getBalance() + ",");
            bufferedWriter.write(newUser.getPassword() + ",");
            bufferedWriter.write(newUser.getPIN() + "\n");

            bufferedWriter.close();
            fileWriter.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
