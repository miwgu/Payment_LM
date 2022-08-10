import java.math.BigDecimal;
import java.util.Date;

public class Deposit implements PaymentReceiver {
    protected String clearingNumber;//Startpost
    protected String accountNumber; //Startpost
    protected BigDecimal totalAmount;//EndPost
    protected int numberOfPayments; //EndPost

    public Deposit(String clearingNumber, String accountNumber) {
        this.clearingNumber = clearingNumber;
        this.accountNumber = accountNumber;
    }

    public Deposit(String clearingNumber, String accountNumber, BigDecimal totalAmount, int numberOfPayments) {
        this.clearingNumber = clearingNumber;
        this.accountNumber = accountNumber;
        this.totalAmount = totalAmount;
        this.numberOfPayments = numberOfPayments;
    }

    public Deposit() {
    }

    public String getClearingNumber() {
        return clearingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public int getNumberOfPayments() {
        return numberOfPayments;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {

        System.out.println("Accountnummer: "+accountNumber + "\n"+
                "Betalaningsdatum: "+ "-" +"\n"+
                "Valuta: "+ "SEK");
    }

    @Override
    public void payment(BigDecimal amount, String reference) {

    }

    @Override
    public void endPaymentBundle() {
        System.out.println("Summan av belopp:  ");

    }
}
