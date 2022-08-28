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

        User curUser;
        while (true) {
            //stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank, sc);

            //stay in the main menu until use quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    /*
    print the ATM's login meni
    @param theBank      the Bank object whose accounts to use
    @param sc           the Scanner object to user for user input
    @return             the authenticated User object
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        //initiates
        String userID;
        String pin;
        User authUser;

        //prompt the user for user ID/pin combo until a correct one is reached
        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();

            //try to get the user object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID/pin combination. " + "Please try again");
            }
        } while( authUser == null); //continue looping until successful login
        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc) {
        //print a summary of the user's accounts
        theUser.printAccountsSummary();

        //initiates
        int choice;

        //user menu
        do {
            System.out.printf("Welcome %s, what would you like to do?", theUser.getFirstName());
            System.out.println("\t1) Show account transaction history");
            System.out.println("\t2) Withdrawal");
            System.out.println("\t3) Deposit");
            System.out.println("\t4) Transfer");
            System.out.println("\t5) Quit");
            System.out.println();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
               System.out.println("Invalid choice. Please choose again.");
            } while (choice <1 || choice > 5)'

        // process the choice
            switch (choice) {
                case 1:
                    ATM.showTransHistory(theUser, sc);
                    break;
                case 2:
                    ATM.showTransHistory(theUser, sc);
                    break;
                case 3:
                    ATM.showTransHistory(theUser, sc);
                    break;
                case 4:
                    ATM.showTransHistory(theUser, sc);
                    break;
            }
            //redisplay the menu unless the user wants to quit
            if (choice != 5) {
                ATM.printUserMenu(theUser, sc);
            }
        } while ();
    }
}
