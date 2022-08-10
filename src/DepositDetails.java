import java.math.BigDecimal;
import java.util.Date;

public class DepositDetails implements PaymentReceiver {
    Deposit deposit;
    BigDecimal amount;
    String reference;

    public DepositDetails(Deposit deposit, BigDecimal amount, String reference) {
        this.deposit = deposit;
        this.amount = amount;
        this.reference = reference;
    }

    public DepositDetails() {
    }

    public Deposit getDeposit() {
        return deposit;
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
        System.out.println("Inbetalningsbelopp: "+amount + "\n"+
                "Inbetalningsreference: "+ reference);

    }

    @Override
    public void endPaymentBundle() {
        System.out.println("\u001b[00;34m" +
                "--------------------------End data---------------------------" + " \u001b[00m");
    }
}
