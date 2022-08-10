import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class DepositFiles {

public DepositFiles() {
    FilenameFilter filter = new FilenameFilter() {

        public boolean accept(File file, String str) {
            if (str.indexOf("_inbetalningstjansten.txt") != -1) {
                return true;
            } else {
                return false;
            }
        }
    };

    File[] files = new File("src/temp").listFiles(filter);

    System.out.println("Det finns "+files.length+ " _inbetalningstjensten.txt");
    for (int i = 0; i < files.length; ++i) {
        System.out.println("Fil" +(i+1)+": "+files[i].toString().substring(9));
    }

    for (int i = 0; i < files.length; ++i) {


        System.out.println("-----File: " + files[i].toString().substring(9)+" start------------");
        String name = "src/temp/" + files[i].toString().substring(9);// Do not need +"\""
        //System.out.println("File: " + name);
        //File inFile_B = new File(name);

        List<DepositDetails> depositDetailsList= Database.readDataFromDepositDetailsFile(name);

        Logic.checkTotalPayment_Deposit(depositDetailsList);
        List<DepositDetails> onlyValidFiles_Deposit = Logic.getValidFiles_Deposit(depositDetailsList);

        PaymentReceiver pr1 = new Deposit();
        PaymentReceiver pr2= new DepositDetails() ;

        System.out.println("Clearingnummeret av det accountnummeret: "+depositDetailsList.get(0).getDeposit().getClearingNumber() );
        pr1.startPaymentBundle(depositDetailsList.get(0).getDeposit().getAccountNumber(), null,null);

        int lastIndex=depositDetailsList.size()-1;//Read the last row but they are 0 or null value
        for (int j= 0; j<lastIndex; j++){
            pr2.payment(depositDetailsList.get(j).getAmount(),depositDetailsList.get(j).getReference());
        }

        //pr2.endPaymentBundle();
        System.out.println("-----File:" + files[i].toString().substring(9)+ " end----------\n");


        int lastIndex2= onlyValidFiles_Deposit.size()-1;
        for (int k= 0; k<lastIndex2; k++) {
            System.out.println("\u001b[00;34m" +
                    "------------------Den här data är validerad------------------" + " \u001b[00m");
            pr1.startPaymentBundle(onlyValidFiles_Deposit.get(k).getDeposit().getAccountNumber(), null,null);
            pr2.payment(onlyValidFiles_Deposit.get(k).getAmount(),onlyValidFiles_Deposit.get(k).getReference());
            pr2.endPaymentBundle();
        }
    }


}
}
