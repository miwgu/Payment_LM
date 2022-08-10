import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {


        BigDecimal bd = new BigDecimal("3300.12");
        String paymentDate = "20220505";
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
        Date payment_date;

    {
        try {
            payment_date = sdFormat.parse(paymentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    Payment p = new Payment("11111", "1111111111", bd,3, payment_date, "SEK");


    @Test
    void getClearingNumber() {
        assertTrue(p.getClearingNumber().equals("11111"));
        assertFalse(p.getClearingNumber().equals("222"));
    }

    @Test
    void getAccountNumber() {
        assertTrue(p.getAccountNumber().equals("1111111111"));
        assertFalse(p.getAccountNumber().equals("2222222222"));
    }

    @Test
    void getTotalAmount() {
        BigDecimal bd2 = new BigDecimal("3300.12");
        BigDecimal bd3 = new BigDecimal("100.00");
        assertTrue(p.getTotalAmount().equals(bd2));
        assertFalse(p.getTotalAmount().equals(bd3));
    }

    @Test
    void getNumberOfPayments() {
        assertEquals(3,p.getNumberOfPayments());
        assertNotEquals(20,p.getNumberOfPayments());
    }

    @Test
    void getPaymentDate() {
        String paymentDate1 = "20220505";
        String paymentDate2 = "20220515";
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
        Date payment_date1 = null;
        Date payment_date2 = null;

        {
            try {
                payment_date1 = sdFormat.parse(paymentDate1);
                payment_date2 = sdFormat.parse(paymentDate2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        assertEquals(p.getPaymentDate(), payment_date1);
        assertTrue(p.getPaymentDate().before(payment_date2));
    }

    @Test
    void getCurrency() {
        assertTrue(p.getCurrency().equals("SEK"));
        assertFalse(p.getCurrency().equals("JPN"));

    }


    @Test
    void startPaymentBundle() {
    }

    @Test
    void payment() {
    }

    @Test
    void endPaymentBundle() {
    }

}