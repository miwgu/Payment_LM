import java.math.BigDecimal;

public class ChangeDatatype {

    /*
    * Change String to BigDecimal and add Decimal marker
    * */
    public static BigDecimal toBigDecimal(String x){

        int intValue = Integer.parseInt(x); //Delete zero before number String-> Integer => 400000
        String outStr = String.valueOf(intValue); // Integer-> String => 400000
        int p_length= outStr.length();// p_length=> 6 To add DecimalMarker .(2)
        String payment_beforeDecimalMarker =outStr.substring(0,p_length-2).trim();//4000
        String payment_afterDecimalMarker =outStr.substring(p_length-2).trim();//00
        //----- to add decimal marker using StringBuilder----------------------------
        StringBuilder sb= new StringBuilder();
        sb.append(payment_beforeDecimalMarker).append(".").append(payment_afterDecimalMarker);//4000.00
        BigDecimal stringToBD = new BigDecimal(sb.toString());//String->BigDecimal 4000.00
        return  stringToBD;
    }

}
