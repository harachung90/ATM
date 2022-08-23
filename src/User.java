import java.util.ArrayList;

public class User {

    private String firstName; // the first name of the user
    private String lastName; // the last name of the user
    private String uuid; // unique universal identifier
    private byte pinHash[]; // the MD5 hash of the user's PIN
    private ArrayList<Account> accounts; // the list of accounts for the user


}
