import java.util.ArrayList;

public class Account {

    private String name; // the name of the account ex) ANZ Smart Savings account
    //private double balance; // the current balance of the account
    private String uuid; // different from the user's uuid; the account's ID number
    private User holder; // the User objects that owns this account
    private ArrayList<Transaction> transactions; // the list of transactions for this account

    /*
    crate a new account
    @param name     the name of the account
    @param holder   the User object that holds this account
    @param theBank  the bank that issues the account
     */

    public Account(String name, User holder, Bank theBank) {
        // set the account name and holder
        this.name = name;
        this.holder = holder;

        // get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        // initialise transactions
        this.transactions = new ArrayList<Transaction>();
    }

    /*
    get the account ID
    @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    /*
    get summary line for the account
    @return the string summary
     */
    public String getSummaryLine() {
        //get the account's balance
        double balance = this.getBalance();

        //format the summary line, depending on the whether the balance is negative
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this,name);
        }
    }

}
