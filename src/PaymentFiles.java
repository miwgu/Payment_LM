import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class PaymentFiles {

public PaymentFiles() {
    FilenameFilter filter = new FilenameFilter() {

        public boolean accept(File file, String str) {
            if (str.indexOf("_betalningsservice.txt") != -1) {
                return true;
            } else {
                return false;
            }
        }
    };

    File[] files = new File("src/temp").listFiles(filter);

    System.out.println("Det finns "+files.length+ " _betalningsservice.txt");
    for (int i = 0; i < files.length; ++i) {
        System.out.println("Fil" +(i+1)+": "+files[i].toString().substring(9));
    }

    for (int i = 0; i < files.length; ++i) {


        System.out.println("-----File: " + files[i].toString().substring(9)+" start------------");
        String name = "src/temp/" + files[i].toString().substring(9);// Do not need +"\""
        //System.out.println("File: " + name);
        //File inFile_B = new File(name);

        //List<Payment> paymentList = Database.readDataFromPaymentFile(name);
        List<PaymentDetails> paymentDetailsList= Database.readDataFromPaymentDetailsFile(name);

        Logic.checkTotalPayment_Payment(paymentDetailsList);
        List<PaymentDetails> onlyValidFiles_Payment = Logic.getValidFiles_Payment(paymentDetailsList);

        PaymentReceiver pr1= new Payment() ;
        PaymentReceiver pr2= new PaymentDetails() ;

        System.out.println("The Clearingnummer of this account: "+paymentDetailsList.get(0).getPayment().getClearingNumber() );
        pr1.startPaymentBundle(paymentDetailsList.get(0).getPayment().getAccountNumber(), paymentDetailsList.get(0).getPayment().getPaymentDate(),paymentDetailsList.get(0).getPayment().getCurrency());

        for (PaymentDetails pd: paymentDetailsList){
            pr2.payment(pd.getAmount(),pd.getReference());
        }

        System.out.println("-----File:" + files[i].toString().substring(9)+ " end----------\n");


        for (PaymentDetails p: onlyValidFiles_Payment) {
            System.out.println("\u001b[00;34m" +
                    "------------------Den här data är validerad------------------" + " \u001b[00m");
            pr1.startPaymentBundle(p.getPayment().getAccountNumber(),p.getPayment().getPaymentDate(),p.getPayment().getCurrency());
            pr2.payment(p.getAmount(),p.getReference());
            pr2.endPaymentBundle();
        }
    }




}
}
