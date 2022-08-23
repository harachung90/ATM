import java.util.ArrayList;

public class Account {

    private String name; // the name of the account ex) ANZ Smart Savings account
    private double balance; // the current balance of the account
    private String uuid; // different from the user's uuid; the account's ID number
    private User holder; // the User objects that owns this account
    private ArrayList<Transaction> transactions; // the list of transactions for this account

}
