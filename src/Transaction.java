import java.util.Date;

public class Transaction {

    private double amount; // the amount of this transaction
    private Date timestamp; // the time and date of this transaction
    private String memo; // the memo for this transaction ex) rent money for this month
    private Account inAccount; // the account in which the transaction was performed


    /*
create a new transaction
@param amount       the amount transacted
@param inAccount    the account the transaction belongs to
 */

    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    /*
    create a new transaction
    @param amount       the amount transacted
    @param memo         the memo for the transaction
    @param inAccount    the account the transaction belongs to
     */

    public Transaction(double amount, String memo, Account inAccount) {
        // call the two-argument constructor first
        this(amount, inAccount);

        // set the memo
        this.memo = memo;
    }

    /*
    get the amount of the transaction
    @return the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /*
    get a string summarising the transaction
    @return the summary string
     */
    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);
        } else {
        }
    }
}
