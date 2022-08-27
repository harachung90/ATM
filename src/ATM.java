import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
        //initiates Scanner
        Scanner sc = new Scanner(System.in);

        //initiates Bank
        Bank theBank = new Bank("Bank of Hara");

        //add a user, which also creates a savings account
        User aUser = theBank.addUser("Hara", "Chung", "1234");

        //add a checking account for the user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);
    }
}
