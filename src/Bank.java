import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name; // name of the bank
    private ArrayList<User> users; // list of all the users for this bank
    private ArrayList<Account> accounts; // list of all the accounts for this bank

    /*
    generate a new universally unique ID for a user
    @return the uuid
     */
    public String getNewUserUUID() {
        // initiates
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {
            //generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

         // check to make sure it's unique
         nonUnique = false;
         for (User u : this.users) {
             if (uuid.compareTo(u.getUUID()) == 0) {
                 nonUnique = true;
                 break;
             }
         }
        } while (nonUnique);

        return uuid;
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
