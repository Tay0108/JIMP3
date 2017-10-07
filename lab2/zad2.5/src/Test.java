import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class Test {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        int decision = 0;
        double x, y, z = 0;

        while (true) {
            reader = new Scanner(System.in);
            System.out.println("1. Wczytaj punkt 3D.");
            System.out.println("2. Wyswietl wszystkie punkty.");
            System.out.println("3. Oblicz odleglosc");
            System.out.println("4. Zakoncz");
            System.out.print("Twoj wybor: ");

            decision = reader.nextInt();

            switch (decision) {
                case 1:
                    System.out.println("Podaj wspolrzedne punktu: ");
                    reader = new Scanner(System.in);
                    x = reader.nextDouble();
                    y = reader.nextDouble();
                    z = reader.nextDouble();
                    Punkt3D point = new Punkt3D(x, y, z);
                    punkty.add(point);
                    break;
                case 2:
                    // foreach dla listy
                    for (Punkt3D i : punkty) {
                        System.out.println("Punkt nr "); // TODO
                        System.out.println("x: " + i.getX());
                        System.out.println("y: " + i.getY());
                        System.out.println("z: " + i.getZ());
                    }
                    break;
                case 3:
                    // oblicz odleglosc
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
                    reader.close();
                    System.exit(0);
                default:
                    System.out.println("Blad: nieprawidlowa wartosc");
                    break;
            }
        }
    }

    private static LinkedList<Punkt3D> punkty = new LinkedList<Punkt3D>();
}
