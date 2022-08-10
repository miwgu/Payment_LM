import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by Miwa Guhrés
 * Application date: 2022-05-27
 * Project: Lumera_uppgift
 * Copyright: MIT
 */

/*******************************************************************
 * Manage files which comes to the company
 * The program loads files and deal with them
 * Requirement: Use interface PaymentReceiver
 *------------------------------------------------------------------
 * You can select number in the menu.
 * The program shows the data in the files and a validation of the data
 *------------------------------------------------------------------
 *******The program******
 * 1.Check the name of files and read every row in the file and save
 *   the data in array for each kind of file
 * 2.Validate that the total amount is correct or not and save the
 *   correct data in array for each correct file
 * 3.Print the data when user select a number in the menu
********************************************************************/

public class Main {

    public Main()  {
        while (true)

        {
            Scanner sc = new Scanner(System.in);
            System.out.println(
                    "***********************************************\n" +
                    "Söka filer \n" +
                    "Ange nummer\n" +
                    "1) betalningservice\n" +
                    "2) inbetalningstjensten\n" +
                    "3) Avbryt\n" +
                    "***********************************************\n");
            String nummer = sc.next().trim();

            switch (nummer) {
                case "1":
                    PaymentFiles f = new PaymentFiles();
                    break;
                default:
                    System.out.println("Felaktig inmatning");
                    break;
                case "2":
                    DepositFiles d = new DepositFiles();
                    break;
                case "3":
                    System.exit(0);

            }
        }

    }
    public static void main(String[] args)  {

        Main main = new Main();

    }
}
