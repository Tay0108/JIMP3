public class Main {
    public static void main(String[] args) {
        Punkt3D punkt1 = new Punkt3D(0,0,0);
        Punkt3D punkt2 = new Punkt3D(1,1,1);

        System.out.println(punkt1.distance(punkt2));
    }
}
