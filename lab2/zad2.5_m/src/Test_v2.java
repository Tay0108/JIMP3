import java.util.Scanner;
import java.util.LinkedList;

public class Test_v2 {

    private static LinkedList<Punkt3d_v2> punkty = new LinkedList<Punkt3d_v2>();

    public static void main(String[] args) {

        Scanner reader;
        int option;

        while (true) {
            try {

                reader = new Scanner(System.in);
                System.out.println("________________\nWybierz akcje:\n________________");
                System.out.println("1. Wczytaj punkt 3D");
                System.out.println("2. Wyświetl wszystkie punkty");
                System.out.println("3. Oblicz odległość");
                System.out.println("4. Zakończ");

                option = reader.nextInt();

                /*
                Czasem może to wywołać problemy, (nextIt() pobiera też znak końca linii).
                Żeby tego uniknąć, należy:
                    option = Integer.parseInt(reader.nextLine());
                - to samo z Double'ami dla punktów.
                Ale wtedy trzeba też zmienić typ wyłapywanego błędu.
                 */

                switch (option) {
                    case 1:
                        System.out.print("Podaj x: ");
                        double x = reader.nextDouble();
                        System.out.print("Podaj y: ");
                        double y = reader.nextDouble();
                        System.out.print("Podaj z: ");
                        double z = reader.nextDouble();

                        Punkt3d_v2 p = new Punkt3d_v2(x, y, z);
                        punkty.addLast(p);
                        break;

                    case 2:
                        if (punkty.size() == 0) {
                            System.out.println("BLAD: Nie podano żadnych punktow w programie.");
                        }
                        for (int i = 0; i < punkty.size(); i++) {
                            System.out.println("Punkt " + (i + 1) + " x: " + punkty.get(i).getX() +
                                    "y: " + punkty.get(i).getY() + "z: " + punkty.get(i).getZ());
                        }
                        break;

                    case 3:
                        System.out.println("Podaj wspolrzedne pierwszego punktu.");
                        System.out.print("x: ");
                        double x1 = reader.nextDouble();
                        System.out.print("y: ");
                        double y1 = reader.nextDouble();
                        System.out.print("z: ");
                        double z1 = reader.nextDouble();
                        Punkt3d_v2 p1 = new Punkt3d_v2(x1, y1, z1);

                        System.out.println("Podaj wspolrzedne drugiego punktu.");
                        System.out.print("x: ");
                        double x2 = reader.nextDouble();
                        System.out.print("y: ");
                        double y2 = reader.nextDouble();
                        System.out.print("z: ");
                        double z2 = reader.nextDouble();
                        Punkt3d_v2 p2 = new Punkt3d_v2(x2, y2, z2);

                        System.out.println("Odleglosc wynosi: " + p1.distance(p2));
                        break;

                    case 4:
                        System.out.println("________________\nKoniec programu.\n________________");
                        System.exit(0);

                    default:
                        System.out.println("BLAD: Wybierz porawny numer z menu.");
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("BLAD: Niepoprawny wybor akcji.");
            }
        }
    }
}
