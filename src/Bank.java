import java.util.ArrayList;

public class Bank {

    private String name; // name of the bank
    private ArrayList<User> user; // list of all the users for this bank
    private ArrayList<Account> accounts; // list of all the accounts for this bank

    public String getNewUserUUID() {

    }

    public String getNewAccountUUID() {

    }

    /*
    add an account
    @param anAcct   the account to add
     */

    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }
}
