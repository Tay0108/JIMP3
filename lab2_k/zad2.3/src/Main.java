public class Main {
    public static void main(String[] args) {
        Punkt2D point1 = new Punkt2D(0, 0);
        Punkt2D point2 = new Punkt2D(1, 1);

        System.out.println("Podaj wspolrzedne pierwszego punktu: ");

        System.out.println("Podaj wspolrzedne drugiego punktu: ");

        System.out.println("Odleglosc miedzy tymi punktami wynosi: " + point2.distance(point1));


    }
}
