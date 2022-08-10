import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Database {

/*
    public static List<Payment> readDataFromPaymentFile (String pathX){

        String firstLine;
        String secondLine;
        Path inFilePath;

        List<Payment> paymentList = new ArrayList<>();


        inFilePath = Paths.get(pathX);

        try (Scanner fileScanner = new Scanner(inFilePath)){

                firstLine = fileScanner.nextLine();

                String clearingNumber= firstLine.substring(1,5).trim();
                String accountNumber = firstLine.substring(6,16).trim();
                String totalAmount = firstLine.substring(16, 30).replace(",", ".").trim();
                BigDecimal stringToBD_totalAmount= new BigDecimal(totalAmount);
                String paymentDate = firstLine.substring(40,48).trim();
                SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
                Date payment_date = sdFormat.parse(paymentDate);
                String currency = firstLine.substring(48).trim();

                Payment p = new Payment(clearingNumber,
                                        accountNumber,
                                        stringToBD_totalAmount,
                                        payment_date,
                                        currency);

                paymentList.add(p);


            if (fileScanner.hasNext()) { //You do not need if actually

                secondLine = fileScanner.nextLine();
                System.out.println("secondline: " + secondLine);
                //-------------BigDecimal-----------------------------------------
                String payment1 = secondLine.substring(1, 15).replace(",", ".").trim();
                BigDecimal stringToBD_eachPayment= new BigDecimal(payment1);
                //--------------Double-------------------------
                double db_eachPayment = Double.parseDouble(payment1);

                //----------------------------------------
                String referens = secondLine.substring(15).trim();
                System.out.println("payment from 2ndline: " + stringToBD_eachPayment);
                System.out.println("referens from 2ndline: " + referens);


                Payment p = new Payment(
                        clearingNumber,
                        accountNumber,
                        stringToBD_totalAmount,
                        payment_date,
                        currency,
                        stringToBD_eachPayment,
                        referens);

                paymentList.add(p);

                while (fileScanner.hasNext()) {
                    secondLine = fileScanner.nextLine();
                    System.out.println("afterThirdline: " + secondLine);

                    //-------------BigDecimal-----------------------------------------
                    payment1 = secondLine.substring(1, 15).replace(",", ".").trim();
                    //BigDecimal stringToBD_eachPayment2= new BigDecimal(payment1);
                    stringToBD_eachPayment= new BigDecimal(payment1);
                    //--------------Double-------------------------
                    double db_eachPayment2 = Double.parseDouble(payment1);
                    double sum1 = 0;
                    sum1=db_eachPayment2++;
                    //----------------------------------------------------------------
                    referens = secondLine.substring(15).trim();
                    System.out.println("payment from after3rdline: " + stringToBD_eachPayment);
                    System.out.println("referens from after3rdline: " + referens);

                    double total=db_eachPayment+sum1;
                    System.out.println("Sum1: "+ sum1);
                    System.out.println("TotalAmout: "+total);

                    Payment p2 = new Payment(
                            clearingNumber,
                            accountNumber,
                            stringToBD_totalAmount,
                            payment_date,
                            currency,
                            stringToBD_eachPayment,
                            referens);

                    paymentList.add(p2);

                }// while second end

            }//If end

        }catch (IOException | ParseException e) {
            System.out.println("Fel inträffade vid läsning från fil");
            e.printStackTrace();
            System.exit(0);
        }
        return paymentList;
    }

 */

    public static List<PaymentDetails> readDataFromPaymentDetailsFile (String pathX){

        String firstLine;
        String secondLine;
        Path inFilePath;

        List<PaymentDetails> paymentDetailsList = new ArrayList<>();

        inFilePath = Paths.get(pathX);

        try (Scanner fileScanner = new Scanner(inFilePath)){

            firstLine = fileScanner.nextLine();

            String clearingNumber= firstLine.substring(1,5).trim();
            String accountNumber = firstLine.substring(6,16).trim();
            String totalAmount = firstLine.substring(16, 30).replace(",", ".").trim();
            BigDecimal stringToBD_totalAmount= new BigDecimal(totalAmount);
            String numberOfPayments=firstLine.substring(30,40).trim();
            Integer int_numberOfP=  Integer.parseInt(numberOfPayments);
            String paymentDate = firstLine.substring(40,48).trim();
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
            Date payment_date = sdFormat.parse(paymentDate);
            String currency = firstLine.substring(48).trim();

            if (fileScanner.hasNext()) { //You do not need if actually

                secondLine = fileScanner.nextLine();

                //-------------payment BigDecimal-----------------------------------------
                String payment1 = secondLine.substring(1, 15).replace(",", ".").trim();
                BigDecimal stringToBD_eachPayment= new BigDecimal(payment1);
                //-------------reference---------------------------
                String referens = secondLine.substring(15).trim();

                Payment p = new Payment(clearingNumber,
                        accountNumber,
                        stringToBD_totalAmount,
                        int_numberOfP,
                        payment_date,
                        currency);

                PaymentDetails pd= new PaymentDetails(p
                        ,stringToBD_eachPayment,referens);

                paymentDetailsList.add(pd);

                while (fileScanner.hasNext()) {
                    secondLine = fileScanner.nextLine();

                    //-------------payment BigDecimal-----------------------------------------
                    payment1 = secondLine.substring(1, 15).replace(",", ".").trim();
                    //BigDecimal stringToBD_eachPayment2= new BigDecimal(payment1);
                    stringToBD_eachPayment= new BigDecimal(payment1);
                    //-------------reference---------------------------------------------------
                    referens = secondLine.substring(15).trim();

                    Payment p2 = new Payment(clearingNumber,
                            accountNumber,
                            stringToBD_totalAmount,
                            int_numberOfP,
                            payment_date,
                            currency);
                    PaymentDetails pd2= new PaymentDetails(p2
                            ,stringToBD_eachPayment,referens);

                    paymentDetailsList.add(pd2);

                }// while second end

            }//If end
            //System.out.println("str "+ str);

        }catch (IOException | ParseException e) {
            System.out.println("Fel inträffade vid läsning från fil");
            e.printStackTrace();
            System.exit(0);
        }
        return paymentDetailsList;
    }

    public static List<DepositDetails> readDataFromDepositDetailsFile (String pathX){

        String firstLine;
        String secondLine;
        Path inFilePath;

        List<DepositDetails> depositDetailsList = new ArrayList<>();

        inFilePath = Paths.get(pathX);

        try (Scanner fileScanner = new Scanner(inFilePath)){

            firstLine = fileScanner.nextLine();

            String clearingNumber =firstLine.substring(10,14).trim();
            String accountNumber =firstLine.substring(14,24).trim();

            //--------------SecondLine---------------------------------

            if (fileScanner.hasNext()) { //You do not need if actually

                secondLine = fileScanner.nextLine();

                //-------------payment string to BigDecimal-----------------------------------------
                String payment =secondLine.substring(2,22).trim();//00000000000000400000
                BigDecimal bd_eachPayment=ChangeDatatype.toBigDecimal(payment);
                //-----------reference-----------------------------
                String referens = secondLine.substring(40).trim();

                Deposit d = new Deposit(
                        clearingNumber,
                        accountNumber,
                        null,
                        0
                      );

                DepositDetails dd= new DepositDetails(
                        d,
                        bd_eachPayment,
                        referens
                );

                depositDetailsList.add(dd);

                while (fileScanner.hasNext()) {

                    secondLine = fileScanner.nextLine();

                    String posytype =secondLine.substring(0,2).trim();//30

                    if(posytype.equalsIgnoreCase("30")) {

                        //System.out.println("Linestart 30");
                        //-------------payment string to BigDecimal-----------------------------------------
                        payment = secondLine.substring(2, 22).trim();//00000000000000400000
                        BigDecimal bd_eachPayment2=ChangeDatatype.toBigDecimal(payment);
                        //----reference--------------------------------------------------------------
                        referens = secondLine.substring(40).trim();

                        Deposit d2 = new Deposit(
                                clearingNumber,
                                accountNumber,
                                null,
                                0
                        );

                        DepositDetails dd2= new DepositDetails(
                                d2,
                                bd_eachPayment2,
                                referens
                        );

                        depositDetailsList.add(dd2);

                    }else{
                        // -----Endpost-------------------------------------------------
                        //-------------Total of payments string to BigDecimal-----------------------------------------
                        String totalAmount = secondLine.substring(2, 22).trim();
                        BigDecimal bd_totalAmount=ChangeDatatype.toBigDecimal(totalAmount);
                        //----reference--------------------------------------------------------------
                        String numberOfPayments = secondLine.substring(30,38).trim();
                        int int_numberOfPayments = Integer.parseInt(numberOfPayments);

                        Deposit d3 = new Deposit(
                                clearingNumber,
                                accountNumber,
                                bd_totalAmount,
                                int_numberOfPayments
                        );

                        BigDecimal zero= new BigDecimal("0");
                        DepositDetails dd3= new DepositDetails(
                                d3,
                                zero,
                                null
                        );

                        depositDetailsList.add(dd3);
                    }

                }// while second end

            }//If end


        }catch (IOException e) {
            System.out.println("Fel inträffade vid läsning från fil");
            e.printStackTrace();
            System.exit(0);
        }
        return depositDetailsList;
    }

}
