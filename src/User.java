import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
public class User {

    private String firstName; // the first name of the user
    private String lastName; // the last name of the user
    private String uuid; // unique universal identifier
    private byte pinHash[]; // the MD5 hash of the user's PIN
    private ArrayList<Account> accounts; // the list of accounts for the user

    /*
    # create a new user
    @param firstName    the user's first name
    @param lastName     the user's last name
    @param pin          the user's account PIN
    @param theBank      the Bank object that the user is a customer of
     */

    public User(String firstName, String lastName, String pin, Bank theBank) {
        this.firstName = firstName; // set the user's name
        this.lastName = lastName;

        // store the pin's MD5 hash, rather than the original value for security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught NoSuchAlgorithmExeception");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();

        // create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print log message
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);

    }

    /*
    add an account for the user
    @param acAcct       the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /*
return the user's UUID
@return the uuid
 */
    public String getUUID() {
        return this.uuid;
    }

    /*
    check whether a given pin matches the true User pin
    @param aPin         the pin to check
    @return             whether the pin is valid or not
     */
    public boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught NoSuchAlgorithmExeception");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /*
    return the user's first name
    @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }



    /*
    print summaries for the accounts of this user
     */
    public void printAccountsSummary() {
        System.out.printf("\n\n%s's accounts summary", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    /*
    get the number of accounts of the user
    @return the number of accounts
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /*
    print transaction history for a particular account
    @param acctIdx  the index of the account to use
     */
    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }
}
