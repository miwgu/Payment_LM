import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment implements PaymentReceiver {
    protected String clearingNumber;//StartPost
    protected String accountNumber;//StartPost
    protected int numberOfPayments;
    protected Date paymentDate;//StartPost
    protected String currency;//StartPost
    protected BigDecimal totalAmount;//StartPost
    protected BigDecimal amount;//PaymentPost
    protected String reference;//PaymentPost

    public Payment(String clearingNumber, String accountNumber,  BigDecimal totalAmount, int numberOfPayments,Date paymentDate, String currency) {
        this.clearingNumber= clearingNumber;
        this.accountNumber = accountNumber;
        this.totalAmount=totalAmount;
        this.numberOfPayments=numberOfPayments;
        this.paymentDate = paymentDate;
        this.currency = currency;
    }


    public Payment() {

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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getCurrency() {
        return currency;
    }


    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {
        String strDate = new SimpleDateFormat("yyyyMMdd").format(paymentDate);
        System.out.println("Accountnummer: "+accountNumber + "\n"+
                            "Betalaningsdatum: "+ strDate +"\n"+
                            "Valuta: "+ currency);
    }

    @Override
    public void payment(BigDecimal amount, String reference) {
        System.out.println("Betalningsbelopp: "+amount + "\n"+
                "Betalningsreference: "+ reference);
    }

    @Override
    public void endPaymentBundle() {

        System.out.println("");
    }
}
