import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void readDataFromPaymentDetailsFile() {
    }

    Database db = new Database();
    String inFile = "test/Test_Exempelfil_inbetalningstjansten.txt";
    List<DepositDetails> depositDetails = db.readDataFromDepositDetailsFile(inFile);

    BigDecimal bd1 = new BigDecimal("15300.00");
    Deposit d1 = new Deposit("1234","1234567897",null,0);
    Deposit d2 = new Deposit("1234","1234567897",bd1,3);

    BigDecimal bd1_1 = new BigDecimal("4000.00");
    BigDecimal bd1_2 = new BigDecimal("1000.00");
    BigDecimal bd1_3 = new BigDecimal("10300.00");
    BigDecimal zero= new BigDecimal("0");
    DepositDetails dd1= new DepositDetails(d1,bd1_1,"9876543210");//The first line does not include totalAmount and the number of payments
    DepositDetails dd2= new DepositDetails(d1,bd1_2,"9876543210");
    DepositDetails dd3= new DepositDetails(d1,bd1_3,"9876543210");
    DepositDetails dd4= new DepositDetails(d2,zero,null);// The last line of this file" _inbetalningstjansten.txt" includes totalAmount and the number of payments

    List<DepositDetails> testDepositDetails = new ArrayList<>();

    @Test
    void readDataFromDepositDetailsFile() {
        testDepositDetails = Arrays.asList(dd1,dd2,dd3,dd4);
        assertTrue(testDepositDetails.size()==4);
        assertTrue(testDepositDetails.size()==depositDetails.size());

        assertTrue(testDepositDetails.get(3).getDeposit().getTotalAmount().equals(depositDetails.get(3).getDeposit().getTotalAmount()));
        BigDecimal bd_test= new BigDecimal("15300.00");
        assertTrue(testDepositDetails.get(3).getDeposit().getTotalAmount().equals(bd_test));

        assertEquals(testDepositDetails.get(0).getDeposit().getTotalAmount(),null);
        assertEquals(depositDetails.get(0).getDeposit().getTotalAmount(),null);
        assertNotEquals(testDepositDetails.get(0).getDeposit().getTotalAmount(),testDepositDetails.get(3).getDeposit().getTotalAmount());


        assertEquals(depositDetails.get(1).getDeposit().getTotalAmount(),null);
        assertEquals(depositDetails.get(2).getDeposit().getTotalAmount(),null);

        BigDecimal bd1_test2 = new BigDecimal("4000.00");
        assertEquals(testDepositDetails.get(0).getAmount(),bd1_test2);
        assertEquals(testDepositDetails.get(testDepositDetails.size()-1).getAmount(),null);
        BigDecimal zero_test= new BigDecimal("0");
        assertEquals(testDepositDetails.get(3).getAmount(),zero_test);
        assertEquals(testDepositDetails.get(3).getReference(),null);
    }
}