import java.io.IOException;
import java.util.Scanner;

/**
 * pliki nie powiny mieć polskich znakow
 */

/**
 * TODO: mozna dodac walidacje znakow oraz obsluge bledu java.io.FileNotFoundException
 */
public class Main {

    public static void main(String[] args) {

        String inputFile = args[0];
        String outputFile = args[1];

        while (true) {
            try {
                Scanner reader = new Scanner(System.in);

                System.out.println("Chcesz 1.zaszyfrowac czy 2.odszyfrowac?");
                int option = reader.nextInt();
                if (!(option == 1 || option == 2)) {
                    throw new java.util.InputMismatchException();
                }

                System.out.println("1.ROT11 czy 2.Polibiusz?");
                int decision = reader.nextInt();
                option = option * 10 + decision;

                ROT11 rot11 = new ROT11();
                Polibiusz polibiusz = new Polibiusz();

                switch (option) {
                    case 11:
                        System.out.println("bedziem szyfrowac ROT11");
                        Cryptographer.cryptfile(inputFile, outputFile, rot11);
                        System.exit(0);
                    case 12:
                        System.out.println("bedziem szyfrowac Polibiuszem");
                        Cryptographer.cryptfile(inputFile, outputFile, polibiusz);
                        System.exit(0);
                    case 21:
                        System.out.println("bedziem deszyfrowac ROT11");
                        Cryptographer.decryptfile(outputFile, inputFile, rot11);
                        System.exit(0);
                    case 22:
                        System.out.println("bedziem deszyfrowac Polibiuszem");
                        Cryptographer.decryptfile(outputFile, inputFile, polibiusz);
                        System.exit(0);
                    default:
                        throw new java.util.InputMismatchException();
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("BLAD: Niepoprawny wybor akcji.");
            } catch (IOException e) {
                System.out.println("Plik nie istnieje");
            }
        }
    }
}