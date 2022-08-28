import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name; // name of the bank
    private ArrayList<User> users; // list of all the users for this bank
    private ArrayList<Account> accounts; // list of all the accounts for this bank

    /*
    create a new Bank object with empty lists of users and accounts
    @param name     the name of the bank
     */

    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

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
                uuid += ((Integer) rng.nextInt(10)).toString();
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

    /*
    generate a new universally unique ID for an account
    @return the uuid
     */

    public String getNewAccountUUID() {
        // initiates
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {
            //generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            // check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uuid;
    }

    /*
    add an account
    @param anAcct       the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /*
    create a new user of the bank
    @param firstName    the user's first name
    @param lastName     the user's last name
    @param pin          the user's pin
    @return             the new User object
     */

    public User addUser(String firstName, String lastName, String pin) {
        // create a new User object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // create a savings account
        Account newAccount = new Account("Savings", newUser, this);
        // add to holder and bank list
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    /*
    get the user object associated with a particular userId and pin, if they are valid
    @param userID       the UUID of the user to log in
    @param pin          the pin of the user
    @return             the User object, if the login is successful, or null if it is not
     */
    public User userLogin(String userId, String pin) {
        //search through list of users
        for (User u: this.users) {
            //check user ID is correct
            if(u.getUUID().compareTo(userId) == 0 && u.validatePin(pin)) {
                return u;
            }
        }

        //if we haven't found the user or have an incorrect pin
        return null;
    }

    /*
    get the name of the bank
    @return the name of the bank
     */
    public String getName() {
        return this.name;
    }
}
