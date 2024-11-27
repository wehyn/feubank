package bank;

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

        // TransferMoney Test
        testAccount.transferMoney(secondTestAccount, 200);
        testAccount.transferMoney(secondTestAccount, 100);

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


}
