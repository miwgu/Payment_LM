import java.math.BigDecimal;
import java.util.Date;

public class PaymentDetails implements PaymentReceiver{
    Payment payment;
    BigDecimal amount;
    String reference;

    public PaymentDetails(Payment payment, BigDecimal amount, String reference) {
        this.payment = payment;
        this.amount = amount;
        this.reference = reference;
    }

    public PaymentDetails() {

    }
    public Payment getPayment() {
        return payment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {

    }

    @Override
    public void payment(BigDecimal amount, String reference) {
        System.out.println("Betalningsbelopp: "+amount + "\n"+
                "Betalningsreference: "+ reference);
    }



    @Override
    public void endPaymentBundle() {
        System.out.println("\u001b[00;34m" +
                "--------------------------End data---------------------------" + " \u001b[00m");
    }
}
