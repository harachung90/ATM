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
        Account newAccount = new Account("Cheque", aUser, theBank);
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
        } while (authUser == null); //continue looping until successful login
        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc) {
        //print a summary of the user's accounts
        theUser.printAccountsSummary();

        //initiates
        int choice;

        //user menu
        do {
            System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
            System.out.println("\t1) Show account transaction history");
            System.out.println("\t2) Withdraw");
            System.out.println("\t3) Deposit");
            System.out.println("\t4) Transfer");
            System.out.println("\t5) Quit");
            System.out.println();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice < 1 || choice > 5);

        // process the choice
        switch (choice) {
            case 1:
                ATM.showTransHistory(theUser, sc);
                break;
            case 2:
                ATM.withdrawFunds(theUser, sc);
                break;
            case 3:
                ATM.depositFunds(theUser, sc);
                break;
            case 4:
                ATM.transferFunds(theUser, sc);
                break;
            case 5:
                //take the rest of previous input
                sc.nextLine();
                break;
        }

        //redisplay the menu unless the user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(theUser, sc);
        }
    }

    /*
    show the transaction history for an account
    @param theUser      the logged-in User object
    @param sc           the Scanner object used for the user input
     */
    public static void showTransHistory(User theUser, Scanner sc) {
        int theAcct;

        //get account whose transaction history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the account\nwhose transactions you want to see: ", theUser.numAccounts());
            theAcct = sc.nextInt() - 1;
            if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                System.out.println("invalid account. Please try again.");
            }
        } while (theAcct < 0 || theAcct >= theUser.numAccounts());

        //print the transaction history
        theUser.printAcctTransHistory(theAcct);
    }

    /*
    process transferring funds from one account to another
     */
    public static void transferFunds(User theUser, Scanner sc) {
        //initiates
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        //get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + "to transfer from: ", theUser.numAccounts());
            fromAcct = sc.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);

        //get the account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + "to transfer to: ", theUser.numAccounts());
            toAcct = sc.nextInt() - 1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= theUser.numAccounts());

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > acctBal) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        //finally, do the transfer
        theUser.addAcctTransaction(fromAcct, -1 * amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
        theUser.addAcctTransaction(toAcct, amount, String.format("Transfer to account %s", theUser.getAcctUUID(fromAcct)));
    }

    /*
    process a fund withdraw from an account
    @param theUser      the logged-in User object
    @param sc           the Scanner object user for user input
     */
    public static void withdrawFunds(User theUser, Scanner sc) {

        //initiates
        int fromAcct;
        double amount;
        double acctBal;
        String memo;

        //get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\nto withdraw from: ", theUser.numAccounts());
            fromAcct = sc.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount to withdraw (max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > acctBal) {
                System.out.printf("Amount must not be greater than\nbalance of $%.02f.\n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        //take the rest of previous input
        sc.nextLine();

        //get a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        //do thr withdrawal
        theUser.addAcctTransaction(fromAcct, -1 * amount, memo);
    }

    /*
    process a fund deposit to an account
    @param theUser      the logged-in user object
    @param sc           the Scanner object used for user input
     */
    public static void depositFunds(User theUser, Scanner sc) {
        //initiates
        int toAcct;
        double amount;
        double acctBal;
        String memo;

        //get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + "to deposit in: ", theUser.numAccounts());
            toAcct = sc.nextInt() - 1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(toAcct);

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            }
        } while (amount < 0);

        //take the rest of previous input
        sc.nextLine();

        //get a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        //do the withdrawal
        theUser.addAcctTransaction(toAcct, amount, memo);
    }
}
