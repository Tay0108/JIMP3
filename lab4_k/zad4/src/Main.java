import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //String messageFile = "/home/Tay/Projects/Labs/JIMP3/lab4/zad4/src/message";
        //String cipherFile = "/home/Tay/Projects/Labs/JIMP3/lab4/zad4/src/cipher";

        String messageFile = args[0]; // argumenty w Intelij daje sie w menu rozwijanym i 'Edit configurations'
        String cipherFile = args[1];

        Scanner reader = new Scanner(System.in);

        while (true) {
            try {

                System.out.println("1. Cipher");
                System.out.println("2. Decipher");
                System.out.println("3. Exit");

                int option = Integer.parseInt(reader.nextLine());

                if(option == 3) {
                    System.exit(0);
                }

                if (!(option == 1 || option == 2)) {
                    throw new java.util.InputMismatchException();
                }

                System.out.println("1.ROT11");
                System.out.println("2. Polibius");
                int decision = Integer.parseInt(reader.nextLine());
                option = option * 10 + decision;

                ROT11 rot11 = new ROT11();
                Polibiusz polibiusz = new Polibiusz();

                switch (option) {
                    case 11:
                        System.out.println("Cipher ROT11...");
                        Cryptographer.cryptfile(messageFile, cipherFile, rot11);
                        break;
                    case 12:
                        System.out.println("Cipher Polibius...");
                        Cryptographer.cryptfile(messageFile, cipherFile, polibiusz);
                        break;
                    case 21:
                        System.out.println("Decipher ROT11...");
                        Cryptographer.decryptfile(cipherFile, messageFile, rot11);
                        break;
                    case 22:
                        System.out.println("Decipher Polibius...");
                        Cryptographer.decryptfile(cipherFile, messageFile, polibiusz);
                        break;
                    default:
                        throw new java.util.InputMismatchException();
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Wrong action number.");
            }
        }
    }
}