import java.util.LinkedList;
import java.util.Scanner;
public class Test {

    public static void main(String[] args) {

        Scanner reader;
        int decision, j = 0;
        double x, y, z = 0;

        while (true) {
            reader = new Scanner(System.in);
            System.out.println("1. Wczytaj punkt 3D.");
            System.out.println("2. Wyswietl wszystkie punkty.");
            System.out.println("3. Oblicz odleglosc.");
            System.out.println("4. Zakoncz.");
            System.out.print("Twoj wybor: ");

            decision = reader.nextInt();

            switch (decision) {
                case 1:
                    // Add point to list
                    System.out.println("Podaj wspolrzedne punktu: ");
                    reader = new Scanner(System.in);
                    x = reader.nextDouble();
                    y = reader.nextDouble();
                    z = reader.nextDouble();
                    Punkt3D point = new Punkt3D(x, y, z);
                    punkty.add(point);
                    break;
                case 2:
                    // Show list
                    j = 0;
                    for (Punkt3D i : punkty) {
                        System.out.println("Punkt nr " + j );
                        System.out.println("x: " + i.getX());
                        System.out.println("y: " + i.getY());
                        System.out.println("z: " + i.getZ());
                        j++;
                    }
                    break;
                case 3:
                    // Count distance between two
                    reader = new Scanner(System.in);
                    System.out.println("Podaj wspolrzedne pierwszego punktu: ");
                    System.out.print("x: ");
                    x = reader.nextDouble();
                    System.out.print("y: ");
                    y = reader.nextDouble();
                    System.out.print("z: ");
                    z = reader.nextDouble();

                    Punkt3D point1 = new Punkt3D(x, y, z);
                    System.out.println("Podaj wspolrzedne drugiego punktu: ");
                    System.out.print("x: ");
                    x = reader.nextDouble();
                    System.out.print("y: ");
                    y = reader.nextDouble();
                    System.out.print("z: ");
                    z = reader.nextDouble();

                    Punkt3D point2 = new Punkt3D(x, y, z);
                    System.out.println("Odleglosc wynosi: " + point1.distance(point2));
                    break;
                case 4:
                    // Close program
                    reader.close();
                    System.exit(0);
                default:
                    // Error
                    System.out.println("Blad: nieprawidlowa wartosc");
                    break;
            }
        }
    }

    private static LinkedList<Punkt3D> punkty = new LinkedList<Punkt3D>();
}
