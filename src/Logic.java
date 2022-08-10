import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Logic {

    /*
     * Check if the total payment is correct from the sum of each payment
     *
     * */

    public static void checkTotalPayment_Payment(List<PaymentDetails> paymentDetails_x) {

        double sum = 0;
        for (PaymentDetails pd1 : paymentDetails_x) {
            BigDecimal bd = pd1.getAmount();
            double eachPayment = bd.doubleValue();
            sum += eachPayment;
            System.out.println("Betalningsbelopp: " + eachPayment);
        }
        BigDecimal bd_sum = BigDecimal.valueOf(sum);
        System.out.println("Verifiering av totala betalningar: " + bd_sum);
        BigDecimal totalAmount = paymentDetails_x.get(0).getPayment().getTotalAmount();
        System.out.println("Summan av beloppsfälten: " + totalAmount);

        printOutCorrectOrNot(bd_sum, totalAmount);

    }

    public static void checkTotalPayment_Deposit(List<DepositDetails> depositDetails_x) {

        double sum = 0;
        for (DepositDetails dd1 : depositDetails_x) {
            BigDecimal bd = dd1.getAmount();
            double eachPayment = bd.doubleValue();
            sum += eachPayment;
            System.out.println("Betalningsbelopp: " + eachPayment);
        }
        BigDecimal bd_sum = BigDecimal.valueOf(sum);
        System.out.println("Verifiering av totala betalningar: " + bd_sum);
        //You can get the total payment in the last raw
        int lastIndex = depositDetails_x.size() - 1;
        BigDecimal totalAmount = depositDetails_x.get(lastIndex).getDeposit().getTotalAmount();
        System.out.println("Summan av beloppsfälten: " + totalAmount);

        printOutCorrectOrNot(bd_sum, totalAmount);
    }

    public static void printOutCorrectOrNot(BigDecimal sum_x, BigDecimal total_x) {

        if (sum_x.compareTo(total_x) == 0) {
            System.out.println(
                    "**********************************\n" +
                    "Summan av beloppen är korrekt\n" +
                    "**********************************\n");
        } else {
            System.out.println("\u001b[00;31m" +
                    "**********************************\n" +
                    "Summan av beloppen är inte korrekt.\n" +
                    "Du behöver kolla på filen!\n" +
                    "**********************************\n" +
                    " \u001b[00m");
        }
    }

    public static List<PaymentDetails> getValidFiles_Payment( List<PaymentDetails> paymentDetails_all) {
        List<PaymentDetails> onlyValidFiles_Payment = new ArrayList<>();

        for (PaymentDetails pd : paymentDetails_all) {
            double sum = 0;
            for (PaymentDetails pd1 : paymentDetails_all) {
                BigDecimal bd = pd1.getAmount();
                double eachPayment = bd.doubleValue();
                sum += eachPayment;
                //System.out.println("Betalningsbelopp: " + eachPayment);
            }
            BigDecimal bd_sum = BigDecimal.valueOf(sum);
            //System.out.println("Verifiering av totala betalningar: " + bd_sum);
            BigDecimal totalAmount = paymentDetails_all.get(0).getPayment().getTotalAmount();
            //System.out.println("Summan av beloppsfälten: " + totalAmount);

            if (bd_sum.compareTo(totalAmount) == 0) {
                onlyValidFiles_Payment.add(pd);
            }
        }
        return onlyValidFiles_Payment;
    }

    public static List<DepositDetails> getValidFiles_Deposit(List<DepositDetails> depositDetails_all) {
        List<DepositDetails> onlyValidFiles_Deposit = new ArrayList<>();
        for (DepositDetails dd : depositDetails_all) {

            double sum = 0;
            for (DepositDetails dd1 : depositDetails_all) {
                BigDecimal bd = dd1.getAmount();
                double eachPayment = bd.doubleValue();
                sum += eachPayment;
                //System.out.println("Betalningsbelopp: " + eachPayment);
            }
            BigDecimal bd_sum = BigDecimal.valueOf(sum);
            //System.out.println("Verifiering av totala betalningar: " + bd_sum);
            //You can get the total payment in the last raw
            int lastIndex = depositDetails_all.size() - 1;
            BigDecimal totalAmount = depositDetails_all.get(lastIndex).getDeposit().getTotalAmount();
            //System.out.println("Summan av beloppsfälten: " + totalAmount);
            if (bd_sum.compareTo(totalAmount) == 0) {
                onlyValidFiles_Deposit.add(dd);
            }
        }
        return onlyValidFiles_Deposit;
    }

}
