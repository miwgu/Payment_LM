import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    Logic l = new Logic();
    BigDecimal bd = new BigDecimal("3300.33");
    BigDecimal bd1_1 = new BigDecimal("1000.00");
    BigDecimal bd1_2 = new BigDecimal("2000.00");
    BigDecimal bd1_3 = new BigDecimal("300.33");

    BigDecimal bd2 = new BigDecimal("1234.12");
    BigDecimal bd2_1 = new BigDecimal("1000.12");
    BigDecimal bd2_2 = new BigDecimal("2000.00");

    String paymentDate = "20220202";
    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
    Date payment_date;

    {
        try {
            payment_date = sdFormat.parse(paymentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    Payment p1= new Payment("11111", "1111111111", bd,3, payment_date, "SEK");
    PaymentDetails pd1= new PaymentDetails(p1, bd1_1,"2345678900" );
    PaymentDetails pd2= new PaymentDetails(p1, bd1_2,"2345678901" );
    PaymentDetails pd3= new PaymentDetails(p1, bd1_3,"2345678902" );

    List<PaymentDetails> testPaymentDetails = new ArrayList<>();
    List<PaymentDetails> validPaymentDetails= new ArrayList<>();

    Payment p2= new Payment("22222", "2222222222", bd2,2, payment_date, "SEK");
    PaymentDetails pd2_1= new PaymentDetails(p2, bd2_1,"2345678909" );
    PaymentDetails pd2_2= new PaymentDetails(p2, bd2_2,"2345678908" );

    List<PaymentDetails> testPaymentDetails_2 = new ArrayList<>();
    List<PaymentDetails> validPaymentDetails_2= new ArrayList<>();

    @Test
    void getValidFiles_Payment() {
        testPaymentDetails = Arrays.asList(pd1,pd2,pd3);
        validPaymentDetails= l.getValidFiles_Payment(testPaymentDetails);
        assertTrue(testPaymentDetails.size()==3);
        assertTrue(validPaymentDetails.size()==3);// TotalAmount is equal to sum of payments
        assertTrue(validPaymentDetails.size()==testPaymentDetails.get(0).getPayment().numberOfPayments);
        assertFalse(testPaymentDetails.size()==2);

        assertTrue(testPaymentDetails.get(0).getPayment().getAccountNumber().equals("1111111111"));
        assertTrue(validPaymentDetails.get(0).getPayment().getAccountNumber().equals("1111111111"));

        /*
        * testPaymentDetails_2 has an incorrect data as totalAmount
        * So validPaymentDetails_2 should not include these paymentDetails-data
        * */
        testPaymentDetails_2 = Arrays.asList(pd2_1,pd2_2);
        validPaymentDetails_2= l.getValidFiles_Payment(testPaymentDetails_2);
        assertTrue(testPaymentDetails_2.size()==2);
        assertTrue(testPaymentDetails_2.size()==testPaymentDetails_2.get(0).getPayment().getNumberOfPayments());
        assertTrue(validPaymentDetails_2.size()==0);// TotalAmount is NOT equal to sum of payments
        assertFalse(validPaymentDetails_2.size()==2);

        assertTrue(testPaymentDetails_2.get(0).getPayment().getAccountNumber().equals("2222222222"));


    }


}